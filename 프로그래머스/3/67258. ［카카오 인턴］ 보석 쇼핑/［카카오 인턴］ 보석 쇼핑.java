import java.util.*;

class Solution {
    
    Map<String, Integer> map;
    Set<String> set;
    
    public int[] solution(String[] gems) {
        
        map = new HashMap<>();
        set = new HashSet<>();
        
        for(String gem : gems){
            set.add(gem);
        }
        
        int bestL = 0;
        int bestR = Integer.MAX_VALUE;
        int left = 0;
        
        for(int right=0; right<gems.length; right++){
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            
            while(map.get(gems[left]) > 1){
                map.put(gems[left], map.get(gems[left]) - 1);
                left++;
            }
            
            if(map.size() == set.size() && bestR-bestL > right-left){
                bestR = right;
                bestL = left;
            }
            
        }
        
        
        return new int[]{bestL+1, bestR+1};
    }
}