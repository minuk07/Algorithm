class Solution {
    int aa = 5500;
    
    public int[] solution(int money) {
        
        int num = money / aa;
        int m = money % aa;
        
        int[] answer = {num, m};
        return answer;
    }
}