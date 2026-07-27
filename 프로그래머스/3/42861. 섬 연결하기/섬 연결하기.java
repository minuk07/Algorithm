import java.util.*;

class Solution {
    
    static int[] root;
    
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
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        Arrays.sort(costs, (a,b) -> a[2] - b[2]);
        root = new int[n];
        
        for(int i=0; i<n; i++){
            root[i] = i;
        }
        
        for(int i=0; i<costs.length; i++){
            if(union(costs[i][0], costs[i][1])){
                answer += costs[i][2];
            }
        }
        
        return answer;
    }
}