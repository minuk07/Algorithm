import java.util.*;

class Solution {
    
    static int n;
    static List<Integer>[] graph;
    
    static int bfs(int start, int dest){
        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{start, 0});
        visited[start] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(cur[0] == dest){
                return cur[1];
            }
            
            for(int adj : graph[cur[0]]){
                if(visited[adj]) continue;
                q.add(new int[]{adj, cur[1] + 1});
                visited[adj] = true;
            }
        }
        
        return -1;
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer> result = new ArrayList<>();
        
        this.n = n;
        graph = new ArrayList[n+1];
        
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            int a = road[0];
            int b = road[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        for(int source : sources){
            result.add(bfs(source, destination));
        }
        
        int[] answer = new int[sources.length];
        for(int i=0; i<answer.length; i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}