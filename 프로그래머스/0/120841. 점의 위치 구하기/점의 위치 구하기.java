class Solution {
    public int solution(int[] dot) {
    
        int answer;
        int a = dot[0];
        int b = dot[1];
        
        if(a>0 && b>0) answer = 1;
        else if (a<0 && b>0) answer = 2;
        else if (a<0 && b<0) answer = 3;
        else answer =4;
        
        return answer;
    }
}