package com.lcl.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache {

    public static void main1(String[] args) {
        LRUCache lruCache = new LRUCache(2);
//        ["LRUCache","put","put","get","put","get","put","get","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        lruCache.put(1,1);
        lruCache.put(2,2);
        int value = lruCache.get(1);
        System.out.println(value);
        lruCache.put(3,3);
        value = lruCache.get(2);
        System.out.println(value);
        lruCache.put(4,4);
        value = lruCache.get(1);
        System.out.println(value);
        value = lruCache.get(3);
        System.out.println(value);
        value = lruCache.get(4);
        System.out.println(value);
    }

    class ListNode{
        public int key;
        public int val;
        public ListNode pre;
        public ListNode next;

        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }

        public ListNode(int key){
            this.key = key;
        }

        public ListNode(){

        }
    }

    private ListNode head;
    private ListNode tail;
    private Map<Integer, ListNode> map;
    private int capacity;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
    }

    private void remove(ListNode node){
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    private void insert(ListNode node){
        ListNode temp = head.next;
        head.next = node;
        node.pre = head;

        node.next = temp;
        temp.pre = node;

    }

    public int get(int key) {

        if(map.containsKey(key)){
            ListNode keyNode = map.get(key);
            remove(keyNode);
            insert(keyNode);
            return keyNode.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode keyNode = map.get(key);
            keyNode.val = value;
            //更新前后节点
            remove(keyNode);
            insert(keyNode);
        }else{
            ListNode keyNode = new ListNode(key, value);
            insert(keyNode);
            if(map.size() >= capacity){
                map.remove(tail.pre.key);
                remove(tail.pre);
            }
            map.put(key, keyNode);
        }
    }



    public void print(int key){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.set(0,5);

        System.out.println("get === " + key);
        ListNode k = head.next;
        while(k != tail){
            System.out.print(k.val+",");
            k = k.next;
        }
        System.out.println("-------");
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.subdomainVisits(new String[]{"9001 discuss.leetcode.com"});
    }


    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap();
        for(String cpdomain : cpdomains){
            String[] cpdoainArr = cpdomain.split(" ");
            int count = Integer.parseInt(cpdoainArr[0]);
            String temp = cpdoainArr[1];
            String[] tempArr = temp.split("\\.");
            String key = "";
            System.out.println(tempArr.length-1);
            for(int i=tempArr.length-1;i>=0;i--){
                System.out.println(tempArr.length-1);
                key = i == tempArr.length - 1 ? tempArr[i] : tempArr[i] + "." + key;


                if(!map.containsKey(key)){
                    map.put(key, count);
                }else{
                    count += map.get(key);
                    map.put(key, count);
                }
            }
        }
        List<String> ans = new ArrayList();
        for(String key : map.keySet()){
            ans.add(key + " " + map.get(key));
        }
        return ans;
    }


    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if(n == 0){
            return new ArrayList();
        }
        for(int i=1;i<=n;i++){
            List<String> aList = generateParenthesis(i-1);
            List<String> bList = generateParenthesis(n-i);
            String s = "(" + toString(aList) + ")" + toString(bList);
            ans.add(s);
        }
        return ans;
    }

    private String toString(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s);
        }
        return sb.toString();
    }



    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        List<Integer> list = new ArrayList();
        List<Boolean> flagList = new ArrayList(nums.length);
        for(int i=0;i<nums.length;i++){
            flagList.add(false);
        }
        permuteNew(nums, 0, list, flagList, ans);
        return ans;
    }

    private void permuteNew(int[] nums, int i, List<Integer> list, List<Boolean> flagList, List<List<Integer>> ans){
        if(i == nums.length){
            if(list.size()<nums.length){
                return;
            }
            printList(list);
            System.out.println("");
            List temp = new ArrayList(list);
            ans.add(temp);
            return;
        }
        for(int index=0;index<nums.length;index++){
            if(!flagList.get(index)){
                permuteNew(nums, i+1, list, flagList, ans);
                list.add(nums[index]);
                flagList.set(index, true);
                printList2(flagList);
                permuteNew(nums, i+1, list, flagList, ans);
                list.remove(i);
                flagList.set(index,false);
            }
        }

    }

    private void printList(List<Integer> list){
        for(Integer i : list){
            System.out.print(i + ",");
        }
    }

    private void printList2(List<Boolean> list){
        for(Boolean i : list){
            System.out.print(i + ",");
        }
    }
}
