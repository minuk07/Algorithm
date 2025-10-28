import java.util.*;

class Solution {
    public static int answer;
    public static boolean visited[];
    public static int graph[][];
    
    public int solution(int n, int[][] wires) {
        answer = Integer.MAX_VALUE;
        
        graph = new int[n+1][n+1];
        
        for(int i=0; i<wires.length; i++){
            graph[wires[i][0]][wires[i][1]] = 1;
            graph[wires[i][1]][wires[i][0]] = 1;
        }
        
        for(int i=0; i<wires.length; i++){
            
            visited = new boolean[n+1];
            
            graph[wires[i][1]][wires[i][0]] = 0;
            graph[wires[i][0]][wires[i][1]] = 0;
            
            int[] arr = new int[2];
            int t = 0;
            int first = dfs(1, graph);
            int second = n - first;
            
            answer = Math.min(answer, Math.abs(first-second));
            
            //System.out.println(i + "번째: a=" + first +" b="+second +"answer="+answer);
            
            graph[wires[i][0]][wires[i][1]] = 1;
            graph[wires[i][1]][wires[i][0]] = 1;
        }
        
        return answer;
    }
    
    public int dfs(int x, int[][] graph){
        
        int con = 1;
        
        visited[x] = true;
        for(int i=1; i<graph.length; i++){
            if(graph[x][i] == 1 && !visited[i]){
                con += dfs(i, graph);
            }
        }
        
        return con;
    }
}