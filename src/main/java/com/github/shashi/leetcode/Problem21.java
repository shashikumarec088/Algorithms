package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem21 {
    /*
    Merge Two Sorted Lists
    You are given the heads of two sorted linked lists list1 and list2.

    Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
    Return the head of the merged l
    Input: list1 = [1,2,4], list2 = [1,3,4]
    Output: [1,1,2,3,4,4]
    Example 2:
    Input: list1 = [], list2 = []
    Output: []
    Example 3:
    Input: list1 = [], list2 = [0]
    Output: [0]
    Constraints:
    The number of nodes in both lists is in the range [0, 50].
    -100 <= Node.val <= 100
    Both list1 and list2 are sorted in non-decreasing order.

    Approach 1:
    * intuition is to iterate over both the list heads and consider the node with smaller value to merge
    algo:
    * initialize prev = listnode and cur as prev, iterate until l1 and l2 not null
    * for each iteration if l1 <= l2 list l1 to cur.next and make cur as cur.next else link l2 and make cur as cur.next
    * link the remaining part of l1 if not null and l2 if not null
    time & space:
    * takes n time and const space;

    Approach 2:
    * intuition is to consider the smallest node and recursively merging the remaining part of lists
    algo:
    * base case is to check if l1 is null if so return l2, if l2 is null return l1
    * if l1 <= l2 then make l1.next = rec(l1.next,l2) and return l1;
    * else make l2.next = rec(l1,l2.next) and return l2
    time & space:
    * n time and n space for recursion
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static void main(String[] args) {
        Problem21 problem119 = new Problem21();
        System.out.println(problem119.mergeTwoLists(null,null));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return  mergeTwoListsA2(list1,list2);
    }

    public ListNode mergeTwoListsA2(ListNode l1, ListNode l2) {
        if(l1==null)return l2;
        if(l2==null)return l1;
        if(l1.val <= l2.val){
            l1.next = mergeTwoListsA2(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoListsA2(l1,l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoListsA1(ListNode list1, ListNode list2) {
        ListNode prev = new ListNode();
        ListNode cur = prev;
        while(list1!=null && list2 != null){
            if(list1.val<=list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if(list1!=null)cur.next=list1;
        if(list2!=null)cur.next=list2;
        return prev.next;
    }
}
