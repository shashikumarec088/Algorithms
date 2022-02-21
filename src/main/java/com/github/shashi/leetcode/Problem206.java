package com.github.shashi.leetcode;

public class Problem206 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode reverseList(ListNode head) {
        return reverseListA2(head);
    }

    public ListNode reverseListA2(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode newHead = reverseListA2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverListA1(ListNode head){
        ListNode prev = null;
        ListNode current = head;
        while(current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
