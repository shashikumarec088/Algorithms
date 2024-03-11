package com.github.shashi.leetcode;

public class Problem61 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode rotateRight(ListNode head, int k) {
        return rotateRightA1(head,k);
    }

    public ListNode rotateRightA1(ListNode head, int k) {
        int l=0;
        ListNode cur=head;
        while(cur!= null){
            l++;
            cur = cur.next;
        }
        if(k>= l && l>0) k = k%l;
        if(l<=1 || k==0 || head== null) return head;
        ListNode prev = head;

        cur = head.next;
        int pos = l-k-1;
        while(pos>0){
            prev = cur;
            cur = cur.next;
            pos--;
        }
        prev.next = null;
        ListNode newHead = cur;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = head;
        return newHead;
    }
}