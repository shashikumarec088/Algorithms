package com.github.shashi.misc;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {

    public static void main(String[] args) {
        String name = "test23 4q";
        String pattern = "^[A-Za-z]([A-Za-z0-9_]{2,})[A-Za-z0-9]$";
        Pattern ptn = Pattern.compile(pattern);
        Matcher matcher = ptn.matcher(name);
        System.out.println(matcher.matches());
    }
    public static void main1(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine().trim());
        String[] arr_A = br.readLine().split(" ");
        int[] A = new int[N];
        for(int i_A = 0; i_A < arr_A.length; i_A++)
        {
            A[i_A] = Integer.parseInt(arr_A[i_A]);
        }

        long out_ = solve(N, A);
        System.out.println(out_);

        wr.close();
        br.close();
    }
    static long solve(int N, int[] A){
        // Write your code here
        int i=0;
        long result = 0;
        if(A[0]<0)
        {
            result +=A[0];
            i+=1;
        }
        for(;i<N;i++){
            if(A[i]<0){
                result-=A[i];
            }
            else{
                result+=A[i];
            }
        }
        return result;

    }
}
