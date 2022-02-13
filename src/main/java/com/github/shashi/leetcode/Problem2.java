package com.github.shashi.leetcode;

public class Problem2 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersA1(l1,l2);
    }

    public ListNode addTwoNumbersA1(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;
        int man = 0;
        while(l1 !=null && l2 != null){
            int sum = l1.val+l2.val+man;
            man = sum/10;
            sum %=10;
            ans.next = new ListNode(sum);
            ans = ans.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null){
            int sum = l1.val+man;
            man = sum/10;
            sum %= 10;
            ans.next = new ListNode(sum);
            ans = ans.next;
            l1 = l1.next;
        }

        while(l2 != null){
            int sum = l2.val+man;
            man = sum/10;
            sum %= 10;
            ans.next = new ListNode(sum);
            ans = ans.next;
            l2 = l2.next;
        }

        if(man>0) ans.next = new ListNode(man);
        return dummy.next;
    }

}
