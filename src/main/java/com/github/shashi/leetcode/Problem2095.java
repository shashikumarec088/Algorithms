package com.github.shashi.leetcode;

public class Problem2095 {
    /*
    2095. Delete the Middle Node of a Linked List

    You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

    The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing,
    where ⌊x⌋ denotes the largest integer less than or equal to x.

    For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.

    Example 1:


    Input: head = [1,3,4,7,1,2,6]
    Output: [1,3,4,1,2,6]
    Explanation:
    The above figure represents the given linked list. The indices of the nodes are written below.
    Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
    We return the new list after removing this node.
    Example 2:


    Input: head = [1,2,3,4]
    Output: [1,2,4]
    Explanation:
    The above figure represents the given linked list.
    For n = 4, node 2 with value 3 is the middle node, which is marked in red.
    Example 3:


    Input: head = [2,1]
    Output: [2]
    Explanation:
    The above figure represents the given linked list.
    For n = 2, node 1 with value 1 is the middle node, which is marked in red.
    Node 0 with value 2 is the only node remaining after removing node 1.


    Constraints:

    The number of nodes in the list is in the range [1, 105].
    1 <= Node.val <= 105

    Approach 1:
    * intuition is to use the 2 pass approach where we determine the length and iterate until n/2 -1 and
    delete the node
    algo:
    * init p1 = head and count=0
    * base case check ig head is null or head.next is null then return null
    * iterate p1 until null
        * increment count
        * make p1 = p1.next
    * make p1 = head and count = (count/2)-1
    * iterate p1 until count>0
        * make p1 = p1.next
        * decrement count
    * make p1.next = p1.next.next
    * return head
    time & space:
    * it takes n time and constant space

    Approach 2: One pass with slow and fast pointers
    * intuition is to use 2 pointers and move fast pointer 2 steps and iterate until it is null
    and delete the slow pointer next node
    algo:
    * init slow = null and fast = head
    * base case check ig head is null or head.next is null then return null
    * iterate fast until it is null and fast.next is null
        * make slow = slow==null? head:slow.next
        * make fast = fast.next.next
    * make slow.next = slow.next !=null?slow.next.next:null
    * return head
    time & space:
    * it takes n time and constant space


     */

    public ListNode deleteMiddle(ListNode head) {
        return deleteMiddleA1(head);
    }


    public ListNode deleteMiddleA1(ListNode head) {
        if(head==null || head.next==null)return null;
        ListNode p1 = head;
        int count = 0;
        while(p1!=null){
            count++;
            p1 = p1.next;
        }
        p1 = head;
        count = (count/2)-1;
        while(count>0){
            p1 = p1.next;
            count--;
        }
        p1.next = p1.next.next;
        return head;
    }

    public ListNode deleteMiddleA2(ListNode head) {
        if(head==null || head.next==null)return null;
        ListNode slow = null, fast = head;
        while(fast !=null && fast.next != null){
            slow = slow==null? head:slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next !=null?slow.next.next:null;
        return head;
    }
}
