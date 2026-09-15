import java.util.*;

class Solution {
    
    static int answer;
    
    static int n, m;
    static List<List<Integer>> input;
    static int[] ans;
    
    static void printList(List<Integer> list){
        for(int l : list){
            System.out.print(l + " ");
        }
        System.out.println();
    }
    
    static boolean sameAnswer(int[] secret){
        for(int i=0; i<m; i++){
            if(secret[i] != ans[i]){
                return false;
            }
        }
        
        return true;
    }
    
    static int[] getContainsValue(List<Integer> combi){
        
        int[] secret = new int[m];
        
        for(int i=0; i<m; i++){
            
            int tmp = 0;
            for(int a : combi){
                if(input.get(i).contains(a)){
                    tmp++;
                }
            }
            
            secret[i] = tmp;
        }
        
        return secret;
    }
    
    static void dfs(int len, int num, List<Integer> list){
        
        if(num > n){
            return;
        }
        
        if(len == 5){
            
            int[] secret = getContainsValue(new ArrayList<>(list));
            
            if(sameAnswer(secret)){
                answer++;
            }
            
            return;
        }
        
        for(int i=num+1 ; i<=n; i++){
            list.add(i);
            dfs(len+1, i, list);
            list.remove(list.size() - 1);
        }
        
    }
    
    
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        
        this.n = n;
        this.ans = ans;
        m = q.length;
        
        List<Integer> list = new ArrayList<>();
        
        input = new ArrayList<>();
        
        for(int i=0; i<m; i++){
            input.add(new ArrayList<>());
        }
        
        for(int i=0; i<m; i++){
            for(int j=0; j<5; j++){
                input.get(i).add(q[i][j]);
            }
        }
        
        for(int i=1; i<=n; i++){
            list = new ArrayList<>();
            list.add(i);
            dfs(1, i, list);
            list.remove(0);
        }
        
        
        return answer;
    }
}