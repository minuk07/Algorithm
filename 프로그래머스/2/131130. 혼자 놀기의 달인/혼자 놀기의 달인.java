import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int n;
    static int[] cards;
    
    static List<List<Integer>> result;
    static List<Integer> tmp;
    
    static void dfs(int idx, List<Integer> list){
        if(visited[idx]){
            result.add(new ArrayList<>(list));
            return;
        }
        
        list.add(cards[idx]);
        visited[idx] = true;
        dfs(cards[idx]-1, list);
    }
    
    public int solution(int[] cards) {
        int answer = 0;
        
        this.cards = cards;
        n = cards.length;
        visited = new boolean[n];
        
        result = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                tmp = new ArrayList<>();
                tmp.add(cards[i]);
                dfs(cards[i] - 1, tmp);
            }
        }
        
        Collections.sort(result, (a,b) -> b.size() - a.size());
        
        if(result.size() < 2){
            return 0;
        }
        
        return result.get(0).size() * result.get(1).size();
    }
}