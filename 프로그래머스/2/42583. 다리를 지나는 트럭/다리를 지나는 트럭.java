import java.util.*;

class Solution {
    public Queue<Integer> q = new LinkedList<>();
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int sum = 0;
        
        for(int i=0; i<truck_weights.length; i++){
            int next = truck_weights[i];
            while(true){
                if(q.isEmpty()){
                    q.add(next);
                    sum += next;
                    answer++;
                    break;
                }
                else if(q.size() < bridge_length){
                    if(sum + next <= weight){
                        q.add(next);
                        sum += next;
                        answer++;
                        break;
                    }
                    else{
                        q.add(0);
                        answer++;
                    }
                }
                else{
                    sum -= q.peek();
                    q.poll();
                }
            }
        }
        
        
        return answer + bridge_length ;
    }
}