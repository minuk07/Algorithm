class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0){
            int tmp = storey % 10;
            
            if(tmp > 5){
                storey += (10 - tmp);
                answer += (10 - tmp);
            }else if(tmp < 5){
                storey -= tmp;
                answer += tmp;
            }else{
                int next = (storey / 10) % 10;
                
                if(next >= 5){
                    storey += 5;
                    answer += 5;
                }else{
                    storey -= 5;
                    answer += 5;
                }
            }
            
            storey /= 10;
        }
        
        return answer;
    }
}