import java.util.*;

class Solution {
    
    int m;
    int answer;
    int[][] q;
    int[] ans;
    
    private boolean check(List<Integer> list){
        
        int idx = 0;
        
        for(int[] secret : q){
            int tmp = 0;
            for(int s : secret){
                if(list.contains(s)) tmp++;
            }
            if(tmp != ans[idx++]) return false;
        }
        
        return true;
    }
    
    private void dfs(int cur, int n, List<Integer> list){
        
        if(list.size() == 5){
            if(check(list)) answer++;
            return;
        }
        
        for(int i=cur; i<=n; i++){
            list.add(i);
            dfs(i+1, n, list);
            list.remove(list.size() - 1);
        }
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        this.q = q;
        this.ans = ans;
        m = q.length;
        
        dfs(1, n, new ArrayList<>());
        
        return answer;
    }
}