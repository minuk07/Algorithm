class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        boolean flag = true;
        
        long purse1 = 0;
        long purse2 = 0;
        
        for(int s : sequence){
            purse1 += (flag ? s : -s);
            purse2 += (flag ? -s : s);
            
            purse1 = Math.max(0, purse1);
            purse2 = Math.max(0, purse2);
            
            answer = Math.max(answer, Math.max(purse1, purse2));
            
            flag = !flag;
        }
        
        return answer;
    }
}