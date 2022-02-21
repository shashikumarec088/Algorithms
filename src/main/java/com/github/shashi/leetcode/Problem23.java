package com.github.shashi.leetcode;
import java.util.*;
public class Problem23 {
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

    public ListNode mergeKListsA3(ListNode[] lists){
        if(lists.length==0) return null;
        int l = lists.length;
        while(l>1){
            for(int i=0; i<l/2;i++)
                lists[i] = merge2Lists(lists[i*2],lists[i*2+1]);
            if(l%2==1)
                lists[l/2] = lists[l-1];
            l = (l+1)/2;
        }
        return lists[0];
    }

    public ListNode mergeKListsA2(ListNode[] lists){
        if(lists.length==0)return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(ListNode list: lists)
            if(list != null)pq.add(list);
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if(node.next != null)
                pq.add(node.next);
        }
        return dummy.next;
    }

    public ListNode mergeKListsA1(ListNode[] lists){
        ListNode result = null;
        if(lists.length ==0)return result;
        result = lists[0];
        for(int i=1; i<lists.length;i++){
            ListNode l2 = lists[i];
            result = merge2Lists(result,l2);
        }
        return result;
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1!= null)cur.next = l1;
        if(l2 != null)cur.next = l2;
        return dummy.next;
    }
}
