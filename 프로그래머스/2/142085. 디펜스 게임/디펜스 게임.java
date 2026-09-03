import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        
        int sum = 0;
        
        for(int round = 0; round<enemy.length; round++){
            int cur = enemy[round];
            
            pq.add(cur);
            sum += cur;
            
            if(sum > n && k > 0){
                k--;
                sum -= pq.poll();
            }
            
            if(sum > n){
                answer = round;
                break;
            }
            
            if(round == enemy.length - 1){
                answer = enemy.length;
            }
            
            
        }
        
        return answer;
    }
}