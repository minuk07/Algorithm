import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n,m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        cnt = 0;

        bfs(1);

        System.out.println(cnt);
    }

    static void bfs(int start){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int y = q.poll();

            for(int next : graph.get(y)){
                if(visited[next]) continue;
                visited[next] = true;
                cnt++;
                q.add(next);
            }
        }
    }
}