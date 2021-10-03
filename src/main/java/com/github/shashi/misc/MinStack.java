package com.github.shashi.misc;

import java.util.ArrayList;
import java.util.Stack;

public class MinStack {

    public static void main(String[] args) {
        MinStack mn = new MinStack();
        mn.push(-2);
        mn.push(0);
        mn.push(-3);
        int min = mn.getMin();
        mn.pop();
        int top = mn.top();

    }

    /** initialize your data structure here. */
    private Stack<Integer> stack;
    private Integer min;
    public MinStack() {
        this.stack = new Stack<>();
        this.min = 0;
    }

    public void push(int val) {
        if(stack.empty())
            this.min = val;
        else{
            if(val < this.min)
                this.min = val;
        }
        stack.push(val);
    }

    public void pop() {
        Integer element = stack.pop();
        if(element.equals(min)){
            min = stack.peek();
            findMin();
        }
    }

    private void findMin(){
        ArrayList<Integer> queue = new ArrayList<>();

        while(!stack.empty()){
            Integer popElement = stack.pop();
            if(popElement < min)
                min = popElement;
            queue.add(popElement);
        }
        for(int i = queue.size() - 1; i >-1; i--){
            stack.push(queue.get(i));
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */