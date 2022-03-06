package com.github.shashi.leetcode;
import java.util.*;
public class Problem160 {
      public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return getIntersectionNodeA2(headA,headB);
    }

    public ListNode getIntersectionNodeA2(ListNode headA, ListNode headB){
        ListNode a = headA, b = headB;
        while(a != b){
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    public ListNode getIntersectionNodeA1(ListNode headA, ListNode headB){
        Set<ListNode> set = new HashSet<>();
        ListNode a = headA;
        while(a != null){
            set.add(a);
            a = a.next;
        }
        a = headB;
        while(a != null && !set.contains(a))
            a = a.next;
        return a;
    }
}