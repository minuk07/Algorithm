import java.util.*;

class Solution {
    
    int[] dy = {0,0,-1,1};
    int[] dx = {1,-1,0,0};
    
    int n, height;
    int[][] land;
    int[][] map;
    boolean[][] visited;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
    int[] root;
    
    private void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    private boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }
    
    private void bfs(int y, int x, int idx){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        map[y][x] = idx;
        visited[y][x] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i=0; i<4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(Math.abs(land[cur[0]][cur[1]] - land[ny][nx]) > height) continue;
                q.add(new int[]{ny, nx});
                visited[ny][nx] = true;
                map[ny][nx] = idx;
            }
        }
    }
    
    private void makeGraph(int y, int x, int idx){
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if(!inRange(ny, nx)) continue;
            if(map[y][x] == map[ny][nx]) continue;
            int cost = Math.abs(land[y][x] - land[ny][nx]);
            pq.add(new int[]{map[y][x], map[ny][nx], cost});
        }
    }
    
    private int find(int a){
        if(root[a] == a) return a;
        
        int r = find(root[a]);
        root[a] = r;
        return r;
    }
    
    private boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA == rootB) return false;
        root[rootB] = rootA;
        return true;
    }
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        this.land = land;
        this.height = height;
        n = land.length;
        map = new int[n][n];
        visited = new boolean[n][n];
        
        int idx = 1;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    bfs(i,j,idx++);
                }
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                makeGraph(i, j, map[i][j]);
            }
        }
        
        root = new int[idx];
        
        for(int i=0; i<idx; i++){
            root[i] = i;
        }
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];
            
            if(union(a,b)){
                answer += c;
            }
        }
        
        return answer;
    }
}