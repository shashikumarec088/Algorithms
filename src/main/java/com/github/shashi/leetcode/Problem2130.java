package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem2130 {
    /*
    2130. Maximum Twin Sum of a Linked List

    In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the
    twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

    For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only
    nodes with twins for n = 4.

    The twin sum is defined as the sum of a node and its twin.

    Given the head of a linked list with even length, return the maximum twin sum of the linked list.


    Example 1:


    Input: head = [5,4,2,1]
    Output: 6
    Explanation:
    Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
    There are no other nodes with twins in the linked list.
    Thus, the maximum twin sum of the linked list is 6.
    Example 2:

    Input: head = [4,2,2,3]
    Output: 7
    Explanation:
    The nodes with twins present in this linked list are:
    - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
    - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
    Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
    Example 3:


    Input: head = [1,100000]
    Output: 100001
    Explanation:
    There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.

    Constraints:

    The number of nodes in the list is an even integer in the range [2, 105].
    1 <= Node.val <= 105

            approach1: using storing values in arraylist
        * intuition is to store the values in arraylist and then use
        the 2 pointers to find the max
        algo:
        * init arraylist al
        * iterate until head is not null
            * add value to list
            * head = head.next
        * init i=0, j=n-1, max = 0
        * iterate until i<j
            * max = max(max,list.get(i)+list.get(j))
            * i++, j--
        * return max

        time & space:
        * it takes n time and n space

        approach 2: using stack
        * intuition is to push the first half of the nodes to stack
        * pop as we proceed to second half
        algo:
        * init slow=null, fast = head, max=0, stack<ListNodes>
        * iterate until fast is not null and fast.next is not null
            * if slow is not null push to stack
            * slow = if null then head else slow.next
            * fast = fast.next.next
        * slow = slow.next
        * iterate until slow is not null
            * max = max(slow.val+stack.pop().val,max)
            slow = slow.next
        return max

        time & space;
        * it takes n time and n space

        approach 3: reversing the second half
        * intuition is to reverse the second half of the list
        and iterate from the last
        algo:
        * init slow=null, fast = head
        * iterate until fast is not null and fast.next is not null
            * slow = slow.next if not null else head
            * fast = fast.next.next
        * fast = slow.next
        * slow.next = null
        * slow = head
        * prev = null
        * iterate until fast is not null
            * next = fast.next
            * fast.next.next = fast
            * fast.next = null
            * prev = fast
            * fast = next
        * iterate until prev is not null
            max = max(max,prev.val+slow.val)
            prev = prev.next, fast = fast.next
        * return max

        time & space:
        * it takes n time and const space
     */

    public int pairSum(ListNode head) {
        return pairSumA2(head);
    }

    public int pairSumA1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head !=null){
            list.add(head.val);
            head = head.next;
        }
        int max = 0,i=0,j=list.size()-1;
        while(i<j)
            max = Math.max(max,list.get(i++)+list.get(j--));
        return max;
    }

    public int pairSumA2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode slow = null, fast = head;
        int max=0;
        while(fast !=null && fast.next !=null){
            slow = slow == null?head:slow.next;
            fast = fast.next.next;
            stack.push(slow);
        }
        slow = slow.next;
        while(!stack.isEmpty()){
            max = Math.max(max,slow.val+stack.pop().val);
            slow = slow.next;
        }
        return max;
    }

    public int pairSumA3(ListNode head) {
        ListNode slow = null, fast = head, prev=null;
        int max=0;
        while(fast !=null && fast.next !=null){
            slow = slow == null?head:slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        slow = head;

        while(fast!=null){
            ListNode next = fast.next;
            fast.next = prev;
            prev = fast;
            fast = next;
        }
        while(prev !=null){
            max = Math.max(max,slow.val+prev.val);
            prev = prev.next;
            slow = slow.next;
        }
        return max;
    }
}
