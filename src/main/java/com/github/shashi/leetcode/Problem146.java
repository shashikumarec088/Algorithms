package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem146 {
    /*
    LRU Cache
    Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

    Implement the LRUCache class:
    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to
    the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.

    Example 1:
    Input
    ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    Output
    [null, null, null, 1, null, -1, null, -1, 3, 4]

    Explanation
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    lRUCache.get(1);    // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    lRUCache.get(2);    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    lRUCache.get(1);    // return -1 (not found)
    lRUCache.get(3);    // return 3
    lRUCache.get(4);    // return 4

    Constraints:
    1 <= capacity <= 3000
    0 <= key <= 104
    0 <= value <= 105
    At most 2 * 105 calls will be made to get and put.

    Approach 1:
    * intuition is to make lru we need to have map which has constant time lookup and constant time addition,
    to support the recency we can use dll and keep appending the nodes at head and remove the nodes from start
    when capacity is reached, when the get is called up keep the recency in sync by betting the node removing it
    from the list and adding at the end so that it will not be evicted when there is a capacity crunch.
    algo:
    * create a node class with prev, next, key and value properties and constructor takes key and val
    * create a dll class which has size, tail, head properties and has add, remove and removelast methods and
    constructor which defines head and tail nodes with dummy nodes and connect head and tail each other in both
    directions
    * add method takes the node and adds at the start of the dll, sets node prev to head and next to existing
    head next and sets the head next to current node and also makes head.next.prev to current node
    * increased the dll size by 1
    * remove method takes node and gets its prev and next nodes. then sets prev.next to next and next.prev = prev
    *removeLast method get the last node by doing tail.prev and calls remove method then returns the removed node
    * LRU cache has map of int, node and dll and capacity, we define these in constructor and initialize capacity
    * in get we check if key exists if so then get the node from map, then remove from dll so that the position
    of node is reset to the head node and then add the node so that the node is added at the head and will
    get evicted last since eviction happens from the end, if no key present return -1.
    * in put method if key exists, get the node from the map, remove from list and add it to reset the position
    and update the value.
    * if key does not exists then check the capacity if reached then remove the last node from the dll by using
    dll.removeLast method, since it returns the node which stores both key and value, take the key and remove the
    key from map as well
    * then create a new node with key and value put it in map and add to dll
    time & space:
    * each operation is constant time ans space is n
     */
    class LRUCache {
        DLinkedList dll;
        Map<Integer,Node> map;
        int capacity;
        class Node{
            Node prev, next;
            int key,val;
            Node(int key, int val){
                this.key = key;
                this.val = val;
            }
        }

        class DLinkedList{
            Node head,tail;
            int size = 0;
            DLinkedList(){
                head = new Node(-1,1);
                tail = new Node(-1,1);
                tail.prev = head;
                head.next = tail;
            }

            public void add(Node node){
                node.next = head.next;
                head.next.prev = node;
                head.next = node;
                node.prev = head;
                size++;
            }


            public void remove(Node node){
                Node next = node.next;
                Node prev = node.prev;
                next.prev = prev;
                prev.next = next;
                size--;
            }

            public Node removeLast(){
                Node node = tail.prev;
                remove(node);
                return node;
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            dll = new DLinkedList();
        }

        public int get(int key) {
            if(!map.containsKey(key))return -1;
            Node node = map.get(key);
            dll.remove(node);
            dll.add(node);
            return node.val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                Node node = map.get(key);
                dll.remove(node);
                node.val = value;
                dll.add(node);
            }else{
                if(map.size()==capacity){
                    Node node = dll.removeLast();
                    map.remove(node.key);
                }
                Node node = new Node(key,value);
                dll.add(node);
                map.put(key,node);
            }

        }
    }
}
