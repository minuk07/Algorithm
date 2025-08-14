import java.util.*;

class Solution {
    public static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        for(int i=0; i<priorities.length; i++){
            pq.add(priorities[i]);
        }
        
        while(!pq.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(priorities[i] == pq.peek()){
                    pq.poll();
                    answer++;
                    
                    if(i == location){
                        return answer;
                    }
                }
            }
        }
        
        return answer;
    }
}