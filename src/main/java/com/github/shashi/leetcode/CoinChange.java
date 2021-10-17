package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoinChange {

    public static void main(String[] args) {
        CoinChange cn = new CoinChange();
        int[] input = new int[]{186,419,83,408};
        System.out.println(cn.coinChange(input,6249));
    }
    public int coinChange(int[] coins, int amount) {
        return coinChangeSort(coins,amount);
    }

    public int coinChangeSort(int[] coins, int amount){
        int count =0;
        if(amount == 0) return count;
        List<Integer> list = new ArrayList<>();
        for(int i : coins)list.add(i);
        list.sort(Comparator.reverseOrder());
        for(Integer coin : list){
            if(coin > amount)continue;
            count += amount/coin;
            amount = amount%coin;
            if(amount == 0)return count;
        }
        return -1;
    }
}
