import java.util.*;

class Solution {
    public String solution(String s) {
        boolean flag = true;
        s = s.toLowerCase();
        String answer = "";
                
        for(int i=0; i<s.length(); i++){
            char temp = s.charAt(i);
            if(temp == ' '){
                flag = true;
            }
            else if (flag){
                temp = Character.toUpperCase(temp);
                flag = false;
            }
            answer += temp;
        }
        
        return answer;
    }
}