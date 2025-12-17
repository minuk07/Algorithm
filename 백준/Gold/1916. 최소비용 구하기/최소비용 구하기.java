import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int m;

    static List<Bus>[] graph;
    static int[] dist;

    static class Bus{
        int dest, cost;
        Bus(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }

    public static void dijkstra(int start){
        boolean[] visited = new boolean[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Bus> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        pq.offer(new Bus(start,0));

        dist[start] = 0;

        while(!pq.isEmpty()){
            Bus cur = pq.poll();
            if(visited[cur.dest]) continue;
            visited[cur.dest] = true;
            
            for(Bus next : graph[cur.dest]){
                if(dist[next.dest] > dist[cur.dest] + next.cost){
                    dist[next.dest] = dist[cur.dest] + next.cost;
                    pq.offer(new Bus(next.dest, dist[next.dest]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Bus(b,c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.print(dist[dest]);
    }
}