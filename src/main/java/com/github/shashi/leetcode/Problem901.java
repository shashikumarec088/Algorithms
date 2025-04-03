package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem901 {
    /*
    901. Online Stock Span

    Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's
     price for the current day.

    The span of the stock's price in one day is the maximum number of consecutive days (starting from that day
    and going backward) for which the stock price was less than or equal to the price of that day.

    For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2,
    then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4
    consecutive days.
    Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8,
    then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3
    consecutive days.
    Implement the StockSpanner class:

    StockSpanner() Initializes the object of the class.
    int next(int price) Returns the span of the stock's price given that today's price is price.


    Example 1:

    Input
    ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
    [[], [100], [80], [60], [70], [60], [75], [85]]
    Output
    [null, 1, 1, 1, 2, 1, 4, 6]

    Explanation
    StockSpanner stockSpanner = new StockSpanner();
    stockSpanner.next(100); // return 1
    stockSpanner.next(80);  // return 1
    stockSpanner.next(60);  // return 1
    stockSpanner.next(70);  // return 2
    stockSpanner.next(60);  // return 1
    stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
    stockSpanner.next(85);  // return 6


    Constraints:

    1 <= price <= 105
    At most 104 calls will be made to next.

    Approach 1: using stack
    * intuition is that for reach element we need to find the number of days before which we have seen the price
    greater than the current price, to do this we need to have the previous prices and the position of those
    prices in the array, we can use stack to store the prices and their position in the array
    * then we can compare the current prices with the values on stack and remove the older ones and add the
    position to current answer
    * this can effectively solved using the monotonic stack
    algo:
    * create stack of type int[] to store the price and the position
    * init stack in constructor
    * in next method
        * init ans=1 since min days is 1
        * iterate until stack is not empty and price at top of stack is <= price
            * pop the element from stack and add the days difference to ans
        * push the price and ans to stack
        * return ans

    time & space:
    * O(n) time and space
     */

    class StockSpanner {
        Stack<int[]> stack;

        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price) {
            int ans=1;
            while(!stack.isEmpty() && stack.peek()[0]<=price)
                ans+=stack.pop()[1];
            stack.push(new int[]{price,ans});
            return ans;
        }
    }
}
