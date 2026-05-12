import java.util.*;

class Solution {
    
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static int answer;
    
    static int dfs(int cur, int prev){
        
        if(graph.get(cur).size() == 1 && graph.get(cur).get(0) == prev){
            return 1;
        }
        
        int temp = 0;
        
        for(Integer next : graph.get(cur)){
            if(next == prev) continue;
            
            temp += dfs(next, cur);
        }
        
        if(temp == 0) return 1;
        
        answer++;
        return 0;
    }
    
    public int solution(int n, int[][] lighthouse) {
        answer = 0;
        
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] g : lighthouse){
            int a = g[0];
            int b = g[1];
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        dfs(1, 0);
        
        return answer;
    }
}