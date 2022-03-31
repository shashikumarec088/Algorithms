package com.github.shashi.leetcode;
import java.util.*;
public class Problem155 {
    class MinStack{
        Stack<Integer> stack;
        Stack<int[]> minStack;

        MinStack(){
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val){
            if(stack.isEmpty() || minStack.peek()[0]>val)
                minStack.push(new int[]{val,1});
            else minStack.peek()[1]++;
            stack.push(val);
        }

        public void pop(){
            if(minStack.peek()[1]==1)
                minStack.pop();
            else minStack.peek()[1]--;
            stack.pop();
        }

        public int top(){
            return stack.peek();
        }

        public int getMin(){
            return minStack.peek()[0];
        }
    }


    class MinStack2 {
        Stack <int[]> stack;

        public MinStack2() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if(stack.isEmpty())
                stack.push(new int[]{val,val});
            else stack.push(new int[]{val,Math.min(stack.peek()[1],val)});
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }
}
