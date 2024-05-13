package com.github.shashi.leetcode;
import java.util.*;
public class Problem138 {
    /*
    Copy List with Random Pointer
    A linked list of length n is given such that each node contains an additional random pointer, which could point
    to any node in the list, or null.

    Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node
    has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes
    should point to new nodes in the copied list such that the pointers in the original list and copied list represent
    the same list state. None of the pointers in the new list should point to nodes in the original list.

    For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding
    two nodes x and y in the copied list, x.random --> y.

    Return the head of the copied linked list.

    The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val,
    random_index] where:

    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not
    point to any node.
    Your code will only be given the head of the original linked list.

    Example 1:
    Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
    Example 2:
    Input: head = [[1,1],[2,1]]
    Output: [[1,1],[2,1]]
    Example 3:
    Input: head = [[3,null],[3,0],[3,null]]
    Output: [[3,null],[3,0],[3,null]]
    Constraints:
    0 <= n <= 1000
    -104 <= Node.val <= 104
    Node.random is null or is pointing to some node in the linked list.

    Approach 1:
    * intuition is to create the new list as we traverse the list and also create a mapping between old and new
    node, which will help to link the random nodes by iterating the list and new list once again
    algo:
    * create a hashmap to hold the mapping between old and new node, create prev = node(-1), make cur = prev and
    old = head;
    * iterate old until null, for each iteration create the new node with value as old.val, and assign it to cur.next,
    then make the cur -> cur.next and old -> old.next, repeat until old is null
    * then reassign old = head, cur = prev.next, iterate until old is null
    * for each iteration check if old.random exists if so make cur.random = map.get(old.random),
    * make old -> old.next and cur -> cur.next, repeat until old is null
    time & space:
    * n time and n space

    Approach 2:
    * intuition is to create the new nodes as the next nodes in the list and then link the random nodes for new nodes
    and then delink the new list from old list
    algo:
    * initialize cur = head and iterate until cur is not null,
    * for each element create the new node and make cur.next and new node and newNode.next as cur.next and cur = cur.next.next
    *then once again make cur = head and link the random nodes for new nodes
    * if cur.random exists then make cur.next.random = cur.random.next
    * make cur = cur.next.next;
    * for delinking the lists, have prev = dummy, prev1 = prev and cur = head, iterate until cur is not null
    * make prev1.next = cur.next, cur = cur.next.next, prev1 = prev1.next
    * return prev.next;
    time & space:
    * n time and cont space

    Approach 3:
    * intuition is to look list as graph and copy as we do dfs
    algo:
    * create a map of node, node to maintain the mapping between old and new node.
    * call dfs with head and map
    * base case is if head is null return null or map contains head return map.get(head)
    * create new node and add to map
    * nNode.next = rec(head.next,map), nNode.random = rec(head.random,map);
    * return map.get(head) at the end;
     */
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    Map<Node,Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        return copyRandomListA1(head);
    }
    public Node copyRandomListA3(Node head) {
        Map<Node,Node> map = new HashMap<>();
        return rec(head,map);
    }

    public Node rec(Node head, Map<Node,Node> map){
        if(head==null)return head;
        if(map.containsKey(head))return map.get(head);
        Node nNode = new Node(head.val);
        map.put(head,nNode);
        nNode.next = rec(head.next,map);
        nNode.random = rec(head.random,map);
        return map.get(head);
    }
    public Node copyRandomListA1(Node head) {
        Node prev = new Node(-1);
        Node cur = prev, old = head;
        Map<Node,Node> map = new HashMap<>();
        while(old != null){
            Node nNode = new Node(old.val);
            map.put(old,nNode);
            old = old.next;
            cur.next = nNode;
            cur = cur.next;
        }
        old = head;
        cur = prev.next;
        while(old != null){
            if(old.random !=null)
                cur.random = map.get(old.random);
            cur = cur.next;
            old = old.next;
        }
        return prev.next;
    }
    public Node copyRandomListA2(Node head) {
        Node prev = new Node(-1);
        Node prev1 = prev;
        Node cur = head;
        while(cur != null){
            Node nNode = new Node(cur.val);
            Node next = cur.next;
            cur.next = nNode;
            nNode.next = next;
            cur = next;
        }
        cur = head;
        while(cur != null){
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        while(cur != null){
            prev1.next = cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
            prev1 = prev1.next;
        }
        return prev.next;
    }
}