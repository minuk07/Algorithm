import java.util.*;

class Solution {
    
    public static Map<Integer, Integer> me = new HashMap<>();
    public static Map<Integer, Integer> bro = new HashMap<>();
    
    public int isContain(int key, Map<Integer, Integer> map){
        if(map.containsKey(key)){
            return map.get(key);
        }
        else{
            return 0;
        }
    }
    
    public int solution(int[] topping) {
        int answer = 0;
        
        for(int i=0; i<topping.length; i++){
            me.put(topping[i], isContain(topping[i], me) + 1);
        }
        
        for(int t : topping){
            me.put(t, me.get(t) - 1);
            
            if(me.get(t).equals(0)){
                me.remove(t);
            }
            
            bro.put(t, isContain(t, me) + 1);
            
            if(bro.size() == me.size()){
                answer++;
            }
        }
        
        return answer;
    }
}