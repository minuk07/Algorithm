import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int[] cards;
    static List<Integer> list;
    
    static int dfs(int size, int idx){
        if(visited[idx-1]) return size - 1;
        
        visited[idx-1] = true;
        return dfs(size+1, cards[idx-1]);
    }
    
    public int solution(int[] cards) {
        int answer = 0;
        
        int n = cards.length;
        this.cards = cards;
        visited = new boolean[n];
        list = new ArrayList<>();
        
        for(int i=1; i<=n; i++){
            if(visited[i-1]) continue;
            
            list.add(dfs(1, i));
        }
        
        Collections.sort(list, Collections.reverseOrder());
        
        if(list.size() <= 1){
            answer = 0;
        }else{
            answer = list.get(0) * list.get(1);
        }
        
        return answer;
    }
}