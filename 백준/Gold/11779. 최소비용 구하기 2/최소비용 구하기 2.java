import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n, m;
    static int startCity, destCity;
    static int[] dist;
    static final int INF = 100000000;
    static List<Bus>[] graph;
    static int[] prev;

    static class Bus{
        int dest, cost;

        Bus(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }

    static void dijkstra(int start){
        boolean[] visited = new boolean[n+1];
        dist = new int[n+1];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Bus> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        pq.add(new Bus(start, 0));

        while(!pq.isEmpty()){
            Bus cur = pq.poll();

            if(visited[cur.dest]) continue;
            visited[cur.dest] = true;

            for(Bus ad : graph[cur.dest]){
                if(!visited[ad.dest] && dist[ad.dest]> dist[cur.dest] + ad.cost){

                    prev[ad.dest] = cur.dest;
                    dist[ad.dest] = dist[cur.dest] + ad.cost;
                    pq.add(new Bus(ad.dest, dist[ad.dest]));
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

        graph = new ArrayList[n + 1];
        prev = new int[n+1];

        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[s].add(new Bus(d, c));
        }

        st = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(st.nextToken());
        destCity = Integer.parseInt(st.nextToken());

        dijkstra(startCity);

        if(dist[destCity] == INF){
            System.out.println(0);
            System.out.println(0);
            return;
        }

        List<Integer> path = new ArrayList<Integer>();

        int cur = destCity;

        while(cur != startCity){
            path.add(cur);
            cur = prev[cur];
            if(cur == startCity) break;
        }

        Collections.reverse(path);
        
        System.out.println(dist[destCity]);
        System.out.println(path.size()+1);

        System.out.print(startCity + " ");
        for(int i=0; i<path.size(); i++){            
            System.out.print(path.get(i) + " ");
        }
    }
}