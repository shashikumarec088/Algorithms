package com.github.shashi.leetcode;

public class Problem19 {
    /*
    Remove Nth Node From End of List
    Given the head of a linked list, remove the nth node from the end of the list and return its head.

    Example 1:
    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]
    Example 2:
    Input: head = [1], n = 1
    Output: []
    Example 3:
    Input: head = [1,2], n = 1
    Output: [1]
    Constraints:
    The number of nodes in the list is sz.
    1 <= sz <= 30
    0 <= Node.val <= 100
    1 <= n <= sz

    Follow up: Could you do this in one pass?

    Approach 1:
    *intuition is to findout the length of the list and findout the prev element then link the prev.next to next.next
    algo:
    * create the dummy node and set dummy.next = head, this is to handle the first node deletion and null head cases
    * set cur = head and iterate till the cur is not null and keep counting the node l
    * iterate until l-n>0 to findout the previous node
    * link prev.next to prev.next.next
    * return the dummy.next
    time & space:
    time is n but it is 2 pass and space is const

    Approach 2:
    * intuition is to have 2 pointers fast and slow and first move the fast pointer n moves and then move both the
    pointers until fast becomes null then we get the prev node then link it to its 2nd next.
    algo:
    * create a dummy node and make dummy.next to head, cur = head;
    * iterate cur until n>0 and dec n
    * then have prev = dummy and iterate prev, cur until cur != null
    * then set prev.next = prev.next.next
    * return dummy.next
    time & space:
    * takes 1 pass n and const space
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNthFromEndA1(head,n);
    }

    public ListNode removeNthFromEndA1(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, cur = head;
        int l = 0;
        while(cur != null){
            l++;
            cur = cur.next;
        }
        dummy.next = head;
        while(l-n>0){
            prev = prev.next;
            l--;
        }

        prev.next = prev.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEndA2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, cur = head;
        dummy.next = head;
        while(n>0){
            cur = cur.next;
            n--;
        }
        while(cur != null){
            cur = cur.next;
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }
}