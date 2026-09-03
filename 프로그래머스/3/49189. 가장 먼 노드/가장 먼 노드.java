import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static Map<Integer, Integer> map;
    
    static int n;
    
    static class Node{
        int idx, depth;
        
        Node(int idx, int depth){
            this.idx = idx; this.depth = depth;
        }
    }
    
    static void bfs(Node start){
        boolean[] visited = new boolean[n+1];
        Queue<Node> q = new LinkedList<>();
        
        q.add(start);
        visited[start.idx] = true;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(int adj : graph[cur.idx]){
                if(visited[adj]) continue;
                
                map.put(cur.depth + 1, map.getOrDefault(cur.depth + 1, 0) + 1);
                q.add(new Node(adj, cur.depth + 1));
                visited[adj] = true;
            }
        }
    }
    
    public int solution(int n, int[][] edge) {
        
        this.n = n;
        
        graph = new ArrayList[n+1];
        map = new HashMap<>();
        
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e : edge){
            int a = e[0];
            int b = e[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        Node start = new Node(1, 0);
        map.put(0, 1);
        bfs(start);
        
        return map.get(map.size() - 1);
    }
}