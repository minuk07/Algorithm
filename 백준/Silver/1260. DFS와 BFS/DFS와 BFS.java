import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n, m, v;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());  
            int b = Integer.parseInt(st.nextToken());  

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i=1; i<n+1; i++){
            Collections.sort(graph.get(i));
        }

        visited = new boolean[n+1];
        dfs(v);

        System.out.println();

        visited = new boolean[n+1];
        bfs(v);
    }

    static void dfs(int start){
        
        visited[start] = true;
        System.out.print(start + " ");

        for(int next : graph.get(start)){
            if(visited[next]) continue;
            dfs(next);
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        System.out.print(start + " ");

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : graph.get(now)){
                if(visited[next]) continue;
                visited[next] = true;
                System.out.print(next + " ");
                q.add(next);
            }
        }
    }
}