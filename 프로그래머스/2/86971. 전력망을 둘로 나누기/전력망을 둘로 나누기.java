import java.util.*;

class Solution {
    
    List<Integer>[] graph;
    
    private int bfs(int n, int start){
        
        int cnt = 1;
        
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int adj : graph[cur]){
                if(visited[adj]) continue;
                visited[adj] = true;
                cnt++;
                q.add(adj);
            }
        }
        
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        graph = new ArrayList[n+1];
        
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires){
            int a = wire[0];
            int b = wire[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        for(int[] wire : wires){
            int a = wire[0];
            int b = wire[1];
            
            graph[a].remove(Integer.valueOf(b));
            graph[b].remove(Integer.valueOf(a));
            
            int cnt = bfs(n, a);
            
            answer = Math.min(answer, Math.abs((n-cnt) -  cnt));
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        return answer;
    }
}