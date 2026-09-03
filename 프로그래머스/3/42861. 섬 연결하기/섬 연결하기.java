import java.util.*;

class Solution {
    
    static int[] root;
    
    static int find(int idx){
        if(root[idx] == idx) return idx;
        
        int r = find(root[idx]);
        root[idx] = r;
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
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        root = new int[n];
        
        for(int i=0; i<n; i++){
            root[i] = i;
        }
        
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        for(int[] cost : costs){
            if(union(cost[0], cost[1])){
                answer += cost[2];
            }
        }
        
        return answer;
    }
}