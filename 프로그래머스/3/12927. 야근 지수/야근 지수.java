import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        
        for(int i=0; i<works.length; i++){
            pq.add(works[i]);
        }
        
        while(n>0 && !pq.isEmpty()){
            n--;
            int num = pq.poll();
            
            if(num <= 0) break;
            pq.add(num - 1);
        }
        
        if(pq.isEmpty()) return answer;
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(),2);
        }
        
        
        return answer;
    }
}