package com.github.shashi.leetcode;

public class Problem25 {
     class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        return reverseKGroupA2(head,k);
    }

    public ListNode reverseKGroupA2(ListNode head, int k){
        ListNode revHead=null, newHead=null,kTail=null;
        while(head != null){
            ListNode cur = head;
            int i;
            for( i=0; i<k && cur !=null; i++)
                cur = cur.next;
            if(i<k) revHead = head;
            else revHead = reverseItr(head,k);
            if(newHead==null)
                newHead = revHead;
            if(kTail != null)
                kTail.next = revHead;
            kTail = head;
            head = cur;
        }
        return newHead;
    }

    public ListNode reverseKGroupA1(ListNode head, int k){
        ListNode cur = head;
        int i;
        for(i=0; i<k && cur != null; i++)
            cur = cur.next;
        if(i < k) return head;
        ListNode prev = reverseItr(head,k);
        head.next = reverseKGroupA1(cur,k);
        return prev;
    }

    public ListNode reverseItr(ListNode head, int k){
        ListNode prev = null, cur = head;
        while(k>0){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            k--;
        }
        return prev;
    }
}
