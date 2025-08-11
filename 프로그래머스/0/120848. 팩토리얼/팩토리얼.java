class Solution {
    public int solution(int n) {
        int answer = 1;
        
        while(true){
            //answer++;
            if(fact(answer) > n) return answer-1;
            else answer++;
        }
        
    }
    
    public int fact(int n){
        if(n==1) return 1;
        else{
            return n*fact(n-1);
        }
    }
}