import java.util.*;

class Solution {
    
    int n;
    int[][] q;
    int[] ans;
    
    int answer;
    
    private boolean check(List<Integer> list){
        
        int[] result = new int[q.length];
        
        for(int i=0; i<q.length; i++){
            int[] secret = q[i];
            
            int tmp = 0;
            for(int s : secret){
                if(list.contains(s)) tmp++;
            }
            
            result[i] = tmp;
        }
        
        for(int i=0; i<q.length; i++){
            if(ans[i] != result[i]) return false;
        }
        
        return true;
    }
    
    private void dfs(List<Integer> list, int num){
        
        if(list.size() == 5){
            
            if(check(list)) answer++;
            return;
        }
        
        for(int i=num; i<=n; i++){
            list.add(i);
            dfs(list, i+1);
            list.remove(list.size()-1);
        }
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        
        this.n=n; this.q=q; this.ans=ans;
        
        dfs(new ArrayList<>(), 1);
        
        return answer;
    }
}