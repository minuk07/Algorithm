import java.util.*;

class Solution {
    public static int answer = 0;
    public static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        //int answer = 0;
        visited = new boolean[dungeons.length];
        
        dfs(0, k, dungeons);
        
        return answer;
    }
    
    public static void dfs(int depth, int current, int[][] dungeons){
        for(int i=0; i<dungeons.length; i++){
            if(!visited[i] && dungeons[i][0] <= current){
                visited[i] = true;
                dfs(depth+1, current-dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
        //System.out.println("answer: " + answer + " depth: " + depth);
        answer = Math.max(answer, depth);
        
        //System.out.println("answer: " + answer);
    }
}