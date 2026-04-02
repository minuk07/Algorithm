import java.util.*;

class Solution {
    
    static Set<Set<String>> result = new HashSet<>();
    static boolean[] visited;
    static int n;
    
    
    static void dfs(int idx, String[] user_id, String[] banned_id, Set<String> selected){
        
        if(idx == banned_id.length){
            result.add(new HashSet<>(selected));
            return;
        }
        
        for(int i=0; i<user_id.length; i++){
            if(visited[i]) continue;
            
            if(!match(user_id[i], banned_id[idx])) continue;
            
            visited[i] = true;
            selected.add(user_id[i]);
            dfs(idx + 1, user_id, banned_id, selected);
            visited[i] = false;
            selected.remove(user_id[i]);
        }
        
    }
    
    static boolean match(String user, String banned){
        
        if(user.length() != banned.length()) return false;
        
        for(int i=0; i<user.length(); i++){
            if (banned.charAt(i) == '*') continue;
            if (user.charAt(i) != banned.charAt(i)) return false;
        }
        
        return true;
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        n = user_id.length;
        visited = new boolean[n];
        
        dfs(0, user_id, banned_id, new HashSet<>());
        
        return result.size();
        
    }
}