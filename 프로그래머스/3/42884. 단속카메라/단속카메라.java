import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (a,b) -> a[1] - b[1]);
        
        int start = routes[0][0];
        int end = routes[0][1];
        
        for(int i=1; i<routes.length; i++){
            if(routes[i][0] > end){
                start = routes[i][0];
                end = routes[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}