package com.github.shashi.leetcode;

public class Problem1721 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode swapNodes(ListNode head, int k) {
        return swapNodesA1(head,k);
    }

    public ListNode swapNodesA1(ListNode head, int k) {
        ListNode cur = head;
        int n = 1;
        while(n<k){
            n++;
            cur = cur.next;
        }
        ListNode kth = cur, lastkth=head;
        while(cur.next!=null){
            lastkth = lastkth.next;
            cur = cur.next;
        }
        n = kth.val;
        kth.val = lastkth.val;
        lastkth.val = n;
        return head;
    }
}