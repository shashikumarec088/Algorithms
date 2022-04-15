package com.github.shashi.leetcode;
import java.util.*;
public class Problem190  {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        return reverseBitsA3(n);
    }

    Map<Byte,Integer> cache = new HashMap<>();

    public int reverseBitsA3(int n){
        byte[] bytes = new byte[4];
        for(int i=0; i<4; i++){
            bytes[i] = (byte)(n&0xff);
            n = n>>>8;
        }
        int res = 0;
        for(int i=0; i<4; i++){
            res = res << 8;
            res += reverseByte(bytes[i]);
        }
        return res;
    }

    public int reverseByte(byte bytes){
        Integer val = cache.get(bytes);
        if(val != null)return val;
        int res = 0;
        for(int i=0; i<8; i++){
            res = res << 1;
            res += (bytes >>> i) & 1;
        }
        cache.put(bytes,res);
        return res;
    }

    public int reverseBitsA2(int n){
        n = n>>>16 | n << 16;
        n = (n&0xff00ff00) >>> 8 | (n&0x00ff00ff) << 8;
        n = (n&0xf0f0f0f0) >>> 4 | (n&0x0f0f0f0f) << 4;
        n = (n&0xcccccccc) >>> 2 | (n&0x33333333) << 2;
        n = (n&0xaaaaaaaa) >>> 1 | (n&0x55555555) << 1;
        return n;
    }

    public int reverseBitsA1(int n){
        int res=0;
        for(int i=0; i<32; i++){
            res = res<<1;
            if((1&n)==1)
                res=res^1;
            n = n>>1;
        }
        return res;
    }
}