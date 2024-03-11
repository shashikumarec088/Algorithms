package com.github.shashi.leetcode;

public class Problem19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNthFromEndA1(head,n);
    }

    public ListNode removeNthFromEndA2(ListNode head, int n) {
        if(head.next==null)return null;
        ListNode slow = head, fast = head;
        while(n>0){
            fast = fast.next;
            n--;
        }
        while(fast != null && fast.next != null){
            slow= slow.next;
            fast = fast.next;

        }
        if(fast==null)return slow.next;
        slow.next = slow.next.next;
        return head;
    }

    public ListNode removeNthFromEndA1(ListNode head, int n) {
        int c=0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            c++;
        }
        ListNode prev = head;
        int diff = c-n;
        while(prev != null && diff>0){
            prev = prev.next;
            diff--;
        }
        if(prev==null || c==1)return null;
        prev.next = prev.next==null?null:prev.next.next;
        return head;
    }
}