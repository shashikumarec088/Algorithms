package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class Problem141 {
    public boolean hasCycle(ListNode head) {
        return hasCycleA2(head);
    }

    public boolean hasCycleA2(ListNode head) {
        ListNode slow = head, fast = head != null && head.next!=null?head.next.next:null;
        while(slow != null && fast != null){
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next != null? fast.next.next:null;
        }
        return false;
    }

    public boolean hasCycleA1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while(cur != null && !set.contains(cur)){
            set.add(cur);
            cur = cur.next;
        }
        if(cur==null)return false;
        return true;
    }
}