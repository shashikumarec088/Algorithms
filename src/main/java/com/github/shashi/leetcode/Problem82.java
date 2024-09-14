package com.github.shashi.leetcode;

public class Problem82 {
    /*
    Remove Duplicates from Sorted List II
    Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
    from the original list. Return the linked list sorted as well.

    Example 1:
    Input: head = [1,2,3,3,4,4,5]
    Output: [1,2,5]
    Example 2:
    Input: head = [1,1,1,2,3]
    Output: [2,3]
    Constraints:
    The number of nodes in the list is in the range [0, 300].
    -100 <= Node.val <= 100
    The list is guaranteed to be sorted in ascending order.

    Approach 1:
    * intuition is to maintain the prev pointer and point to the head if it is not duplicate else iterate the head
    and assign next value to prev.next
    algo:
    * create a dummy node and point to head, make prev = dummy,
    * iterate until head is not null
    * if head and next are same then move head until the next value
    * make prev.next ad head.next,
    * if head is not same as next then move prev to next and also head to next
    * return dummy.next
    time & space:
    * n time and const space

    Approach 2:
    * slight modification of approach 1 came up with my own
    algo:
    * same as approach one
    * at each iteration we check if cur and next are same if so make cur=cur.next until next are not same
    * else make prev.next = cur, prev=prev.next
    * make  cur=cur.next repeat the loop
    * at the end make prev.next=null tp handle duplicates at the end

    Approach 3: recursion approach
    * intuition and code is same as approach 1 but using recursion

     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        return deleteDuplicatesA1(head);
    }

    public ListNode deleteDuplicatesA3(ListNode head) {
        ListNode dummy = new ListNode(1);
        ListNode prev=dummy;
        rec(prev,head);
        return dummy.next;
    }

    public void rec(ListNode prev,ListNode head){
        if(head==null){
            prev.next=null;
            return;
        }
        if(head.next==null || head.val!=head.next.val){
            prev.next=head;
            prev=prev.next;
            head=head.next;
            rec(prev,head);
        }else{
            while(head.next!=null && head.next.val==head.val)
                head=head.next;
            head=head.next;
            rec(prev,head);
        }
    }

    public ListNode deleteDuplicatesA1(ListNode head) {
        ListNode dummy = new ListNode(1,head);
        ListNode prev = dummy;
        while(head != null){
            if(head.next != null && head.next.val == head.val){
                while(head.next != null && head.next.val == head.val)
                    head = head.next;
                prev.next = head.next;
            }else prev = prev.next;
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode deleteDuplicatesA2(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode prev = dummy, cur = head;
        while(cur != null){
            if(cur.next != null && cur.val == cur.next.val){
                while(cur.next != null && cur.val == cur.next.val)
                    cur = cur.next;
            }else{
                prev.next = cur;
                prev = prev.next;
            }
            cur = cur.next;
        }
        // next nodes might be duplicates so we need to reset it
        prev.next=null;
        return dummy.next;

    }
}
