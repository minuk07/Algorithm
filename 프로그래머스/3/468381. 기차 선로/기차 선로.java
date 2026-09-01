import java.util.*;

class Solution {

    static final int RIGHT = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int UP = 3;

    static int[][] grid;
    static int[][] installed;

    static int n;
    static int m;

    static int required;
    static int answer;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    
    static boolean inRange(int y, int x){
        return(y >= 0 && x>=0 && y<n && x<m);
    }

    static void dfs(int y, int x, int dir, int count) {
        
        if (!inRange(y, x)) {
            return;
        }

        if (grid[y][x] == -1) {
            return;
        }

        if (y == n - 1 && x == m - 1) {

            if (count != required) {
                return;
            }

            if (dir == RIGHT && grid[y][x] == 1) {
                answer++;
            }

            if (dir == DOWN && grid[y][x] == 2) {
                answer++;
            }

            return;
        }

        if (dir == RIGHT) {

            if (grid[y][x] == 1 || grid[y][x] == 3) {
                dfs(y, x + 1, RIGHT, count + 1);

            } else if (grid[y][x] == 4) {
                dfs(y - 1, x, UP, count + 1);

            } else if (grid[y][x] == 7) {
                dfs(y + 1, x, DOWN, count + 1);

            } else if (grid[y][x] == 0) {

                if (installed[y][x] == 3) {
                    dfs(y, x + 1, RIGHT, count);

                } else if (installed[y][x] == 0) {

                    installed[y][x] = 3;
                    dfs(y, x + 1, RIGHT, count);

                    installed[y][x] = 4;
                    dfs(y - 1, x, UP, count);

                    installed[y][x] = 7;
                    dfs(y + 1, x, DOWN, count);

                    installed[y][x] = 0;
                }
            }

        } else if (dir == DOWN) {

            if (grid[y][x] == 2 || grid[y][x] == 3) {
                dfs(y + 1, x, DOWN, count + 1);

            } else if (grid[y][x] == 4) {
                dfs(y, x - 1, LEFT, count + 1);

            } else if (grid[y][x] == 5) {
                dfs(y, x + 1, RIGHT, count + 1);

            } else if (grid[y][x] == 0) {

                if (installed[y][x] == 3) {
                    dfs(y + 1, x, DOWN, count);

                } else if (installed[y][x] == 0) {

                    installed[y][x] = 3;
                    dfs(y + 1, x, DOWN, count);

                    installed[y][x] = 4;
                    dfs(y, x - 1, LEFT, count);

                    installed[y][x] = 5;
                    dfs(y, x + 1, RIGHT, count);

                    installed[y][x] = 0;
                }
            }

        } else if (dir == LEFT) {

            if (grid[y][x] == 1 || grid[y][x] == 3) {
                dfs(y, x - 1, LEFT, count + 1);

            } else if (grid[y][x] == 5) {
                dfs(y - 1, x, UP, count + 1);

            } else if (grid[y][x] == 6) {
                dfs(y + 1, x, DOWN, count + 1);

            } else if (grid[y][x] == 0) {

                if (installed[y][x] == 3) {
                    dfs(y, x - 1, LEFT, count);

                } else if (installed[y][x] == 0) {

                    installed[y][x] = 3;
                    dfs(y, x - 1, LEFT, count);

                    installed[y][x] = 5;
                    dfs(y - 1, x, UP, count);

                    installed[y][x] = 6;
                    dfs(y + 1, x, DOWN, count);

                    installed[y][x] = 0;
                }
            }

        } else {

            if (grid[y][x] == 2 || grid[y][x] == 3) {
                dfs(y - 1, x, UP, count + 1);

            } else if (grid[y][x] == 6) {
                dfs(y, x + 1, RIGHT, count + 1);

            } else if (grid[y][x] == 7) {
                dfs(y, x - 1, LEFT, count + 1);

            } else if (grid[y][x] == 0) {

                if (installed[y][x] == 3) {
                    dfs(y - 1, x, UP, count);

                } else if (installed[y][x] == 0) {

                    installed[y][x] = 3;
                    dfs(y - 1, x, UP, count);

                    installed[y][x] = 6;
                    dfs(y, x + 1, RIGHT, count);

                    installed[y][x] = 7;
                    dfs(y, x - 1, LEFT, count);

                    installed[y][x] = 0;
                }
            }
        }
    }

    public int solution(int[][] grid) {

        n = grid.length;
        m = grid[0].length;

        this.grid = grid;

        installed = new int[n][m];

        required = 0;
        answer = 0;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {

                if (grid[y][x] == 3) {
                    required += 2;
                } else if (grid[y][x] >= 1) {
                    required++;
                }
            }
        }

        dfs(0, 0, RIGHT, 1);

        return answer;
    }
}