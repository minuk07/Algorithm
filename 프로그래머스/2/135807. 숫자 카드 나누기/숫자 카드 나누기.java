import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        
        for(int a : arrayA){
            gcdA = gcd(gcdA, a);
        }
        
        for(int b : arrayB){
            gcdB = gcd(gcdB, b);
        }
        
        if(cantDivide(gcdA, arrayB)){
            answer = Math.max(answer, gcdA);
        }
        
        if(cantDivide(gcdB, arrayA)){
            answer = Math.max(answer, gcdB);
        }
        
        return answer;
    }
    
    static int gcd(int a,int b){
        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
    
    static boolean cantDivide(int n, int[] array){
        for(int a : array){
            if(a % n == 0) return false;
        }
        
        return true;
    }
}