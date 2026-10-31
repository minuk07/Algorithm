import java.util.*;

class Solution {
    
    List<Node>[] graph;
    boolean[] visited;
    int n;
    int answer;
    
    class Node{
        int dest, type;
        
        Node(int dest, int type){
            this.dest=dest; this.type=type;
        }
    }
    
    
    private void dfs(int cnt, int k){
        if(cnt == k){
            
            int result = 0;
            for(int i=1; i<=n; i++){
                if(visited[i]) result++;
            }
            answer = Math.max(result, answer);
            
            return;
        }
        
        for(int type=1; type<=3; type++){
            List<Integer> list = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();
            
            for(int i=1; i<=n; i++){
                if(visited[i]) q.add(i);
            }
            
            while(!q.isEmpty()){
                int cur = q.poll();
                
                for(Node adj : graph[cur]){
                    if(visited[adj.dest]) continue;
                    if(adj.type != type) continue;
                    
                    q.add(adj.dest);
                    list.add(adj.dest);
                    visited[adj.dest] = true;
                }
            }
            
            dfs(cnt+1, k);
            
            for(int l : list){
                visited[l] = false;
            }
        }
        
    }
    
    public int solution(int n, int infection, int[][] edges, int k) {
        answer = 0;
        
        graph = new ArrayList[n+1];
        this.n = n;
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            int v1 = edge[0];
            int v2 = edge[1];
            int type = edge[2];
            
            graph[v1].add(new Node(v2, type));
            graph[v2].add(new Node(v1, type));
        }
        
        visited = new boolean[n+1];
        
        visited[infection] = true;
        
        dfs(0, k);
        
        return answer;
    }
}