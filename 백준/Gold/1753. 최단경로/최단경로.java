import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int v;
    static int e;
    static int k;

    static List<Node>[] graph;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;

    static class Node{
        int dest, cost;
        Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }

    public static void dijkstra(int start){
        boolean[] visited = new boolean[v+1];
        dist = new int[v+1];
        for(int i=0; i<v+1; i++){
            dist[i] = INF;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.dest]) continue;
            visited[cur.dest] = true;
            
            for(Node next : graph[cur.dest]){
                if(!visited[next.dest] && dist[next.dest] > dist[cur.dest] + next.cost){
                    dist[next.dest] = dist[cur.dest] + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v+1];
        for(int i=0; i<=v; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v,w));
        }

        dijkstra(k);
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<=v; i++){
            if(i == k)
            {
                sb.append("0").append('\n');
            }
            else if (dist[i] == INF){
                sb.append("INF").append('\n');
            }
            else{
                sb.append(dist[i]).append('\n');
            }
        }

        System.out.print(sb.toString());
    }
}