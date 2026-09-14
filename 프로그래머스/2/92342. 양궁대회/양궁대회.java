import java.util.*;

class Solution {
    
    static int[] info;
    static int n;
    static List<Integer> answerArray = new ArrayList<>();
    static int maxDiff;
    
    static void printList(List<Integer> list){
        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
    
    static boolean isWin(List<Integer> result){
        
        int a = 0;
        int l = 0;
        
        for(int i=0; i<=10; i++){
            
            if(result.get(i) != 0){
                l += i;
                continue;
            }
            
            
            if(info[10-i] != 0){
                a += i;
            }
        }
        
        if(a < l && maxDiff <= l - a){
            maxDiff = l - a;
            return true;
        }
        
        return false;
    }
    
    static void dfs(int score, int used, List<Integer> result){

        if(used > n){
            return;
        }
        
        if(score > 10){
            
            if(used < n){
                result.set(0, result.get(0) + (n - used));
            }
            
            if(isWin(result)){
                answerArray = new ArrayList<>(result);
            }
            
            if(used < n){
                result.set(0, result.get(0) - (n - used));    
            }
            
            return;
        }
        
        int idx = 10 - score;
        
        int mine = info[idx] + 1;
        result.add(0);
        dfs(score + 1, used, result);
        result.remove(result.size() - 1);
        
        result.add(mine);
        dfs(score + 1, used + mine, result);
        result.remove(result.size() - 1);
    }
    
    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        
        this.n = n;
        this.info = info;
        
        List<Integer> result = new ArrayList<>();
        answerArray = new ArrayList<>();
        maxDiff = -1;
        dfs(0, 0, result);
        
        if(!answerArray.isEmpty()){
            for(int i=0; i<=10; i++){
                answer[10-i] = answerArray.get(i);
            }
            
            return answer;
        }
        
        return new int[]{-1};
    }
}