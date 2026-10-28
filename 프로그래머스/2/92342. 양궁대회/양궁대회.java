import java.util.*;

class Solution {
    
    int n;
    int[] info;
    int[] my;
    int result;
    int[] answer;
    
    private void printList(int[] arr){
        for(int a : arr){
                System.out.print(a + " ");
            }
        System.out.println();
    }
    
    private boolean isWin(int[] arr){
        int appeach = 0;
        int lion = 0;
        
        for(int i=0; i<=10; i++){
            int score = 10 - i;
            
            if(arr[i] == 0 && info[i] == 0) continue;
            
            if(arr[i] > info[i]) lion += score;
            else appeach += score;
        }
        
        if(lion > appeach && lion - appeach >= result){
            
            result = lion - appeach;
            // System.out.println("lion: " + lion + " appeach: " + appeach);
            // printList(arr);
            return true;
        }else{
            return false;
        }
    }
    
    private void dfs(int score, int used, int[] arr){
        if(used > n){
            return;
        }
        
        
        if(score > 10){
            
            if(n > used) arr[10] += (n - used);
                
            if(isWin(arr)){
                answer = arr.clone();
            }
            
            if(n > used) arr[10] -= (n - used);
            
            return;
        }
        
        int idx = 10 - score;
        int appeach = info[idx];
        
        dfs(score + 1, used, arr);
        
        int mine = appeach + 1;
        arr[idx] = mine;
        dfs(score + 1, used + mine, arr);
        arr[idx] = 0;
        
    }
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        
        result = -1;
        my = new int[11];
        this.n = n;
        this.info = info;
        
        dfs(0, 0, my);
        
        if(result == -1) return new int[]{-1};
        
        return answer;
    }
}