package com.github.shashi.leetcode;

public class Problem25 {
    /*
    25. Reverse Nodes in k-Group
    Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
    multiple of k then left-out nodes, in the end, should remain as it is.

    You may not alter the values in the list's nodes, only nodes themselves may be changed.

    Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [2,1,4,3,5]
    Example 2:
    Input: head = [1,2,3,4,5], k = 3
    Output: [3,2,1,4,5]
    Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 5000
    0 <= Node.val <= 1000
    Follow-up: Can you solve the problem in O(1) extra memory space?

    Approach 1: recursive solution
    * intuition is to count the number of nodes until it reaches k, if minimum k nodes are their then we reverse
    the first k nodes and then recursively reverse the remaining part of the list
    algo:
    * init cur = head, count=0
    * iterate until count < k and cur is not null
    * count is < k then return head
    * else call reverse first k nodes and store the node as revHead.
    * make head.next as recurse of cur, k
    * return revHead
    time & space:
    * it takes n space in worst case for recursion stack and takes n time

    Approach 2: iterative approach
    * intuition is same as recursion, but instead of recursion we use iteration to link the reversed lists
    algo:
    * init revHead,newHead and kTail as null
    * iterate until head is not null
    * make cur=head, int it 0 and iterate until cur is not null i<k
    * if i<k then make revHead = hehad else revHead as reversefirstN head,k
    * if newHead is null then set revHead as new head this is the answer
    * if kTail is not null then asetkTail.next as revHead
    * make kTail = head and head=cur
     */
     class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        return reverseKGroupA2(head,k);
    }

    public ListNode reverseKGroupA2(ListNode head, int k){
        ListNode revHead=null, newHead=null,kTail=null;
        while(head != null){
            ListNode cur = head;
            int i;
            for( i=0; i<k && cur !=null; i++)
                cur = cur.next;
            if(i<k) revHead = head;
            else revHead = reverseItr(head,k);
            if(newHead==null)
                newHead = revHead;
            if(kTail != null)
                kTail.next = revHead;
            kTail = head;
            head = cur;
        }
        return newHead;
    }

    public ListNode reverseKGroupA1(ListNode head, int k){
        ListNode cur = head;
        int i;
        for(i=0; i<k && cur != null; i++)
            cur = cur.next;
        if(i < k) return head;
        ListNode prev = reverseItr(head,k);
        head.next = reverseKGroupA1(cur,k);
        return prev;
    }

    public ListNode reverseItr(ListNode head, int k){
        ListNode prev = null, cur = head;
        while(k>0){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            k--;
        }
        return prev;
    }
}
