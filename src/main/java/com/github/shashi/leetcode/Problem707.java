package com.github.shashi.leetcode;
public class Problem707 {
    class Node{
        int val;
        Node next;
        Node(int val){
            this.val=val;
        }
    }

    Node head, tail;
    int size;

    public Problem707() {

    }

    public static void main(String[] args) {
        Problem707 obj = new Problem707();
        String[] actions ={"addAtHead","addAtTail","addAtTail","get","get","addAtTail","addAtIndex","addAtHead","addAtHead","addAtTail","addAtTail","addAtTail","addAtTail","get","addAtHead","addAtHead","addAtIndex","addAtIndex","addAtHead","addAtTail","deleteAtIndex","addAtHead","addAtHead","addAtIndex","addAtTail","get","addAtIndex","addAtTail","addAtHead","addAtHead","addAtIndex","addAtTail","addAtHead","addAtHead","get","deleteAtIndex","addAtTail","addAtTail","addAtHead","addAtTail","get","deleteAtIndex","addAtTail","addAtHead","addAtTail","deleteAtIndex","addAtTail","deleteAtIndex","addAtIndex","deleteAtIndex","addAtTail","addAtHead","addAtIndex","addAtHead","addAtHead","get","addAtHead","get","addAtHead","deleteAtIndex","get","addAtHead","addAtTail","get","addAtHead","get","addAtTail","get","addAtTail","addAtHead","addAtIndex","addAtIndex","addAtHead","addAtHead","deleteAtIndex","get","addAtHead","addAtIndex","addAtTail","get","addAtIndex","get","addAtIndex","get","addAtIndex","addAtIndex","addAtHead","addAtHead","addAtTail","addAtIndex","get","addAtHead","addAtTail","addAtTail","addAtHead","get","addAtTail","addAtHead","addAtTail","get","addAtIndex"};
        int[][] items = {{84},{2},{39},{3},{1},{42},{1,80},{14},{1},{53},{98},{19},{12},{2},{16},{33},{4,17},{6,8},{37},{43},{11},{80},{31},{13,23},{17},{4},{10,0},{21},{73},{22},{24,37},{14},{97},{8},{6},{17},{50},{28},{76},{79},{18},{30},{5},{9},{83},{3},{40},{26},{20,90},{30},{40},{56},{15,23},{51},{21},{26},{83},{30},{12},{8},{4},{20},{45},{10},{56},{18},{33},{2},{70},{57},{31,24},{16,92},{40},{23},{26},{1},{92},{3,78},{42},{18},{39,9},{13},{33,17},{51},{18,95},{18,33},{80},{21},{7},{17,46},{33},{60},{26},{4},{9},{45},{38},{95},{78},{54},{42,86}};
        for(int i = 0; i<actions.length;i++){
            int[] item = items[i];
            String action = actions[i];
            if(action.equals("addAtHead"))
                obj.addAtHead(item[0]);
            if(action.equals("addAtTail"))
                obj.addAtTail(item[0]);
            if(action.equals("get"))
                obj.get(item[0]);
            if(action.equals("addAtIndex"))
                obj.addAtIndex(item[0],item[1]);
            if(action.equals("deleteAtIndex"))
                obj.deleteAtIndex(item[0]);
        }

    }

    public int get(int index) {
        int value =-1,ind=0;
        if(index>=0 && index < size){
            Node temp = head;
            while(ind <index){
                temp = temp.next;
                ind++;
            }
            value = temp.val;
        }
        return value;
    }

    public void addAtHead(int val) {
        Node cur = new Node(val);
        if(size==0){
            head = cur;
            tail = head;
        }else{
            cur.next = head;
            head = cur;
        }
        size++;

    }

    public void addAtTail(int val) {
        Node cur = new Node(val);
        if(size==0){
            head = cur;
            tail = head;
        }else{
            tail.next = cur;
            tail = cur;
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size) return;
        if(index== 0){
            addAtHead(val);
            return;
        }
        if(index==size){
            addAtTail(val);
            return;
        }
        int i=0;
        Node prev = head;
        while(i<index-1){
            prev = prev.next;
            i++;
        }
        Node cur = new Node(val);
        cur.next = prev.next;
        prev.next = cur;
        size++;
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size)return;
        if(size==1){
            head= null;
            tail=null;
            size--;
            return;
        }
        if(index==0){
            head = head.next;
            size--;
            return;
        }
        int i=0;
        Node prev = head;
        while(i<index-1){
            prev = prev.next;
            i++;
        }
        Node cur = prev.next;
        prev.next = cur.next;
        size--;
    }
}