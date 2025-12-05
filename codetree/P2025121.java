import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class P2025121 {

    static int n;
    static int q;
    static List<Mineral> list = new ArrayList<>();
    static int[][] map;
    static int[][] temp;
    static boolean[][] visited;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static StringBuilder sb;

    static class Mineral{
        int id, r1, c1, r2, c2, width;
        boolean del;
        Mineral(int id, int r1, int c1, int r2, int c2, int width){
            this.id=id;this.r1=r1;this.c1=c1;this.r2=r2;this.c2=c2;this.width=width;
            this.del= true;
        }
    }

    public static void printList(int[][] map){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x < n);
    }

    public static void putMineral(Mineral m){
        for(int i=m.r1; i<=m.r2; i++){
            for(int j=m.c1; j<=m.c2; j++){
                map[i][j] = m.id;
            }
        }
    }

    public static void updateMineral(Mineral m){
        int cnt = 0;

        m.r1 = Integer.MAX_VALUE;
        m.c1 = Integer.MAX_VALUE;
        m.r2 = Integer.MIN_VALUE;
        m.c2 = Integer.MIN_VALUE;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == m.id){
                    m.r1 = Math.min(i, m.r1);
                    m.c1 = Math.min(j, m.c1);
                    m.r2 = Math.max(i, m.r2);
                    m.c2 = Math.max(j, m.c2);
                    cnt++;
                }
            }
        }

        m.width = cnt;
        if(cnt == 0){
            m.del = false;
        }
        //System.out.println(m.id+": " + "[" + m.r1 + "," + m.c1 +"][" +m.r2+","+m.c2+"]");
    }

    public static boolean isConnected(int id){
        visited = new boolean[n][n];
        int cnt = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j] && map[i][j] == id){
                    cnt++;
                    dfs(i,j,id);
                    if(cnt > 1) return false;
                }
            }
        }
        return true;
    }

    public static void dfs(int y, int x, int id){
        visited[y][x] = true;

        for(int i=0; i<4; i++){
            int ddy = y + dy[i];
            int ddx = x + dx[i];

            if(!inRange(ddy,ddx)) continue;
            if(!visited[ddy][ddx] && map[ddy][ddx] == id){
                dfs(ddy, ddx, id);
            }
        }
    }

    public static void remove(Mineral m){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == m.id){
                    map[i][j] = 0;
                }
            }
        }
        m.del = false;
        m.width = 0;
    }

    public static void moveMineral(Mineral m){

        int curHei = m.r2-m.r1+1;
        int curWid = m.c2-m.c1+1;

        for(int i=0; i<=n-curHei; i++){
            boolean place = false;
            for(int j=0; j<=n-curWid; j++){
                boolean flag = true;
                for(int k=0; k<curHei; k++){
                    for(int l=0; l<curWid; l++){
                        int originY = m.r1 + k;
                        int originX = m.c1 + l;
                        if(map[originY][originX] != m.id) continue;
                        if(temp[i+k][j+l] > 0){
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                }

                if(flag){
                    for(int k=0; k<curHei; k++){
                        for(int l=0; l<curWid; l++){
                            int originY = m.r1 + k;
                            int originX = m.c1 + l;
                            if(map[originY][originX] != m.id) continue;
                            temp[i+k][j+l] = m.id;
                        }
                    }
                    place = true;
                    break;
                }

            }
            if(place) break;
        }

    }

    public static void calculateMineral(int num){
        boolean[][] isAdj = new boolean[num+1][num+1];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];
                    if(!inRange(ddy, ddx)) continue;
                    if(isAdj[map[i][j]][map[ddy][ddx]] || isAdj[map[ddy][ddx]][map[i][j]]) continue;

                    if(map[i][j] != map[ddy][ddx]){
                        isAdj[map[i][j]][map[ddy][ddx]] = true;
                        isAdj[map[ddy][ddx]][map[i][j]] = true;
                    }
                }
            }
        }
        int result = 0;


        for(int i=1; i<=num; i++){
            for(int j=i; j<=num; j++){
                if(isAdj[i][j]){
                    result += (list.get(i-1).width * list.get(j-1).width);
                }
            }
        }

        sb.append(result).append('\n');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        sb = new StringBuilder();

        for(int tc=1; tc<=q; tc++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            int width = (r2-r1+1) * (c2-c1+1);

            Mineral mineral = new Mineral(tc, r1, c1, r2, c2, width);

            list.add(mineral);
            putMineral(mineral);


            for(Mineral m : list){
                if(!m.del) continue;
                if(!isConnected(m.id)){
                    remove(m);
                }
            }

            for(Mineral m : list){
                if(!m.del) continue;
                updateMineral(m);
            }

            list.sort(Comparator.comparingInt((Mineral a) -> a.width).reversed());

            temp = new int[n][n];

            for(Mineral m : list){
                if(!m.del) continue;
                moveMineral(m);
            }

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    map[i][j] = temp[i][j];
                }
            }

            for(Mineral m : list){
                if(!m.del) continue;
                updateMineral(m);
            }

            list.sort(Comparator.comparingInt((Mineral a) -> a.id));

            calculateMineral(tc);

            // System.out.println(tc);
            // printList(map);

        }
        System.out.print(sb.toString());
    }
}