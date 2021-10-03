package com.github.shashi.misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class MyHashMap {

    public static void main(String[] args) {
        System.out.println("test");
    }

    private static class Pair<U,V>{
        public U key;
        public V value;
        public Pair(U key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private static class Bucket{
        private List<Pair<Integer,Integer>> bucket;
        public Bucket(){
            this.bucket = new LinkedList<>();
        }

        public Integer get(Integer key){
            int val = -1;
            for(Pair<Integer,Integer> pair : this.bucket){
                if(pair.key.equals(key))
                    val = pair.value;
            }
            return val;
        }

        public void update(Integer key, Integer value){
            for(Pair<Integer,Integer> pair : this.bucket){
                if(pair.key.equals(key)){
                    pair.value = value;
                    return;
                }

            }
            this.bucket.add(new Pair<Integer,Integer>(key,value));
        }

        public void remove(Integer key){
            for(Pair<Integer,Integer> pair : this.bucket){
                if(pair.key.equals(key)){
                    this.bucket.remove(pair);
                    return;
                }

            }
        }
    }

    /** Initialize your data structure here. */

    private int initialSize;
    private List<Bucket> table;

    public MyHashMap(){
        this.initialSize = 2069;
        this.table = new ArrayList<>();
        for(int i = 0; i < this.initialSize; i++){
            this.table.add(new Bucket());
        }
    }

    public void put(int key, int value){
        int hash = key % this.initialSize;
        this.table.get(hash).update(key,value);
    }

    public void get(int key){
        int hash = key % this.initialSize;
        this.table.get(hash).get(key);
    }

    public void remove(int key){
        int hash = key % this.initialSize;
        this.table.get(hash).remove(key);
    }

}