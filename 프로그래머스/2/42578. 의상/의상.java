import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] clothe : clothes){
            String name = clothe[0];
            String type = clothe[1];
            
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        
        for(Integer num : map.values()){
            answer *= (num + 1);
        }
        
        return answer - 1;
    }
}