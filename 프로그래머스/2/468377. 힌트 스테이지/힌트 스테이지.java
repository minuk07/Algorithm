import java.util.*;

class Solution {
    
    static int n, k;
    static int[] bundle;
    static int[][] cost;
    static int[][] hint;
    static int answer;
    
    private void dfs(int stage, int[] bundle, int c){
        
        if(stage > n){
            answer = Math.min(c, answer);
            return;
        }
        
        int curStageHint = Math.min(n-1, bundle[stage]);
        c += cost[stage-1][curStageHint];
        
        dfs(stage + 1, bundle, c);
        
        if(stage < n){
            int[] buyHint = hint[stage - 1];
            
            c += buyHint[0];
            
            for(int i=1; i<buyHint.length; i++){
                bundle[buyHint[i]]++; 
            }
            
            dfs(stage + 1, bundle, c);
            
            for(int i=1; i<buyHint.length; i++){
                bundle[buyHint[i]]--;
            }
        }
        
    }
    
    public int solution(int[][] cost, int[][] hint) {
        
        this.cost = cost;
        this.hint = hint;
        
        n = cost.length;
        k = hint.length;
        
        bundle = new int[n+1];
        answer = Integer.MAX_VALUE;
        dfs(1, bundle, 0);
        
        return answer;
    }
}