import java.util.*;

class Solution {
    static int[][] board;
    static int n;

    static int[][][] cost;   

    static int[] dy = {-1, 1, 0, 0};  // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    
    public static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }
    
    public static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();

        for(int d=0; d<4; d++){
            cost[y][x][d] = 0;
            q.add(new int[]{y, x, d});
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int uy = cur[0];
            int ux = cur[1];
            int dir = cur[2];

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(board[ddy][ddx] == 1) continue;

                int newCost = cost[uy][ux][dir] + (dir == i ? 100 : 600);

                if(cost[ddy][ddx][i] > newCost){
                    cost[ddy][ddx][i] = newCost;
                    q.add(new int[]{ddy, ddx, i});
                }
            }
        }
    }
    
    public int solution(int[][] board) {
        Solution.board = board;
        n = board.length;

        cost = new int[n][n][4];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        bfs(0, 0);

        return Math.min(
                Math.min(cost[n-1][n-1][0], cost[n-1][n-1][1]),
                Math.min(cost[n-1][n-1][2], cost[n-1][n-1][3])
        );
    }
}
