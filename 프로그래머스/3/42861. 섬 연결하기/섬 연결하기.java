import java.util.*;

class Solution {
    
    int[] root;
    
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
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        root = new int[n+1];
        
        for(int i=0; i<=n; i++){
            root[i] = i;
        }
        
        Arrays.sort(costs, (a,b) -> a[2] - b[2]);
        
        for(int[] cost : costs){
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];
            
            if(union(a, b)) answer += c;
        }
        
        return answer;
    }
}