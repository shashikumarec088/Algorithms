package com.github.shashi.leetcode;

public class Problem86 {
    /*
    Partition List
    Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater
    than or equal to x.
    You should preserve the original relative order of the nodes in each of the two partitions.

    Example 1:
    Input: head = [1,4,3,2,5,2], x = 3
    Output: [1,2,2,4,3,5]
    Example 2:
    Input: head = [2,1], x = 2
    Output: [1,2]
    Constraints:
    The number of nodes in the list is in the range [0, 200].
    -100 <= Node.val <= 100
    -200 <= x <= 200

    Approach 1:
    * intuition is to form the sublists less than x and greater than x and join them and return the header
    algo:
    * create 2 dummy nodes and another 2 variable before with value dummy1 and after with value dummy2
    * iterate until the head is empty, if value head is < x then make before.next to head and move before to before.next
    * if the value of head >= x then make after.next = head and make after = after.next
    * move the head forward to head.next
    * link the before.next to dummy2.next and make after.next = null as this is the end node of both the lists
    else we will get the cycle
    * return the dummy1.next
    time & space:
    * take n time and constant space as we are using the nodes in the input

    read recursive solution later https://leetcode.com/problems/partition-list/solutions/349660/simple-recursive-solution-runtime-and-memory-both-100-with-detailed-explanations/?envType=study-plan-v2&envId=top-interview-150
     */

    public ListNode partition(ListNode head, int x) {
        return partitionA1(head,x);
    }

    public ListNode partitionA1(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-2);
        ListNode before = dummy1, after = dummy2;
        while(head != null){
            if(head.val < x){
                before.next = head;
                before = before.next;
            }else{
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        before.next = dummy2.next;
        return dummy1.next;

    }
}
