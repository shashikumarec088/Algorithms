package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.List;

public class Problem148 {
    /*
    Sort List
    Given the head of a linked list, return the list after sorting it in ascending order.
    Example 1:
    Input: head = [4,2,1,3]
    Output: [1,2,3,4]
    Example 2:
    Input: head = [-1,5,3,4,0]
    Output: [-1,0,3,4,5]
    Example 3:
    Input: head = []
    Output: []
    Constraints:
    The number of nodes in the list is in the range [0, 5 * 104].
    -105 <= Node.val <= 105
    Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

    Approach 1: top down mergesort
    * intuition is to keep splitting the list into half until empty or 1 element is left and merging the lists
    in sorted manner to get the final sorted list
    algo:
    * we use the mergesort recursion approach to get the sorted list, in the mergesort method we check if head is
    null or does not have next if so we return the head
    * we find out the mid of the list by passing head to the getMid method which delinks the left and right half
    of the list and return the head of right list
    * we recursively call mergesort with head which is start of left list and with mid which is start of right sublist
    * we call the merge on the sorted left and right subtree
    * in getMid method we initialize prev to null and fast to head, we iterate until fast is null
    * at each iteration if prev is null then we set head as prev else prev.next as prev and we make fast as fast.next
    .next which moves 2 steps, we get the tail of the left half
    * we take the next element of prev which is the head of right list and we delink left and right half making
    prev.next to null
    * we return fast which holds prev.next.
    time & space:
    * it takes nlogn time and logn space for recursion

    Approach 2: bottom up merge sort iterative solution with constant space
    reference : https://leetcode.com/problems/sort-list/solutions/46712/bottom-to-up-not-recurring-with-o-1-space-complextity-and-o-nlgn-time-complextity/?envType=study-plan-v2&envId=top-interview-150
    comment section
    * intuition is to split the list in steps and at each step merge the step size lists and increase the
    step size by multiples of 2 until we reach list length.
    algo:
    * initialize dummy and link next to head, find length of list
    * iterate step from 1 to n with step size of step*2 after each iteration
    * in each iteration, make prev = dummy and cur = prev.next
    * iterate until cur is null, at each step, make left = cur, find right by calling split with cur and step,
    find cur by calling split with right and step
    * call merge with left,right,prev and assign the returned tail to prev
    * return dummy.next at the end
    * in split method we take head and split size n, if head is null we return null
    * iterate from i=1 to n and also we check if head.next is not null in the loop condition we make head=head.next;
    we store head.next in variable and make head.next=null and return next
    * in merge method we do the normal merge of two sorted lists we use the prev provided in input, in the
    end we iterate prev until prev.next is not null and return prev, this is used to link the next set of nodes
    in sort method.
    time & space:
    * it takes nlogn time and takes constant space
     */

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        return sortListA2(head);
    }

    public ListNode sortListA2(ListNode head) {
        if(head==null || head.next==null)return head;
        int n=0;
        ListNode cur = head, dummy = new ListNode(1,head);
        while(cur!=null){
            n++;
            cur = cur.next;
        }

        for(int step=1; step<n; step=step*2){
            ListNode prev = dummy;
            cur = prev.next;
            while(cur!=null){
                ListNode left = cur;
                ListNode right = split(cur,step);
                cur = split(right,step);
                prev = merge(left,right,prev);
            }
        }
        return dummy.next;
    }

    public ListNode split(ListNode cur, int n){
        if(cur==null)return null;
        for(int i=1; i<n && cur.next!=null;i++)
            cur = cur.next;
        ListNode next = cur.next;
        cur.next=null;
        return next;
    }

    public ListNode merge(ListNode l1, ListNode l2, ListNode prev){
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else{
                prev.next=l2;
                l2 = l2.next;
            }
            prev=prev.next;
        }
        if(l1!=null)prev.next=l1;
        else prev.next = l2;
        while(prev.next!=null)
            prev=prev.next;
        return prev;
    }

    public ListNode sortListA1(ListNode head) {
        if(head==null || head.next==null)return head;
        ListNode mid = getMid(head);
        ListNode left = sortListA1(head);
        ListNode right = sortListA1(mid);
        return merge(left,right);
    }

    public ListNode getMid(ListNode head){
        ListNode prev=null, fast=head;
        while(fast!=null){
            prev = prev==null?head : prev.next;
            fast = fast.next!=null?fast.next.next:null;
        }
        fast = prev.next;
        prev.next=null;
        return fast;
    }

    public ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(1);
        ListNode prev = dummy;
        while(l1!=null && l2 != null){
            if(l1.val<l2.val){
                prev.next = l1;
                l1=l1.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
            }
            prev=prev.next;
        }
        if(l1!=null)
            prev.next=l1;
        if(l2!=null)
            prev.next=l2;
        return dummy.next;
    }
}
