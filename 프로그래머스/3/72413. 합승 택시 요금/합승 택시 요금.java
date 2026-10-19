import java.util.*;

class Solution {
    
    final int INF = 200 * 100_000;
    int[] dist;
    List<Node>[] graph;
    
    class Node{
        int dest, cost;
        Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }
    
    private void dijkstra(int start, int n){
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        dist[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(cur.cost > dist[cur.dest]) continue;
            
            for(Node adj : graph[cur.dest]){
                
                int newCost = cur.cost + adj.cost;
                if(dist[adj.dest] > newCost){
                    dist[adj.dest] = newCost;
                    pq.add(new Node(adj.dest, newCost));
                }
            }
        }
        
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares){
            int v1 = fare[0];
            int v2 = fare[1];
            int c = fare[2];
            
            graph[v1].add(new Node(v2, c));
            graph[v2].add(new Node(v1, c));
        }
        
        for(int i=1; i<=n; i++){
            int tmp = 0;
            dijkstra(i, n);
            
            tmp += dist[s] + dist[a] + dist[b];
            
            answer = Math.min(tmp, answer);
        }
        
        return answer;
    }
}