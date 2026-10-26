import java.util.*;

class Solution {
    
    int n;
    String[][] relation;
    int answer;
    List<List<Integer>> candidate;
    
    private boolean isUnique(List<Integer> list){
        
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] tuple : relation){
            String tmp = "";
            for(int idx : list){
                tmp += tuple[idx];
            }
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            if(map.get(tmp) >= 2){
                return false;
            }
        }
        
        return true;
    }
    
    private void dfs(int idx, int len, int target, List<Integer> list){
        
        if(target == len){
            
            for(List<Integer> key : candidate){
                if(list.containsAll(key)) return;
            }
            
            if(isUnique(list)){
                candidate.add(new ArrayList<>(list));
                answer++;
            }
            return;
        }
        
        for(int i=idx; i<n; i++){
            list.add(i);
            dfs(i+1, len+1, target, list);
            list.remove(list.size()-1);
        }
    }
    
    public int solution(String[][] relation) {
        answer = 0;
        this.relation = relation;
        n = relation[0].length;
        candidate = new ArrayList<>();
        
        for(int len=1; len<=n; len++){
            dfs(0, 0, len, new ArrayList<>());
        }
        
        return answer;
    }
}