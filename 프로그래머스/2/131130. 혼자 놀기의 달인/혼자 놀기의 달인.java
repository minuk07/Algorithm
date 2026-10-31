import java.util.*;

class Solution {
    
    int n;
    int[] cards;
    boolean[] visited;
    List<Integer> list;
    
    private void dfs(int cnt, int idx){
        if(visited[idx]){
            list.add(cnt);
            return;
        }
        
        int num = cards[idx];
        visited[idx] = true;
        dfs(cnt+1, num-1);
    }
    
    public int solution(int[] cards) {
        this.cards = cards;
        n = cards.length;
        
        visited = new boolean[n];
        list = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(0, i);
            }
        }
        
        Collections.sort(list);
        
        if(list.size() == 1){
            return 0;
        }
        
        return list.get(list.size()-1) * list.get(list.size()-2);
    }
}