import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        int sum = 0;
        
        for(int i=0; i<enemy.length; i++){
            int e = enemy[i];
            sum += e;
            pq.add(e);
            
            if(sum > n){
                if(k > 0){
                    sum -= pq.poll();
                    k--;
                }else{
                    return i;
                }
            }
        }
        
        return enemy.length;
    }
}