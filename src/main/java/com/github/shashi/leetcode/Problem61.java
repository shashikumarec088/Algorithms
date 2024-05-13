package com.github.shashi.leetcode;

public class Problem61 {
    /*
    Rotate List
    Given the head of a linked list, rotate the list to the right by k places.

    Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]
    Example 2:
    Input: head = [0,1,2], k = 4
    Output: [2,0,1]
    Constraints:
    The number of nodes in the list is in the range [0, 500].
    -100 <= Node.val <= 100
    0 <= k <= 2 * 109

    Approach 1:
    * intuition is to connect the tail to head and find the new tail and disconnect it from the head and return the new head
    algo:
    * check if head is null or single node then return it as we no need to rotate it
    * find the length and the tail in one pass of the list from head, initialize n=1 and iterate until head.next is not null
    * inc n and make oldTail as oldTail.next
    * connect the old tail to head
    * once again find the newtail by iterating from i=0 to n-(k%n)-1
    * get the new head as newtail.next and delink the newtail to head link
    * return new head
    time & space:
    * time is n and space is constant
     */
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
        if(head == null || head.next == null)return head;
        int n;
        ListNode oldTail = head;
        for(n=1; oldTail.next != null; n++)oldTail = oldTail.next;
        oldTail.next = head;
        ListNode newTail = head;
        for(int i=0; i< n -(k%n)-1; i++)newTail = newTail.next;
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}