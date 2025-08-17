class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        
        long min = 0;
        long max = 0;
        
        if(a <= b){
            min = a;
            max = b;
        }
        else{
            min = b;
            max = a;
        }
        
        for(long i=min; i<=max; i++){
            answer+=i;
        }
        
        return answer;
    }
}