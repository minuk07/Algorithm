import java.util.*;

class Solution {
    public String solution(String my_string, int num1, int num2) {
        
        StringBuilder sb = new StringBuilder();
        sb.append(my_string);
        
        char ch1 = sb.charAt(num1);
        char ch2 = sb.charAt(num2);
        
        sb.setCharAt(num1, ch2);
        sb.setCharAt(num2, ch1);
        
        String answer = sb.toString();
        return answer;
    }
}