package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

class ListNode {
    /*
    Linked List Cycle
    Given head, the head of a linked list, determine if the linked list has a cycle in it.

    There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
    following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
    connected to. Note that pos is not passed as a parameter.

    Return true if there is a cycle in the linked list. Otherwise, return false.

    Example 1:
    Input: head = [3,2,0,-4], pos = 1
    Output: true
    Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
    Example 2:
    Input: head = [1,2], pos = 0
    Output: true
    Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
    Example 3:
    Input: head = [1], pos = -1
    Output: false
    Explanation: There is no cycle in the linked list.


    Constraints:
    The number of the nodes in the list is in the range [0, 104].
    -105 <= Node.val <= 105
    pos is -1 or a valid index in the linked-list.

    Follow up: Can you solve it using O(1) (i.e. constant) memory?

    Approach 1:
    * intuition is if there is a cycle then we end up visiting the same node which can be detected by storing the
    values in set
    algo:
    * create the set of type Listnode, iterate until head is in set or head is null
    * at each iteration add head to set and make head = head.next
    * return true if head is not null else false;
    time & space:
    * n time and n space

    Approach 2:
    * intuition is to use the floyds cycle detection algorithm which states that if the list has cycle and if
    two pointers move at different speed,then they end up meeting at some point else the fast pointer reaches
    end first
    algo:
    * have slow=head and fast = head.next, iterate over the fast until it is not empty and not equal to slow
    * at each iteration make slow = slow.next and fast = fast.next!=null?fast.next.next:null;
    * return true if fast is not null and false if fast is null
    time & space:
    * n time and 1 space

     */
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class Problem141 {
    public boolean hasCycle(ListNode head) {
        return hasCycleA2(head);
    }

    public boolean hasCycleA1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head !=null && !set.contains(head)){
            set.add(head);
            head = head.next;
        }
        return head !=null;
    }

    public boolean hasCycleA2(ListNode head) {
        ListNode slow = head, fast = head!=null?head.next:head;
        while(fast != null && slow !=fast){
            slow = slow.next;
            fast = fast.next !=null?fast.next.next:null;
        }
        return fast !=null;
    }
}