package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class PerfectSquares {
    Set<Integer> square_nums = new HashSet<>();

    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        perfectSquares.numSquares(2);
    }
    protected boolean is_divided_by(int n, int count) {
        if (count == 1) {
            return square_nums.contains(n);
        }

        for (Integer square : square_nums) {
            if (is_divided_by(n - square, count - 1)) {
                return true;
            }
        }
        return false;
    }

    public int numSquares(int n) {
        this.square_nums.clear();

        for (int i = 1; i * i <= n; ++i) {
            this.square_nums.add(i * i);
        }

        int count = 1;
        for (; count <= n; ++count) {
            if (is_divided_by(n, count))
                return count;
        }
        return count;
    }
}
