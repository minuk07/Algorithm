class Solution {
    public String solution(int n) {
        String answer = "";
        String[] numbers = {"4", "1", "2"};
        
        while(n>0){
            int tmp = n%3;
            
            if(tmp == 0){
                n--;
            }
            
            answer = numbers[tmp] + answer;
            
            n /= 3;
        }
        
        return answer;
    }
}