package com.github.shashi.leetcode;
import java.util.*;
public class Problem23 {
    /*
    Merge k Sorted Lists
    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
    Merge all the linked-lists into one sorted linked-list and return it.

    Example 1:
    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    merging them into one sorted list:
    1->1->2->3->4->4->5->6
    Example 2:
    Input: lists = []
    Output: []
    Example 3:
    Input: lists = [[]]
    Output: []

    Constraints:
    k == lists.length
    0 <= k <= 104
    0 <= lists[i].length <= 500
    -104 <= lists[i][j] <= 104
    lists[i] is sorted in ascending order.
    The sum of lists[i].length will not exceed 104.

    Approach 1: brute force approach
    * intuition is to add all values from all lists to list and sort the list and construct the new linkedList.
    it does not uses the sorted nature of each lists.
    algo:
    * create new arrayList of type integer
    * iterate over each list and iterate over all elements in list and add values to arrayList
    * sort the list using Collections.sort method
    * create dummy node, make prev = dummy, iterate over the list
    * create a node for each value and link prev.next to node and make prev = prev.next
    * return dummy.next
    time & space:
    * where N is the total nodes in all lists, then it takes NlogN time for sorting and takes N space.

    Approach 2: using Priority queue
    * intuition is to add the heads of all lists to priority queue pq and take the small node each time link it to
    prev node and if node has next then add it back to pq, repeat until pq is empty
    algo:
    * initialize priority queue pq of type treeNode, iterate over all lists and add each list to queue
    * initialize the dummy node and make prev = dummy.
    * iterate until pq is empty, for each node make prev.next = node, if node has next then add next to pq,
    make prev=prev.next;
    * return dummy.next at the end
    time & space:
    * it takes  Nlog k time where k is list size, we poll all lements once from pq which is N times so overall
    time complexity of N log k. it takes k space for priority queue for storing heads

    Approach 3: Divide & conquer
     * intuition is we can merge the lists in steps of k/2, k/4, k/8 in over all log k steps until
     the list becomes 1 size.
     algo:
     * initialize l = lists.length
     * iterate until l > 1
     * for reach iteration of l iterate i from 0 to < l/2
     * at each iteration merge two lists at positions 2*i, 2*i+1 and store the result list at i, here we are
     multiply by 2 because we merge 2 lists at positions like 0,1 or 2,4 etc.
     * we need to handle the case when tere are odd number of lists, in that case we copy the list at l-1 position
     to l/2 position.
     * remember to make l as (l+1)/2 here we are considering l+1 to handle odd lists case.
     * return list[0] at the end
     * in merge 2 lists we compare the heads and link whichever is lower and make to merged list forward
     time & space:
     * it takes N logk as there will be k levels and we take N time for each level so total is N log N
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKListsA3(lists);
    }

    public ListNode mergeKListsA3(ListNode[] lists) {
        int l = lists.length;
        if(l==0)return null;
        while(l>1){
            for(int i=0; i<l/2;i++)
                lists[i] = merge2Lists(lists[2*i],lists[2*i+1]);
            if(l%2==1)lists[l/2]= lists[l-1];
            l=(l+1)/2;
        }
        return lists[0];
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(1);
        ListNode prev = dummy;
        while(l1!=null && l2!=null){
            if(l1.val <l2.val){
                prev.next=l1;
                l1=l1.next;
            }else{
                prev.next=l2;
                l2 = l2.next;
            }
            prev=prev.next;
        }
        prev.next = l1!=null?l1:l2;
        return dummy.next;
    }

    public ListNode mergeKListsA2(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a,b)->a.val-b.val
        );
        for(ListNode list : lists){
            if(list!=null)
                pq.offer(list);
        }
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            prev.next = node;
            if(node.next!=null)pq.offer(node.next);
            prev=prev.next;
        }
        return dummy.next;
    }

    public ListNode mergeKListsA1(ListNode[] lists) {
        List<Integer> listArray = new ArrayList<>();
        for(ListNode list : lists){
            while(list!=null){
                listArray.add(list.val);
                list = list.next;
            }
        }
        Collections.sort(listArray);
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        for(int num : listArray){
            prev.next = new ListNode(num);
            prev = prev.next;
        }
        return dummy.next;
    }
}
