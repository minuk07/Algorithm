import java.util.*;

class Solution {
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        Map<Integer, int[]> map = new HashMap<>();
        
        for(int[] e : edges){
            int a = e[0];
            int b = e[1];
            
            map.putIfAbsent(a, new int[2]);
            map.putIfAbsent(b, new int[2]);
            
            map.get(a)[1]++; // out
            map.get(b)[0]++; // in
        }
        
        for(int node : map.keySet()){
            
            int[] edge = map.get(node);
            
            int in = edge[0];
            int out = edge[1];
            
            if(in == 0 && out >= 2) answer[0] = node;
        }
        
        int n = map.get(answer[0])[1];
        
        for(int node : map.keySet()){
            if(node == answer[0]) continue;
            
            int out = map.get(node)[1];
            
            if(out == 0) answer[2]++;
            else if(out == 2) answer[3]++;
        }
        
        answer[1] = n - answer[2] - answer[3];
        
        return answer;
    }
}