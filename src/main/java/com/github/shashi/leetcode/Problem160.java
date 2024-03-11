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

    private int getLength(ListNode l){
        int count=0;
        while(l != null){
            count++;
            l = l.next;
        }
        return count;
    }

    public static void main(String[] args) {
        Problem160 problem160 = new Problem160();
    }

    public ListNode getIntersectionNodeA3(ListNode headA, ListNode headB) {
        int l1 = getLength(headA);
        int l2 = getLength(headB);
        if(l2<l1){
            ListNode temp = headA;
            headA = headB;
            headB = temp;
            int t = l2;
            l2=l1;
            l1=l2;
        }
        int diff = l1-l2;
        while(diff > 0 && headA!= null){
            headA = headA.next;
            diff--;
        }

        while(headA != null && headB != null){
            if(headA==headB)return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
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