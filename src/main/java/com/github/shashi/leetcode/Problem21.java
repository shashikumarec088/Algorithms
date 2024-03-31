package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem21 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static void main(String[] args) {
        Problem21 problem119 = new Problem21();
        System.out.println(problem119.mergeTwoLists(null,null));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return  mergeItr(list1,list2);
    }

    /*
     * it is very straight forward, once one of the list is null
     * just appent the remaining list to prev, no need of another while loop;
     */
    public ListNode mergeItr(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while(l1 != null & l2 != null){
            if(l1.val <= l2.val){
                prev.next =l1;
                l1=l1.next;
            }else{
                prev.next =l2;
                l2=l2.next;
            }
            prev = prev.next;
        }
        if(l1 != null) prev.next=l1;
        if(l2 != null)prev.next=l2;
        return dummy.next;
    }

    /*
     * approach is if l1 < l2 then l1 will be the head and we need to
     * merge the remaining part of l1 and complete l2
     */
    public ListNode mergeRec(ListNode l1, ListNode l2){
        if(l1==null)return l2;
        if(l2==null)return l1;
        if(l1.val <= l2.val){
            l1.next=mergeRec(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeRec(l1,l2.next);
            return l2;
        }
    }
}
