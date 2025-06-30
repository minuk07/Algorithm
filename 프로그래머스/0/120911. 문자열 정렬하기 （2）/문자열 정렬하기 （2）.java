import java.util.*;

class Solution {
    public String solution(String my_string) {
    
        my_string = my_string.toLowerCase();
        //System.out.print(my_string);
        
        char[] str = new char[my_string.length()];
        
        for(int i=0; i<my_string.length(); i++){
            str[i] = my_string.charAt(i);
        }
        
        Arrays.sort(str);
        
//         for(int i=0; i<str.length; i++){
//             System.out.print(str[i] + " ");
//         }
        
        String answer = "";
        
        for(char c : str){
            answer += c;
        }
        return answer;
    }
}