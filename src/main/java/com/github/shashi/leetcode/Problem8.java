package com.github.shashi.leetcode;
import java.util.*;
public class Problem8 {

    enum State{q0,q1,q2,qd}
    class StateMachine{
        private int result,sign;
        private State currentState;

        StateMachine(){
            result=0;
            sign=1;
            currentState = State.q0;
        }

        public void toStateQ0(){
            currentState = State.q0;
        }
        public void toStateQ1(char ch){
            sign = ch=='-'?-1:1;
            currentState = State.q1;
        }

        public void toStateQ2(char ch){
            currentState = State.q2;
            appendDigit(ch - '0');
        }

        public void toStateQd(){
            currentState = State.qd;
        }

        public void appendDigit(int digit){
            if((result> Integer.MAX_VALUE/10)||
                    ((result == Integer.MAX_VALUE/10) &&
                            digit > Integer.MAX_VALUE%10)){
                if(sign==1)
                    result=Integer.MAX_VALUE;
                else{
                    sign=1;
                    result = Integer.MIN_VALUE;
                }
                toStateQd();
            }else result = result*10+digit;
        }

        public void transition(char ch){
            if(currentState == State.q0){
                if(ch == ' ')return;
                else if(ch=='+' || ch=='-')
                    toStateQ1(ch);
                else if(Character.isDigit(ch))
                    toStateQ2(ch);
                else toStateQd();
            }
            else if(currentState == State.q1 ||
                    currentState == State.q2){
                if(Character.isDigit(ch))
                    toStateQ2(ch);
                else toStateQd();
            }
        }

        public int getInteger(){
            return sign * result;
        }

        public State getState(){
            return currentState;
        }

        public boolean isDead(){
            return currentState==State.qd;
        }
    }

    public int myAtoiSM(String s){
        StateMachine sm = new StateMachine();
        for(int i=0; i<s.length() && !sm.isDead();i++)
            sm.transition(s.charAt(i));
        return sm.getInteger();
    }
    public static void main(String[] args) {
        Problem8 problem8 = new Problem8();
        int ans = problem8.myAtoi("42");
        System.out.println(ans);
    }
    public int myAtoi(String s) {
        return myAtoi2(s);
    }

    public int myAtoi2(String s){
        int index=0,sign=1,n=s.length(),result=0;
        while(index<n && s.charAt(index)==' ')index++;
        if(index<n && s.charAt(index)=='+'){
            sign=1;
            index++;
        }
        if(index<n && s.charAt(index)=='-'){
            sign=-1;
            index++;
        }
        while(index<n && Character.isDigit(s.charAt(index))){
            int digit = '0'- s.charAt(index);
            if((result>Integer.MAX_VALUE/10) ||
                    (result == Integer.MAX_VALUE/10 &&
                            digit > Integer.MAX_VALUE%10))
                return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            result=result*10+digit;
            index++;
        }
        return sign*result;
    }

    public int myAtoi1(String s){
        String s2 = s.trim();
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0; i<10; i++) map.put((char)('0'+i),i);
        long result=0;
        int maxLimit = (int)(Math.pow(2,31)-1);
        int minLimit = (int)(Math.pow(2,31));
        boolean neg = false;
        for(int i=0; i<s2.length(); i++){
            char c = s2.charAt(i);
            if(i==0){
                if(c=='-')
                    neg=true;
                else if(map.containsKey(c))
                    result = map.get(c);
                else if(c=='+') neg=false;
                else break;
            }else if(map.containsKey(c)){
                result = result*10+map.get(c);
                if((result >=maxLimit && !neg)||
                        (result >=minLimit && neg)) break;
            }
            else break;
        }
        if(neg)result = result * -1;
        if(result > Math.pow(2,31)-1) return (int)(Math.pow(2,31)-1);
        if(result < -Math.pow(2,31)) return (int)-Math.pow(2,31);
        return (int)result;
    }
}
