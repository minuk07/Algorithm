import java.util.*;

class Solution {
    
    int answer;
    List<List<String>> candidate;
    Set<String> result;
    
    private boolean isSame(String user, String banned){
        
        if(user.length() != banned.length()) return false;
        
        for(int i=0; i<user.length(); i++){
            int u = user.charAt(i);
            int b = banned.charAt(i);
            
            if(b == '*') continue;
            if(u != b) return false;
        }
        
        return true;
    }

    private void dfs(int idx, List<String> list, String[] banned_id){
        if(idx == banned_id.length){
            List<String> sorted = new ArrayList<>(list);
            Collections.sort(sorted);
            
            StringBuilder tmp = new StringBuilder();
            for(String s : sorted){
                tmp.append(s);
                tmp.append(",");
            }
            result.add(tmp.toString());
            
            return;
        }
        
        for(String user : candidate.get(idx)){
            if(list.contains(user)) continue;
            
            list.add(user);
            dfs(idx+1, list, banned_id);
            list.remove(user);
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        
        candidate = new ArrayList<>();
        
        for(String banned : banned_id){
            
            List<String> list = new ArrayList<>();
            
            for(String user : user_id){
                if(isSame(user, banned)){
                    list.add(user);
                }
            }
            
            candidate.add(list);
        }
        
        result = new HashSet<>();
        dfs(0, new ArrayList<>(), banned_id);
        
        return result.size();
    }
}