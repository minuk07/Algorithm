import java.util.*;

class Solution {
    
    Map<String, List<Integer>> map;
    
    private void dfs(String[] app, String combi, int depth){
        if(depth == 4){
            map.computeIfAbsent(combi, k -> new ArrayList<>()).add(Integer.parseInt(app[4]));
            return;
        }
        
        dfs(app, combi + app[depth], depth+1);
        dfs(app, combi + "-", depth+1);
    } 
    
    private String makeKey(String[] query){
        String key = "";
        
        for(int i=0; i<query.length - 1; i+=2){
            key += query[i];
        }
        
        return key;
    }
    
    public int[] solution(String[] info, String[] query) {
        List<Integer> list = new ArrayList<>();
        map = new HashMap<>();
        
        for(String app : info){
            dfs(app.split(" "), "", 0);
        }
        
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        
        for(String q : query){
            String[] tmp = q.split(" ");
            String key = makeKey(tmp);
            int score = Integer.parseInt(tmp[tmp.length -1]);
            
            if(map.get(key) == null){
                list.add(0);
                continue;
            }
            
            List<Integer> scores = map.get(key);
            
            int left = 0;
            int right = scores.size();
            
            while(left < right){
                int mid = left + (right - left) / 2;
                
                if(scores.get(mid) < score){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }
            
            list.add(scores.size() - left);
        }
        
        int[] answer = new int[query.length];
        
        for(int i=0; i<query.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}