class Solution {
    public int solution(int n) {
        int answer = 0;
        
        while(true){
            answer++;
            if(answer*6 % n == 0){
                return answer;
            }
            
        }
    }
}