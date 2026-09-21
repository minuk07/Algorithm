import java.util.*;

class Solution {
    
    static List<Node>[] graph;
    static boolean[] visited;
    static int answer;
    static int n;
    
    static class Node{
        int dest, pipe;
        
        Node(int dest, int pipe){
            this.dest = dest; this.pipe = pipe;
        }
    }
    
    static void bfs(int cnt, int k){
        if(k == cnt){
            int infected = 0;
            for(int i=1; i<=n; i++){
                if(visited[i]) infected++;
            }
            answer = Math.max(answer, infected);
            return;
        }
        
        for(int t=1; t<=3; t++){
            List<Integer> newNode = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();
            
            for(int i=1; i<=n; i++){
                if(visited[i]){
                    q.add(i);
                }
            }
            
            while(!q.isEmpty()){
                int cur = q.poll();
                
                for(Node adj : graph[cur]){
                    if(visited[adj.dest]) continue;
                    if(t != adj.pipe) continue;
                    
                    visited[adj.dest] = true;
                    newNode.add(adj.dest);
                    q.add(adj.dest);
                }
            }
            
            bfs(cnt+1, k);
            
            for(int node : newNode){
                visited[node] = false;
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
            int a = edge[0];
            int b = edge[1];
            int pipe = edge[2];
            
            graph[a].add(new Node(b, pipe));
            graph[b].add(new Node(a, pipe));
        }
        
        visited = new boolean[n+1];
        visited[infection] = true;
        
        bfs(0, k);
        
        return answer;
    }
}