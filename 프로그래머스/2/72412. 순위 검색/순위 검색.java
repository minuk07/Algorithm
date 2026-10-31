import java.util.*;

class Solution {
    
    int n;
    Map<String, List<Integer>> map;
    
    private void dfs(int idx, String str, int score, String[] info){
        if(idx >= 4){
            map.computeIfAbsent(str, k -> new ArrayList<>()).add(score);
            return;
        }
        
        dfs(idx+1, str + "-", score, info);
        dfs(idx+1, str + info[idx], score, info);
        
    }
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        n = info.length;
        map = new HashMap<>();
        
        for(int i=0; i<n; i++){
            String[] information = info[i].split(" ");
            int score = Integer.parseInt(information[4]);
            dfs(0, "", score, information);
        }
        
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        
        for(int i=0; i<query.length; i++){
            String[] tmp = query[i].split(" ");
            String q = "";
            for(int j=0; j<tmp.length-1; j++){
                if(tmp[j].equals("and")) continue;
                q += tmp[j];
            }
            
            int score = Integer.parseInt(tmp[tmp.length-1]);
            
            if(map.get(q) == null){
                answer[i] = 0;
                continue;
            }
            
            List<Integer> scores = map.get(q);
            
            int left = 0;
            int right = scores.size();
            
            while(left < right){
                int mid = left + (right - left) / 2;
                
                if(scores.get(mid) >= score){
                    right = mid;
                }else{
                    left = mid + 1;
                }
            }
            
            answer[i] = scores.size() - left;
        }
        
        return answer;
    }
}