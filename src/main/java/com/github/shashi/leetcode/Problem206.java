package com.github.shashi.leetcode;

public class Problem206 {
    /*
    Given the head of a singly linked list, reverse the list, and return the reversed list.



    Example 1:


    Input: head = [1,2,3,4,5]
    Output: [5,4,3,2,1]
    Example 2:


    Input: head = [1,2]
    Output: [2,1]
    Example 3:

    Input: head = []
    Output: []


    Constraints:

    The number of nodes in the list is the range [0, 5000].
    -5000 <= Node.val <= 5000


    Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?

    approach 1: iterative approach
    * intuition is to point the next pointer to previous element and
    repeat it until we reach the last element
    algo:
    * init prev = null
    * iterate until headis not null
        * next = head.next
        * head.next = prev
        * prev = head
        * head = head.next
    * return prev

    time & space:
    * it takes n time and const space

    approach 2: recursion
    * intuition is same as iterative but doing it recursively
    algo:
    * call recursion with head
    * if head is null or head.next is null then return head
    * last = rec(head.next)
    * make head.next.next = head
    * head.next = null
    * return last

    time & space:
    * it takes n time and n space for recursive stack

     */
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
