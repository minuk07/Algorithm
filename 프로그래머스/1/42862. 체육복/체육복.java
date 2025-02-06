import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int result = n - lost.length;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if(lost[i] == reserve[j]){
                    result ++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length;j++){
                if(reserve[j]-1==lost[i] || reserve[j]+1 == lost[i]){
                    result++;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        return result;
    }
}