## 一、单例优缺点总结
    1、饿汉式：
        优点：单例对象的创建是线程安全的；获取单例对象时不需要加锁。
        缺点：单例对象的创建，不是延时加载。
    2、懒汉式（双重检测锁）：
        对象的创建是线程安全的。支持延时加载。获取对象时不需要加锁。
    3、懒汉式（静态内部类）
        对象的创建是线程安全的。支持延时加载。获取对象时不需要加锁。
    4、枚举
        用枚举来实现单例，是最简单的方式。这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
## 二、maven/spring 的 profile 机制，都有什么用法？

## 三、总结 Hibernate 与 MyBatis 的各方面异同点。
        相同点：

    1、 Hibernate ：Hibernate 是当前非常流行的ORM框架，对数据库结构提供了较为完整的封装，都是为了简化Dao层的操作。
    Mybatis：Mybatis同样也是非常流行的ORM框架，主要着力点在于POJO 与SQL之间的映射关系，都是为了简化Dao层的操作。
    
    2、Hibernate与MyBatis都可以是通过SessionFactoryBuider由XML配置文件生成SessionFactory，然后由SessionFactory 生 成Session，最后由Session来开启执行事务和SQL语句。其中SessionFactoryBuider，SessionFactory，Session的生命周期都是差不多的。Hibernate和MyBatis都支持JDBC和JTA事务处理。

    3、Hibernate和Mybatis的二级缓存除了采用系统默认的缓存机制外，都可以通过实现自己的缓存或为其他第三方缓存方案。

    不同点：

    1、hibernate是全自动，而mybatis是半自动。
    Hibernate完全实现了对JDBC的封装,可看成"全自动洗衣机".调用一个save()方法就能实现插入操作,完全不需要写sql.当然,它也支持类似sql的hql语句.ibatis需要自己写sql

    2、hibernate不怎么需要写sql，而mybatis需要把sql写在配置文件里面。

    3、 hibernate数据库移植性和扩展性远大于mybatis，维护性比较好。
    Mybatis由于所有SQL都是依赖数据库书写的，所以扩展性，迁移性比较差，成本很高。Hibernate与数据库具体的关联都在XML中，所以HQL对具体是用什么数据库并不是很关心，大大降低了对象与数据库（oracle、mysql等）的耦合性。

    4、hibernate开发速度比mybatis相对快点
    Hibernate的开发难度要大于Mybatis。主要由于Hibernate比较复杂、庞大，学习周期较长。
    而Mybatis则相对简单一些，并且Mybatis主要依赖于sql的书写，让开发者感觉更熟悉。
    Hibernate和MyBatis都有相应的代码生成工具。可以生成简单基本的DAO层方法。
    针对高级查询，Mybatis需要手动编写SQL语句，以及ResultMap。而Hibernate有良好的映射机制，开发者无需关心SQL的生成与结果映射，可以更专注于业务流程。

    5、 hibernate拥有完整的日志系统，mybatis则欠缺一些。
    hibernate日志系统非常健全，涉及广泛，包括：sql记录、关系异常、优化警告、缓存提示、脏数据警告等；而mybatis则除了基本记录功能外，功能薄弱很多。

    6、mybatis相比hibernate需要关心很多细节
    hibernate配置要比mybatis复杂的多，学习成本也比mybatis高。但也正因为mybatis使用简单，才导致它要比hibernate关心很多技术细节。mybatis由于不用考虑很多细节，开发模式上与传统jdbc区别很小，因此很容易上手并开发项目，但忽略细节会导致项目前期bug较多，因而开发出相对稳定的软件很慢，而开发出软件却很快。hibernate则正好与之相反。但是如果使用hibernate很熟练的话，实际上开发效率丝毫不差于甚至超越mybatis。

    7、sql直接优化上，mybatis要比hibernate方便很多
    由于mybatis的sql都是写在xml里，因此优化sql比hibernate方便很多。而hibernate的sql很多都是自动生成的，无法直接维护sql；虽有hql，但功能还是不及sql强大，见到报表等变态需求时，hql也歇菜，也就是说hql是有局限的；hibernate虽然也支持原生sql，但开发模式上却与orm不同，需要转换思维，因此使用上不是非常方便。总之写sql的灵活度上hibernate不及mybatis。

    8、mybait比hibernate更加灵活，驾驭型更好

    Mybatis优势
        MyBatis可以进行更为细致的SQL优化，可以减少查询字段。
        MyBatis容易掌握，而Hibernate门槛较高。
    Hibernate优势
        Hibernate的DAO层开发比MyBatis简单，Mybatis需要维护SQL和结果映射。
        Hibernate对对象的维护和缓存要比MyBatis好，对增删改查的对象的维护要方便。
        Hibernate数据库移植性很好，MyBatis的数据库移植性不好，不同的数据库需要写不同SQL。
        Hibernate有更好的二级缓存机制，可以使用第三方缓存。MyBatis本身提供的缓存机制不佳。