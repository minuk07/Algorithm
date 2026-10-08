import java.util.*;

class Solution {
    
    List<String> result;
    Map<String, Integer> map;
    
    private String sortedOrder(String order){
        char[] tmp = order.toCharArray();
        Arrays.sort(tmp);
        
        return new String(tmp);
    }
    
    private void dfs(int depth, int target, String str, String order){
        if(target == str.length()){
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }
        
        for(int i=depth; i<order.length(); i++){
            dfs(i+1, target, str+order.charAt(i), order);
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        
        result = new ArrayList<>();
        
        for(int c : course){
            map = new HashMap<>();
            for(String order : orders){
                String sorted = sortedOrder(order);
                dfs(0, c, "", sorted);
            }
            
            int max = 0;
            
            for(int value : map.values()){
                max = Math.max(max, value);
            }
            
            for(String key : map.keySet()){
                int value = map.get(key);
                if(value == max && max >= 2){
                    result.add(key);
                }
            }
        }
        
        Collections.sort(result);
        
        return result.toArray(new String[0]);
    }
}