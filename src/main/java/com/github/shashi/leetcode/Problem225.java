package com.github.shashi.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem225 {
    class MyStack {
        Queue<Integer> queue1, queue2;
        int peek;
        public void MyStack1() {
            queue1 = new ArrayDeque<>();
            queue2 = new ArrayDeque<>();
        }

        public MyStack() {
            queue1 = new ArrayDeque<>();
        }

        public void push(int x) {
            queue1.offer(x);
            int size = queue1.size();
            while(size>1){
                queue1.add(queue1.remove());
                size--;
            }
        }

        public void push1(int x) {
            peek=x;
            queue1.offer(x);
        }

        public int pop() {
            return queue1.remove();
        }

        public int pop1() {
            while(queue1.size()>1){
                peek = queue1.poll();
                queue2.offer(peek);
            }
            int val =  queue1.poll();
            Queue<Integer> temp = queue2;
            queue2 = queue1;
            queue1 = temp;
            return val;
        }

        public int top() {
            return queue1.peek();
        }

        public int top1() {
            return queue1.size()==1 ? queue1.peek():peek;
        }

        public boolean empty() {
            return queue1.isEmpty();
        }

        public boolean empty1() {
            return queue2.isEmpty() && queue1.isEmpty();
        }
    }
}
