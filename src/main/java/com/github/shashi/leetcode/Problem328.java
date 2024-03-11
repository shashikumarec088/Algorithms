package com.github.shashi.leetcode;

public class Problem328 {
    public ListNode oddEvenList(ListNode head) {
        return oddEvenListA1(head);
    }

    public ListNode oddEvenListA1(ListNode head) {
        ListNode dOdd = new ListNode(0), dEven = new ListNode(0),cur = head;
        ListNode cOdd = dOdd, cEven = dEven;
        int count = 1;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = null;
            if(count%2 != 0){
                cOdd.next = cur;
                cOdd = cOdd.next;
            }else{
                cEven.next = cur;
                cEven = cEven.next;
            }
            cur = next;
            count++;
        }
        cOdd.next = dEven.next;
        return dOdd.next;
    }
}