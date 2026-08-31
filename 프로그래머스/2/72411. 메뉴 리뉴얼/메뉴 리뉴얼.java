import java.util.*;

class Solution {
    
    static Map<String, Integer> map;
    
    public String[] solution(String[] orders, int[] course) {
        List<String> list = new ArrayList<>();
        
        for(int len : course){
            
            map = new HashMap<>();
            
            for(String order : orders){
                String sorted = sortOrder(order);
                
                dfs(0, len, "", sorted);
            }
            
            int max = 0;
            
            for(int value : map.values()){
                max = Math.max(max, value);
            }
            
            for(String key : map.keySet()){
                int value = map.get(key);
                
                if(value >= 2 && max <= value){
                    list.add(key);
                }
            }
        }
        
        String[] answer = new String[list.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    static void dfs(int idx, int len, String answer , String order){
        
        if(len == answer.length()){
            map.put(answer, map.getOrDefault(answer, 0) + 1);
            return;
        }
        
        for(int i=idx; i<order.length(); i++){
            dfs(i+1, len, answer + order.charAt(i) ,order);
        }
        
    }
    
    static String sortOrder(String order){
        char[] arr = new char[order.length()];
        
        for(int i=0; i<order.length(); i++){
            arr[i] = order.charAt(i);
        }
        
        Arrays.sort(arr);
        
        String answer = "";
        
        for(int i=0; i<arr.length; i++){
            answer += arr[i];
        }
        
        return answer;
    }
}