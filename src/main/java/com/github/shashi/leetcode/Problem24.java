package com.github.shashi.leetcode;

public class Problem24 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode swapPairs(ListNode head) {
        return swapPairsA2(head);
    }

    public ListNode swapPairsA2(ListNode head) {
        if(head==null || head.next==null)return head;
        ListNode dummy = new ListNode();
        dummy.next= head;
        ListNode prev = dummy;
        while(head!=null && head.next!=null){
            ListNode first = head;
            ListNode second = head.next;
            ListNode third = second.next;
            //swap the nodes
            first.next = third;
            second.next = first;
            // link prev to currect next previous which is second
            prev.next = second;

            // update prev and head for next iteration

            // here prev is updated to first because the next prev will be first
            // after the swap is made
            prev = first;
            head = third;
        }
        return dummy.next;
    }

    public ListNode swapPairsA1(ListNode head) {
        if(head==null || head.next==null)return head;
        ListNode nn = head.next.next;
        ListNode n = head.next;
        n.next = head;
        head.next = swapPairsA1(nn);
        return n;
    }
}