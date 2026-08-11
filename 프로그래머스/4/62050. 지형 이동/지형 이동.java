import java.util.*;

class Solution {
    
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    
    static int n;
    static int height;
    
    static int[][] group;  
    static int[][] land;
    static boolean[][] visited;
    
    static List<Edge> edges;
    static int[] root;
    
    static class Edge{
        int from, to, cost;
        
        Edge(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
    }
    
    static void printList(int[][] list){
        for(int i=0; i<list.length; i++){
            for(int j=0; j<list[i].length; j++){
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }
    
    static void setGroup(int y, int x, int num){
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});
        group[y][x] = num;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            
            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];
                
                if(!inRange(ddy, ddx)) continue;
                if(visited[ddy][ddx]) continue;
                
                int between = land[ddy][ddx] - land[cy][cx];
                if(Math.abs(between) > height) continue;
                
                visited[ddy][ddx] = true;
                q.add(new int[]{ddy, ddx});
                group[ddy][ddx] = num;
            }
        }
        
    }
    
    static void setGraph(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0]; int cx = cur[1];
            int curGroupIdx = group[cy][cx];
            int curLand = land[cy][cx];
            
            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];
                
                if(!inRange(ddy, ddx)) continue;
                if(visited[ddy][ddx]) continue;
                
                int nextGroupIdx = group[ddy][ddx];
                int nextLand = land[ddy][ddx];
                
                if(curGroupIdx == nextGroupIdx){
                    visited[ddy][ddx] = true;
                    q.add(new int[]{ddy, ddx});
                }else{
                    int between = Math.abs(curLand - nextLand);
                    edges.add(new Edge(curGroupIdx, nextGroupIdx, between));
                }
            }
        }
    }
    
    static int find(int a){
        if(root[a] == a) return a;
        
        int r = find(root[a]);
        root[a] = r;
        return r;
    }
    
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA != rootB){
            root[rootB] = rootA;
            return true;
        }
        
        return false;
    }
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        
        this.land = land;
        this.height = height;
        
        n = land.length;
        
        group = new int[n][n];
        visited = new boolean[n][n];
        
        int idx = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    setGroup(i, j, idx);
                    idx++;
                }
            }
        }
        
        visited = new boolean[n][n];
        edges = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    setGraph(i, j);
                }
            }
        }
        
        Collections.sort(edges, (a, b) -> a.cost - b.cost);
        
        root = new int[idx];
        for(int i=0; i<idx; i++){
            root[i] = i;
        }
        
        for(Edge e : edges){
            if(union(e.from, e.to)){
                answer += e.cost;
            }
        }
        
        return answer;
    }
}