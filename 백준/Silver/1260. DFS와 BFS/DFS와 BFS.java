
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int k;
    static int start;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];

        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int g1 = Integer.parseInt(st.nextToken());
            int g2 = Integer.parseInt(st.nextToken());

            graph.get(g1).add(g2);
            graph.get(g2).add(g1);
        }

        for(int i=0; i<n+1; i++){
            Collections.sort(graph.get(i));
        }

        dfs(graph, visited, start);

        visited = new boolean[n+1];

        System.out.println();
        bfs(graph, visited, start);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int start){
        visited[start] = true;
        System.out.print(start + " ");

        for(int i=0; i<graph.get(start).size(); i++){
            int x = graph.get(start).get(i);
            if(!visited[x]){
                dfs(graph, visited, x);
            }
        }
    }

    public static void bfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int start){
        visited[start] = true;
        q.add(start);

        while(!q.isEmpty()){
            int x = q.peek();
            System.out.print(x + " ");
            q.remove();

            for(int i=0; i<graph.get(x).size(); i++){
                int y = graph.get(x).get(i);

                if(!visited[y]){
                    q.add(y);
                    visited[y] = true;
                }
            }
        }
    }
}