package com.github.shashi.leetcode;
import java.util.*;
public class Problem155 {
    /*
    Min Stack
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    Implement the MinStack class:

    MinStack() initializes the stack object.
    void push(int val) pushes the element val onto the stack.
    void pop() removes the element on the top of the stack.
    int top() gets the top element of the stack.
    int getMin() retrieves the minimum element in the stack.
    You must implement a solution with O(1) time complexity for each function.

    Example 1:
    Input
    ["MinStack","push","push","push","getMin","pop","top","getMin"]
    [[],[-2],[0],[-3],[],[],[],[]]
    Output
    [null,null,null,null,-3,null,0,-2]

    Explanation
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin(); // return -3
    minStack.pop();
    minStack.top();    // return 0
    minStack.getMin(); // return -2

    Constraints:
    -231 <= val <= 231 - 1
    Methods pop, top and getMin operations will always be called on non-empty stacks.
    At most 3 * 104 calls will be made to push, pop, top, and getMin.

    Approach 1:
    * intuition is to maintain the minvalue for each element along with the value in stack
    algo:
    create a stack of type int array, in constructor initialize the stack
    * in push keep the cur value as min and if stack is not empty take the min of cur and the min value in the stop of
    the stack and push cur,min to stack
    * in top take the peak element return 1st element from array
    * in getMin take the peak element and return the 0th element
    time & space:
    space is 0(2n)

    Approach 2:
    * intuition is instead of storing the min for each value we can use extra stack and store the min and its
    frequency and keep updating the frequency when same min and add new min if it changes
    algo:
    * create 2 stacks 1 for actual values which is of type integer and 2nd is of type int array which stores the
    min value and its occurences
    * in put if size is 0 or minstack top element is > current element then add min,1 to freqStack, add the element
    to stack, else increment minstack top element frequency
    * in pop, check if min element on top has freq 1 if so pop it else reduce the freq
    * in getMin return the top element in the freq stack
    time & space:
    space needed in worst case 2 n in best case < 2n
     */
    class MinStackA2 {
        Stack<int[]> freqStack;
        Stack<Integer> stack;
        public MinStackA2(){
            stack = new Stack<>();
            freqStack = new Stack<>();
        }

        public void push(int val) {
            if(freqStack.isEmpty() || freqStack.peek()[0] > val)
                freqStack.push(new int[]{val,1});
            else freqStack.peek()[1]++;
            stack.push(val);
        }

        public void pop() {
            stack.pop();
            freqStack.peek()[1]--;
            if(freqStack.peek()[1]==0)
                freqStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return freqStack.peek()[0];
        }
    }
    class MinStackA1 {
        Stack<int[]> stack;
        public MinStackA1(){
            stack = new Stack<>();
        }

        public void push(int val) {
            int min = val;
            if(!stack.isEmpty())
                min = Math.min(min,stack.peek()[1]);
            stack.push(new int[]{val,min});
        }

        public void pop() {
            if(!stack.isEmpty())
                stack.pop();
        }

        public int top() {
            int val=-1;
            if(!stack.isEmpty())
                val = stack.peek()[0];
            return val;
        }

        public int getMin() {
            int val=-1;
            if(!stack.isEmpty())
                val = stack.peek()[1];
            return val;
        }
    }
}
