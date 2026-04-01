import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int idx = B.length - 1;
        int len = B.length - 1;
        
        for(int i=len; i>=0; i--){
            if(A[i] < B[idx]){
                answer++;
                idx--;
            }
        }
        
        return answer;
    }
}