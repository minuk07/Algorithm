import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
    
        int[] answer = new int[commands.length];
        
        for(int t=0; t<commands.length; t++){
            int i = commands[t][0]; int j = commands[t][1]; int k = commands[t][2];
            int[] temp = new int[j-i+1];
            
            for(int n = 0; n <j-i+1; n++){
                temp[n] = array[n+i-1];
            }
            
            Arrays.sort(temp);
            
            answer[t] = temp[k-1];
        }
        
        return answer;
    }
}