package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem735 {
    /*
    735. Asteroid Collision

    We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array
    represent their relative position in space.

    For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right,
    negative meaning left). Each asteroid moves at the same speed.

    Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both
    are the same size, both will explode. Two asteroids moving in the same direction will never meet.

    Example 1:

    Input: asteroids = [5,10,-5]
    Output: [5,10]
    Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
    Example 2:

    Input: asteroids = [8,-8]
    Output: []
    Explanation: The 8 and -8 collide exploding each other.
    Example 3:

    Input: asteroids = [10,2,-5]
    Output: [10]
    Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.


    Constraints:

    2 <= asteroids.length <= 104
    -1000 <= asteroids[i] <= 1000
    asteroids[i] != 0

    approach 1: stack based solution
    * intuition is when the cur element is < 0 and top element pf stack > 0 then we perform operation and
    we continue if the cur element survives
    algo:
    * init stack of ints
    * iterate over elements
        * init flag = true to indicate if we need to add the cur element or not
        * iterate until stack not empty and top element >0 and cur <0
            * check if top element is < cur then pop top and continue
            * else both same then remove top element
            * if we reached this point then cur element is destroyed
            * set flag to false and break
        * if flag is true then push it to stack
    * init ans int array of stack size and add elements and return
    time & space:
    * n time and n space
     */

    public int[] asteroidCollision(int[] asteroids) {
        return asteroidCollisionA1(asteroids);
    }

    public int[] asteroidCollisionA1(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int ast: asteroids){
            boolean flag=true;
            while(!stack.isEmpty() && (ast < 0 && stack.peek()>0)){
                if(Math.abs(stack.peek())<Math.abs(ast)){
                    stack.pop();
                    continue;
                }else if(stack.peek() == -ast)stack.pop();
                flag=false;
                break;
            }
            if(flag)stack.push(ast);
        }
        int[] ans = new int[stack.size()];
        int i=0;
        for(int ast:stack)
            ans[i++]=ast;
        return ans;
    }


}
