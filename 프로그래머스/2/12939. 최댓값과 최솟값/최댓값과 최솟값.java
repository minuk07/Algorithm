import java.util.*;

class Solution {
    public String solution(String s) {
        
        List<Integer> list = new ArrayList<>();
        
        for (String numStr : s.split(" ")) {
            if (!numStr.isEmpty()) { 
                list.add(Integer.parseInt(numStr));
            }
        }
        
        // for(int i=0; i<list.size(); i++){
        //     System.out.print(list.get(i) + " ");
        // }
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<list.size(); i++){
            if(max < list.get(i)) max = list.get(i);
            if(min > list.get(i)) min = list.get(i);
        }
        
        String answer = String.valueOf(min) + " " + String.valueOf(max);
        
        return answer;
    }
}