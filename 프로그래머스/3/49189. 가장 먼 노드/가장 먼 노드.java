import java.util.*;

class Solution {
    
    List<Integer>[] graph;
    Map<Integer, Integer> map;
    int max;
    int n;
    
    private void bfs(int start){
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        q.add(new int[]{start, 0});
        visited[start] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int adj : graph[cur[0]]){
                if(visited[adj]) continue;
                visited[adj] = true;
                map.put(cur[1] + 1, map.getOrDefault(cur[1]+1, 0) + 1);
                
                max = Math.max(max, cur[1] + 1);
                q.add(new int[]{adj, cur[1]+1});
            }
        }
    }
        
    public int solution(int n, int[][] edge) {
        max = 0;
        this.n = n;
        
        graph = new ArrayList[n+1];
        
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e : edge){
            int v1 = e[0];
            int v2 = e[1];
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        map = new HashMap<>();
        
        bfs(1);
        
        return map.get(max);
    }
}