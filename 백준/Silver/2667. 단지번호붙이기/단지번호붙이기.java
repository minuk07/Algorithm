import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int n;
    static int[][] map;
    static boolean[][] visited;

    static List<Integer> list;

    static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x <n);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];
        list = new ArrayList<>();

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int cnt = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    list.add(bfs(i, j));
                    cnt++;
                }
            }
        }

        list.sort((a,b) -> a-b);

        System.out.println(cnt);

        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    static int bfs(int y, int x){
        int tmp = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0]; int cx = cur[1];

            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(map[ddy][ddx] == 0) continue;
                if(visited[ddy][ddx]) continue;

                q.add(new int[]{ddy, ddx});
                visited[ddy][ddx] = true;
                tmp++;
            }
        }

        return tmp;
    }
}