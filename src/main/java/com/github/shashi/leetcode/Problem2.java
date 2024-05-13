package com.github.shashi.leetcode;

public class Problem2 {
    /*
    Add Two Numbers
    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
    and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example 1:
    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.
    Example 2:
    Input: l1 = [0], l2 = [0]
    Output: [0]
    Example 3:
    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    Output: [8,9,9,9,0,0,0,1]
    Constraints:
    The number of nodes in each linked list is in the range [1, 100].
    0 <= Node.val <= 9
    It is guaranteed that the list represents a number that does not have leading zeros.

    Approach 1:
    * intuition is to iterate over both the lists at the same time along with carry and keep building the
    sum List, remember to add the carry to result and also at the end if carry>0 add extra node with carry.
    algo:
    * create the ListNode prev which is to hold the head of the sum list, have cur = prev, this is to add the
    node at each node of l1 and l2 and move to the next node.
    * iterate over l1, l2 or carry >0 sum l1, l2 and carry  and divide the sum /10 to get the next carry
    and sum = sum %10 to get the value of node at current position
    * make l1 = l1.next and l2 = l2.next, then proceed with the next iteration
    * return prev.next at the end
    time & space:
    * time is o(n) and space is const

    Approach 2:
    * intuition is to do the same as approach 1 using recursion, where we add the l1, l2, carry and add the
    new node with the sum % 10 and carry = sum / 10, then we call the rec with l1.next, l2.next, with base
    condition as l1==null && l2==null && carry==0
    algo:
    * create prev node, make cur = prev, call rec(l1,l2,cur,0)
    * in recursion check the base case l1==null && l2==null && carry ==0 then return
    * create sum = l1.val + l2.val + carry, carry = sum / 10; sum = sum % 10;
    * create the node and make it as cur.next, make cur = cur.next
    * call rec with l1.next, l2.next
    time & space:
    * time o(n) and space is 0(n) for recursion stack

     */
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

    public ListNode addTwoNumbersA2(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode();
        ListNode cur= prev;
        rec( l1, l2, cur, 0);
        return prev.next;
    }

    public void rec(ListNode l1, ListNode l2, ListNode cur, int carry){
        if(l1==null && l2==null && carry==0)return;
        int sum = (l1!=null?l1.val:0) + (l2!=null?l2.val:0) + carry;
        carry = sum / 10;
        sum = sum % 10;
        cur.next = new ListNode(sum);
        rec(l1!=null?l1.next:l1,l2!=null?l2.next:l2,cur.next,carry);
    }

    public ListNode addTwoNumbersA1(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode();
        ListNode cur= prev;
        int carry = 0;
        while(l1!=null || l2 != null || carry>0){
            int sum = l1!=null?l1.val:0;
            sum += l2!=null?l2.val:0;
            sum += carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l1 = l1!=null?l1.next:null;
            l2 = l2!=null?l2.next:null;
        }
        return prev.next;
    }

}
