package com.github.shashi.leetcode;

public class Problem92 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        return reverseBetweenA1(head,left,right);
    }

    ListNode successor=null;

    /*
     * intuition behind this approach is to traverse the head till
     * the start of the left position and call the normal list reverse function
     * and reverse the head at the end
     */
    public ListNode reverseBetweenA1(ListNode head, int left, int right) {
        if(left==1){
            return rec(head,right);
        }
        head.next = reverseBetweenA1(head.next,left-1,right-1);
        return head;
    }
    /*
     * intuition behind the below recursion is we move till the right
     * position and start reversing the list from their, one difference
     * is we store the next pointer in global variable and use it to link
     * the tail of the reversed list, this to establish the link to the remaining
     * part of the list
     */
    public ListNode rec(ListNode head, int r){
        if(r==1){
            // update the successor and return head;
            successor=head.next;
            return head;
        }
        ListNode last = rec(head.next,r-1);
        head.next.next=head;
        head.next=successor;
        return last;
    }
    /*
     * intuition behind the below approach is to move the cur till the left
     * position, then have the prev, cur positions stored for linking later
     * reverse the remaining portion till the right position, then establish
     * the linked properly this is one pas solution
     */
    public ListNode reverseBetweenA2(ListNode head, int left, int right) {
        ListNode prev = null, cur=head;
        if(head==null || head.next==null || left==right)return head;
        int pos=1;
        while(cur!=null && pos <left){
            prev=cur;
            cur=cur.next;
            pos++;
        }
        ListNode prev1 = prev, prev2 = cur;
        prev=null;
        while(cur!=null && pos<=right){
            ListNode next = cur.next;
            cur.next = prev;
            prev=cur;
            cur=next;
            pos++;
        }
        if(prev2!=null)
            prev2.next = cur;
        if(prev1!=null)prev1.next = prev;
        else return prev;
        return head;
    }
}