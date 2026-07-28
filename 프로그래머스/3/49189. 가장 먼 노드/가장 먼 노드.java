import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static Map<Integer, Integer> map;
    
    static int n;
    
    static void bfs(int start){
        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        
        visited[start] = true;
        q.add(new int[]{start, 0});
        map.put(0, 1);
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int node = cur[0];
            int depth = cur[1];
            
            for(int adj : graph[node]){
                if(visited[adj]) continue;
                
                visited[adj] = true;
                q.add(new int[]{adj, depth+1});
                map.put(depth+1, map.getOrDefault(depth+1, 0) + 1);
            }
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        this.n = n;
        
        graph = new ArrayList[n+1];
        map = new HashMap<Integer, Integer>();
        
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e : edge){
            int a = e[0];
            int b = e[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        bfs(1);
        
        
        
        return map.get(map.size()-1);
    }
}