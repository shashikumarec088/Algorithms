package com.github.shashi.leetcode;
import java.util.*;
public class Problem190  {
    /*
    Reverse Bits
    Reverse bits of a given 32 bits unsigned integer.
    Note:
    Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output
    will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary
    representation is the same, whether it is signed or unsigned.
    In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above,
    the input represents the signed integer -3 and the output represents the signed integer -1073741825.
    Example 1:

    Input: n = 00000010100101000001111010011100
    Output:    964176192 (00111001011110000010100101000000)
    Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
    so return 964176192 which its binary representation is 00111001011110000010100101000000.
    Example 2:

    Input: n = 11111111111111111111111111111101
    Output:   3221225471 (10111111111111111111111111111111)
    Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
    so return 3221225471 which its binary representation is 10111111111111111111111111111111.

    Constraints:
    The input must be a binary string of length 32
    Follow up: If this function is called many times, how would you optimize it?

    Approach 1: bits shifting
    * intuition is to consider the lsb shift it to its right position for example 0th bit to 31st pos of ans
    and 1st to 30th like that, until the number is not zero after processing each bit shift number 1 position right
    algo:
    * init ans=0, pow=31
    * iterate until n !=0, for each iteration
    * make and = ans + (and number with 1 and shift left by pow positions ie ans+= (n&1)<< pow;
    * do unsigned shift of ans to 1 position right n=n>>>1
    * dec pow
    * return ans at the end
    time & space:
    * it takes constant time & const space

    Approach 2: bit masking
    * intuition is to mask half of the bits swap with other half, continue until the swap size is reduced to 1,
    initially we swap first 16 with last 16 using masking technique.
    * first we we change positions of 16 bits, that can be done by first masking last 16 bits and shifting the
    result to right 16 times and oring the result with the left shifted last 16 bits ie n= n >>> 16 || n << 16
    *  then we need to shift the 8 bits within each 16 bits this can be done by masking last 8 then first 8
    it & with 0xff00ff00 and for considering last 8  0x00ff00ff.
    * for considering the first 4 bits we & with 0xf0f0f0f0 and 0x0f0f0f0f shift 4 positions
    * for considering first 2 bits we and with 0xcccccccc and for considering last 2 bits 0x33333333
    * for considering alternate bit 0xaaaaaaaa and from lat we and with 0x55555555
    time & space:
    * it takes constant time and space

    Approach 3: Byte by Byte with memoization
    * intuition is to convert the integer into array of bytes and reverse each byte. we can cache the bytes
    so that for repeated reversal we can use the cache which also helps when we are processing multiple integers
    or input string with many bytes
    algo:
    * create a global cache of type map of Byte, Integer
    * convert the input into bytes array, init bytes array of size 4
    * iterate from i=0 to < 4, for each i make bytes[i] to num & 0xff
    * make num = num >>> 8
    * then init res=0 iterate from i=0 to 4, for each value of  i
    * shift res 8 positions left  ie res = res << 8
    * add reverseByte(bytes[i]) to res
    * return res at the end
    * in reverseByte(bytes[i]) method check if num present in cache ifso return
    * init ans=0, iterate i=0 <8
    * shift ans left by 1 position, add ans with (num >>> i) & 1;
    * return ans at the end
    time & space:
    * takes const time & only space for cache

     */
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

    public int reverseBitsA1(int n) {
        int ans=0, pow=31;
        while(n!=0){
            ans += (n&1) << pow;
            n = n >>> 1;
            pow -=1;
        }
        return ans;
    }
}