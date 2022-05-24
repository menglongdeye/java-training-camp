# 一、原子类
​	基本数据类型：主要有AtomicBoolean、AtomicInteger、AtomicLong，是对单一一个基本数据类型的原子修改
​	数组类型，主要有：AtomicIntegerArray、AtomicLongArray、AtomicReferenceArray，是对数组内元素的修改，而非数组本身修改
​	引用类型，主要有：AtomicReference、AtomicReferenceFieldUpdater、AtomicMarkableReference，对引用内部变量做修改
​	原子更新字段类：AtomicIntegerFieldUpdater、AtomicLongFieldUpdater、AtomicStampedFieldUpdater，是对单一一个基本数据类型的原子修改

​	对比：

​		前三种区别比较明显，主要是AtomicInteger和AtomicIntegerFieldUpdater这两组的区别，首先从功能上来说，是一样的，但是从可用性和内存占用上来说，AtomicIntegerFieldUpdater会更好。

​		功能性：要使用AtomicInteger需要修改代码，将原来int类型改造成AtomicInteger，使用该对象的地方都要进行调整（多进行一次get()操作获取值），但是有时候代码不是我们想改就能改动的，例如引用一些三方组件。

​		内存占用，AtomicInteger中的成员变量int value，而AtomicIntegerFieldUpdater是staic final类型，即类变量，并不会占用当前对象的内存。正是基于AtomicIntegerFieldUpdater该使用特性，当字段所属的类会被创建大量的实例时，如果用AtomicInteger每个实例里面都要创建AtomicInteger对象，从而多出很多不必要的内存消耗。

# 二、并发容器
​	1、并发集合
​		ConcurrentHashMap：JDK1.7使用分段锁的方式实现，JDK8使用CAS+Synchrozied方式实现。主要涉及并发的点是初始化、put、扩容，而next和value使用volatile修饰，不需要考虑并发，另外size方法也使用了数据的方式保证并发。
​		CopyOnWriteArrayList：写时创建一个新的副本，写完之后将数组指针指向新的数组，get方法读取的是快照
​	2、阻塞队列
​		ArrayBlockingQueue：一个由数组组成的有界阻塞队列
​		LinkedBlockingQueue：一个由链表组成的有界阻塞队列，队列默认和最大长度是Integer.MAX_VALUE
​		PriortyBlockingQueue：具有优先级的阻塞队列，默认情况下元素采取自然序列升序排列，也可以自己实现compareTo()方法来自定义排序方式；但是同优先级的元素不保证顺序
​		DelayQueue：延迟阻塞队列
​		SynchronousQueue：单一阻塞队列
​		LinkedTransferQueue：一个由链表组成的无界阻塞队列
​		LinkedBlockingDeque：一个由链表组成的双向阻塞队列

# 三、并发工具
​	CountDownLatch：主线程调用await等待指定数量子线程运行完成后继续执行，子线程调用countDown表示执行完成
​	CycllicBarrier：所有线程调用await阻塞，等有指定数量的线程都到达await后，所有线程继续执行
​	Semaphore：信号量，使用acquire获取，使用release释放，保证同一时间内只有相等的线程运行
​	Exchanger：线程间的数据交换

​	CountDownLatch和CycllicBarrier的区别

​		CountDownLatch拦截的是主线程，触发后执行时执行的是主线程的逻辑、CycllicBarrier拦截的子线程，触发后是触发所有被拦截的子线程；CountDownLatch数量是累减的，CycllicBarrier是累加的；CountDownLatch数量不可重用，CycllicBarrier可以重置；CountDownLatch是基于AQS的state来实现的，CycllicBarrier是基于consition的await和signalAll实现的。

# 四、线程池
​	Executor：内置创建线程池的工具类
​		FixedThreadPool：固定长度线程池
​		SingleThreadExecutor：单一长度线程池
​		CacheThreadPool：具有缓存功能的线程池，一般用作任务量大但是执行时间短的任务，可以复用之前创建的线程。
​		newScheduledThreadPool：具有延时调度功能的线程池

​	ThreadPoolExecutor：线程池类，可以调用构造函数自定义线程池。

# 五、锁
​	synchrozied：存在自适应偏向锁、轻量级锁、重量级锁，会锁升级，可以锁方法、代码块、对象，锁对象可以使用当前对象和新创建对象来控制锁粒度
​	Lock：使用Lock接口和AQS接口实现，有一个volatile修饰的state属性
​		ReentrantLock：可重入锁，使用state累加和累减，加锁就对state加一，解锁就减一，如果为0，就真正解锁
​		ReentrantReadWriteLock：state分高低位，高位是读锁，低位是写锁，读锁是共享锁，写锁是排它锁
​		LockSupport：提供park和unpark来等待许可和发放许可，可以指定等待的线程和释放的线程
​		Condition：可以在同一个锁中创建不同的维度或不同事件类型的等待唤醒操作，作用于Lock，其await和single可以对比synchrozied块中object的wait、notify

# 六、异步编程
​	任务提交
​		execute：提交任务，没有返回值，如果任务报错，在子线程中报错
​		submit：可以有返回值，如果报错，在使用future.get接收时，会报错
​	异步处理
​		Future：接收线程执行结果
​		FutureTask：由于Future是一个接口，没有办法创建对象，而Future是一个对象，实现了Future，因此其可以直接创建对象使用
​		CompletableFuture：可以在执行成功或者异常时主动回调，并且提供了丰富的方法
​		stream().parallel()：JDK8提供的并行处理方式
​		Threadlocal：当前线程的ThreadlocalMap属性，key是当前线程，value是当前线程设置的值，保证线程安全，需要注意内存泄漏问题，不使用时需要remove。