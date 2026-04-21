import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        HashSet<String> hash = new HashSet<>();
        for(String s : gems){
            hash.add(s);
        }
        int size = hash.size();
        
        int left = 0; int right = 0;
        int result = Integer.MAX_VALUE;
        int start = 0;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        while(right < gems.length){
            
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            right++;
            
            while(map.size() == size){
                if(result > right - left){
                    start = left;
                    result = right - left;
                }
                map.put(gems[left], map.get(gems[left]) - 1);
                if(map.get(gems[left]) == 0){
                    map.remove(gems[left]);
                }
                left++;
            }
        }
        
        //System.out.println(result);
        
        answer[0] = start + 1;
        answer[1] = start + result;
        return answer;
    }
}