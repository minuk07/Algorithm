import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int s : scoville){
            pq.add(s);
        }
        
        while(!pq.isEmpty() && pq.peek() < K){
            
            if(pq.size() < 2) return -1;
            
            answer++;
            
            int newOne = 0;
            
            newOne += pq.poll();
            newOne += pq.poll() * 2;
            
            pq.add(newOne);
        }
        
        return answer;
    }
}