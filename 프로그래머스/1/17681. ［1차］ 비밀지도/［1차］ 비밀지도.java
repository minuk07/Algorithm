import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        
        String[] answer = new String[n];
        int[] arr = new int[n];
        
        for(int i=0; i<n; i++){
            arr[i] = arr1[i] | arr2[i];
        }
        
        for(int i=0; i<n; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++){
                int bit = arr[i] & (1 << j);
                sb.append(bit!=0 ? "#" : " ");
                //System.out.println(j+"번째 비트: "+bit+ ", arr: "+arr[i]);
            }
            sb.reverse();
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}