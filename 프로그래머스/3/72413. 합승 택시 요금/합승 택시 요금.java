import java.util.*;

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    static List<Node>[] graph;
    static int n;
    
    static class Node{
        int dest, cost;
        
        Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }
    
    static int[] dijkstra(int start){
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        
        pq.add(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            visited[cur.dest] = true;
            
            for(Node adj : graph[cur.dest]){
                
                if(visited[adj.dest]) continue;
                
                if(dist[adj.dest] > dist[cur.dest] + adj.cost){
                    dist[adj.dest] = dist[cur.dest] + adj.cost;
                    pq.add(new Node(adj.dest, dist[adj.dest]));
                }
            }
        }
        
        return dist;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        this.n = n;
        
        graph = new ArrayList[n+1];
        
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares){
            int a1 = fare[0];
            int a2 = fare[1];
            int c = fare[2];
            graph[a1].add(new Node(a2, c));
            graph[a2].add(new Node(a1, c));
        }
        
        int[] distS = dijkstra(s);
        int[] distA = dijkstra(a);
        int[] distB = dijkstra(b);
        
        for(int i=1; i<=n; i++){
            int tmp = 0;
            tmp += distS[i];
            tmp += distA[i];
            tmp += distB[i];
            
            answer = Math.min(answer, tmp);
        }
        
        
        
        return answer;
    }
}