import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        int temp = a[0];
        
        for(int i=0; i<n; i++){
            left[i] = Math.min(a[i], temp);
            temp = left[i];
        }
        
        temp = a[n-1];
        for(int i=n-1; i>=0; i--){
            right[i] = Math.min(a[i], temp);
            temp = right[i];
        }
        
        int answer = 2;
        
        for(int i=1; i<n-1; i++){
            int b = a[i];
            if(left[i] < b && right[i] < b) continue;
            answer++;
        }
        
        return answer;
    }
}