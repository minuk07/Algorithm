import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        List<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<progresses.length; i++){
            if((100-progresses[i])%speeds[i] == 0){
                q.add(((100-progresses[i]) / speeds[i]));
            }
            else{
                q.add(((100-progresses[i]) / speeds[i]) + 1);
            }
        }
        
        int frt = q.poll();
        int cnt = 1;
        
        while(!q.isEmpty()){
                        
            if(frt < q.peek()){
                list.add(cnt);
                cnt = 1;
                frt = q.poll();
            }
            else{
                cnt++;
                q.poll();
            }
        }
        
        list.add(cnt);
        
        // for(int i=0; i<list.size(); i++){
        //     System.out.print(list.get(i) + " ");
        // }
        
        int[] result = new int[list.size()];
        
        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
}