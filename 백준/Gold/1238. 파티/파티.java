import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static final int INF = Integer.MAX_VALUE / 4;

    static int n;
    static int m;
    static int x;

    static List<Node>[] graph;
    static int[] dist;

    static void printList(int[] arr){
        for(int i=1; i<=n; i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }

    static class Node{
        int dest, cost;
        Node(int dest, int cost){
            this.dest=dest;this.cost=cost;
        }
    }

    public static void dijkstra(int start){
        dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, INF);
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
                    pq.offer(new Node(next.dest, dist[next.dest]));
                }
            }
        }
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,t));
        }

        int result = 0;

        for(int i=1; i<=n; i++){
            if(i == x) continue;
            int temp = 0;
            dijkstra(i);
            temp += dist[x];
            dijkstra(x);
            temp += dist[i];
            result = Math.max(result, temp);
        }

        System.out.println(result);
    }
}