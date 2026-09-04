import java.util.*;

class Solution {
    
    static Map<String, Integer> map;
    
    static void dfs(int start, int len, int target, String now, String order){
        
        if(len == target){
            map.put(now, map.getOrDefault(now, 0) + 1);
            return;
        }
        
        for(int i=start; i<order.length(); i++){
            dfs(i+1, len+1, target, now + order.charAt(i), order);
        }
        
        return;
    }
    
    static String sortOrder(String order){
        String answer = "";
        
        char[] tmp = new char[order.length()];
        
        for(int i=0; i<order.length(); i++){
            tmp[i] = order.charAt(i);
        }
        
        Arrays.sort(tmp);
        
        for(char c : tmp){
            answer += c;
        }
        
        return answer;
    }
    
    public String[] solution(String[] orders, int[] course) {
        List<String> list = new ArrayList<>();
        
        map = new HashMap<>();
        
        
        for(int c : course){
            
            for(String order : orders){
                String sortedOrder = sortOrder(order);
                dfs(0, 0, c, "", sortedOrder);
            }
        }
        
        for(int c :course){
            
            int maxSize = 0;
            
            for(String key : map.keySet()){
                if(key.length() == c){
                    maxSize = Integer.max(maxSize, map.get(key));
                }
            }
            
            for(String key : map.keySet()){
                if(key.length() == c && map.get(key) == maxSize && map.get(key) >= 2){
                    list.add(key);
                }
            }
        }
        
        Collections.sort(list);
        
        String[] answer = new String[list.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}