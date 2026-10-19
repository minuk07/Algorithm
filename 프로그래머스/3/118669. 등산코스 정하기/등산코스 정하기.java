import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE / 2;
    int[] type;
    List<Node>[] graph;

    class Node{
        int dest, cost;
        Node(int dest, int cost){
            this.dest=dest; this.cost=cost;
        }
    }
    
    private int[] dijkstra(int[] gates, int[] summits, int n){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        
        for(int gate : gates){
            dist[gate] = 0;
            pq.add(new Node(gate, 0));
        }
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(cur.cost > dist[cur.dest]) continue;
            if(type[cur.dest] == 2) continue;
            
            for(Node adj : graph[cur.dest]){
                int newCost = Math.max(adj.cost, cur.cost);
                if(dist[adj.dest] > newCost){
                    dist[adj.dest] = newCost;
                    pq.add(new Node(adj.dest, newCost));
                }
            }
        }
        
        int[] result = new int[2];
        int bestSummit = -1;
        int bestIntensity = Integer.MAX_VALUE;
        
        Arrays.sort(summits);
        
        for(int summit : summits){
            if(bestIntensity > dist[summit]){
                bestSummit = summit;
                bestIntensity = dist[summit];
            }
        }
        
        result[0] = bestSummit;
        result[1] = bestIntensity;
        
        return result;
    }
    
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        type = new int[n+1];
        
        for(int g : gates){
            type[g] = 1;
        }
        
        for(int s : summits){
            type[s] = 2;
        }
        
        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] path : paths){
            int v1 = path[0];
            int v2 = path[1];
            int cost = path[2];
            
            graph[v1].add(new Node(v2, cost));
            graph[v2].add(new Node(v1, cost));
        }
        
        return dijkstra(gates, summits, n);        
    }
}