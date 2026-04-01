import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (a, b) -> a[1]-b[1]);
        
        int end = routes[0][1];
        
        for(int[] r : routes){
            if(end < r[0]){
                answer++;
                end = r[1];
            }
        }
        
        return answer;
    }
}