package com.github.shashi.leetcode;
import java.util.*;
public class Problem155 {
    class MinStack{
        Stack<int[]> stack = new Stack<>();
        Stack<Integer> stackVal = new Stack<>();
        Stack<Integer> stackMin = new Stack<>();
        Stack<int[]> stackMinF = new Stack<>();

        public MinStack() {

        }

        public void push(int val) {
            int min =!stackMinF.isEmpty()? Math.min(val, stackMinF.peek()[1]):val;
            stackVal.push(val);
            if(stackMinF.size() > 0 && min == stackMinF.peek()[1])stackMinF.peek()[0]++;
            else stackMinF.push(new int[]{1,min});
        }
        public void push2(int val) {
            int min =!stackMin.isEmpty()? Math.min(val, stackMin.peek()):val;
            stackVal.push(val);
            stackMin.push(min);
        }

        public void push1(int val) {
            int min =!stack.isEmpty()? Math.min(val, stack.peek()[1]):val;
            stack.push(new int[]{val,min});
        }

        public void pop() {
            stackVal.pop();
            stackMinF.peek()[0]--;
            if(stackMinF.peek()[0]==0)stackMinF.pop();
        }

        public void pop2() {
            stackVal.pop();
            stackMin.pop();
        }

        public void pop1() {
            stack.pop();
        }

        public int top() {
            return stackVal.peek();
        }

        public int top2() {
            return stackVal.peek();
        }

        public int top1() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stackMinF.peek()[1];
        }

        public int getMin2() {
            return stackMin.peek();
        }

        public int getMin1() {
            return stack.peek()[1];
        }
    }
}
