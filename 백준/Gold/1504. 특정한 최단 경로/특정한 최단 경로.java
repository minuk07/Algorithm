import java.util.*;
import java.io.*;

class Main {
    static int n, e;
    static List<Node>[] graph;
    static long[] dist;
    static final long INF = Long.MAX_VALUE / 4;

    static class Node {
        int dest;
        long cost;
        Node(int dest, long cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void dijkstra(int start) {
        boolean[] visited = new boolean[n + 1];
        dist = new long[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.dest]) continue;
            visited[cur.dest] = true;

            for (Node ad : graph[cur.dest]) {
                if (!visited[ad.dest] &&
                    dist[ad.dest] > dist[cur.dest] + ad.cost) {

                    dist[ad.dest] = dist[cur.dest] + ad.cost;
                    pq.offer(new Node(ad.dest, dist[ad.dest])); // 핵심 수정
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1, v2
        dijkstra(1);
        long d1v1 = dist[v1];
        long d1v2 = dist[v2];

        // v1 -> v2, n
        dijkstra(v1);
        long dv1v2 = dist[v2];
        long dv1n  = dist[n];

        // v2 -> v1, n
        dijkstra(v2);
        long dv2v1 = dist[v1];
        long dv2n  = dist[n];

        long path1 = d1v1 + dv1v2 + dv2n; // 1 → v1 → v2 → n
        long path2 = d1v2 + dv2v1 + dv1n; // 1 → v2 → v1 → n

        if (d1v1 == INF || dv1v2 == INF || dv2n == INF) path1 = INF;
        if (d1v2 == INF || dv2v1 == INF || dv1n == INF) path2 = INF;

        long ans = Math.min(path1, path2);
        System.out.println(ans >= INF ? -1 : ans);
    }
}
