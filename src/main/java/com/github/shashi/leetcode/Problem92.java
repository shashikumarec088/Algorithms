package com.github.shashi.leetcode;

public class Problem92 {
    /*
    Reverse Linked List II
    Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the
    list from position left to position right, and return the reversed list.



    Example 1:
    Input: head = [1,2,3,4,5], left = 2, right = 4
    Output: [1,4,3,2,5]
    Example 2:
    Input: head = [5], left = 1, right = 1
    Output: [5]
    Constraints:
    The number of nodes in the list is n.
    1 <= n <= 500
    -500 <= Node.val <= 500
    1 <= left <= right <= n
    Follow up: Could you do it in one pass?

    Approach 1:
    * intuition is to first move the cur to left position and reverse until the right position and link the nodes.
    algo:
    * create a dummy and assign its next to head, make prev = dummy
    * iterate from i=1 to i<left and make prev = prev.next
    * store the leftPrev = prev, leftStat = prev.next to link the nodes after reversing the nodes
    *  make cur = prev.next, prev = null iterate from i=left to i<= right, reverse the nodes, it next = cur.next and
    * make cur.next to prev and prev = cur and cur = next
    * link leftStart.next cur node to establish the link with the remaining list
    * link leftPrev.next = prev to link the before list to latest head
    * return dummy.next
    time & space:
    * n time and const space

    Approach 2:
    * intuition is to move the reversal till left becomes 1 and call the recursion method to reverse the first n nodes
    * we need to remember to link the successor list to the reversed list when reversing first n nodes
    algo:
    * check if left is 1 if so call reverseFirstN method with head and r else
    * call the same method on head.next by reducing left and right by 1 value since we reduced 1 node from the list
    * and assign the result to head.next
    *return the head at the end
    * in reverseFirstN  we check if we reached the end by checking if r=1, if so, we store the next element in successor
    to link to the rail of reversed list and we return head
    * we call the recursion on reverseFirstN with head.next and reduce r by1 and store it as last
    * we make head.next.next = head
    * we need to link the cur next to successor  (this is the difference compared to recursive reverse)
    * then we return the last node
    time & space:
    * n time and n space for recursion stack.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        return reverseBetweenA1(head,left,right);
    }

    public ListNode reverseBetweenA2(ListNode head, int left, int right) {
        if(left == 1)
            return reverseFirstN( head,right);
        head.next = reverseBetweenA2(head.next,left-1,right-1);
        return head;
    }
    ListNode successor;
    public ListNode reverseFirstN(ListNode head, int r){
        if(r==1){
            successor = head.next;
            return head;
        }
        ListNode last = reverseFirstN(head.next,r-1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    public ListNode reverseBetweenA1(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        if(left==right)return head;
        ListNode prev = dummy;
        for(int i=1; i<left;i++)
            prev = prev.next;
        ListNode leftPrev = prev, leftStart = prev.next,cur = prev.next;
        prev = null;
        for(int i=left; i<=right;i++){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        leftStart.next = cur;
        leftPrev.next = prev;
        return dummy.next;
    }
}