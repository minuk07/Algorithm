import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static List<Node>[] graph;
    static boolean[] visited;
    static int result;

    static class Node{
        int dest, weight;
        Node(int dest, int weight){
            this.dest = dest; this.weight = weight;
        }
    }

    public static void dfs(int cur, int dist){
        for(Node n : graph[cur]){
            if(!visited[n.dest]){
                visited[n.dest] = true;
                dfs(n.dest, dist + n.weight);
            }
        }
        result = Math.max(result, dist);
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[parent].add(new Node(child, weight));
            graph[child].add(new Node(parent, weight));
        }

        result = 0;

        for(int i=1; i<n+1; i++){
            visited = new boolean[n+1];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.print(result);
    }
}