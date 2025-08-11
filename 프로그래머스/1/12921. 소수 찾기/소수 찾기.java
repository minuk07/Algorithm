import java.util.*;

class Solution {
    
    public int solution(int n) {
        int answer = 0;
        
        for(int i=2; i<=n; i++){
            if(isPrime(i)) answer++;
            else continue;
        }
        
        // for(int i=0; i<list.size(); i++){
        //     System.out.println(list.get(i)+ " ");
        // }
        
        return answer;
    }
    
    boolean isPrime(int n){
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i == 0) return false;
        }
        
        return true;
    }
}