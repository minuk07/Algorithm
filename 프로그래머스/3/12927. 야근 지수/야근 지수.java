import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        
        for(int w : works){
            pq.add(w);
        }
        
        for(int i=0; i<n; i++){
            int job = pq.poll() - 1;
            pq.add(Math.max(job, 0));
        }
        
        while(!pq.isEmpty()){
            int job = pq.poll();
            answer += Math.pow(job, 2);
        }
        
        return answer;
    }
}