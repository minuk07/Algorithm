import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
        
        for(int i=0; i<enemy.length; i++){
            int e = enemy[i];
            pq.add(e);
            
            n -= e;
            if(n < 0){
                if(k > 0){
                    k--;
                    n += pq.poll();
                }
                else{
                    break;
                }
            }
            answer++;
        }
        
        return answer;
    }
}