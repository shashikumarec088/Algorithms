package com.github.shashi.leetcode;

public class Problem203 {
    public ListNode removeElements(ListNode head, int val) {
        return removeElementsA1(head, val);
    }

    public ListNode removeElementsA1(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur=head, prev=dummy;
        while(cur != null){
            if(cur.val == val){
                prev.next = cur.next;
            }else prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}