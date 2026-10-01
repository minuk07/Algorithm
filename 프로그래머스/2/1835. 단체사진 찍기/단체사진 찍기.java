import java.util.*;

class Solution {
    
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] visited;
    String[] data;
    int answer;
    
    private boolean canOrder(Map<Character, Integer> ordered){
        for(String d : data){
            char[] wanted = d.toCharArray();
            
            char a = wanted[0];
            char b = wanted[2];
            char condition = wanted[3];
            int num = wanted[4] - '0';
            
            int distance = Math.abs(ordered.get(a) - ordered.get(b)) - 1;
        
            if(condition == '='){
                if(distance != num) return false;
            }else if(condition == '<'){
                if(distance >= num) return false;
            }else{
                if(distance <= num) return false;
            }
        }
        
        return true;
    }
    
    private void dfs(int order, Map<Character, Integer> ordered){
        if(order == 9){
            if(canOrder(ordered)) answer++;
            return;
        }
        
        for(int i=0; i<8; i++){
            if(!visited[i]){
                visited[i] = true;
                ordered.put(friends[i], order);
                dfs(order+1, ordered);
                ordered.remove(friends[i]);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int n, String[] data) {
        answer = 0;
        this.data = data;
        
        Map<Character, Integer> ordered = new HashMap<>();
        visited = new boolean[8];
        
        dfs(1, ordered);
        
        return answer;
    }
}