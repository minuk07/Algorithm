import java.util.*;

class Solution {
    
    Map<String, Integer> map = new HashMap<>();
    
    public boolean solution(String[] phone_book) {
        
        for(int i=0; i<phone_book.length; i++){
            map.put(phone_book[i], i);
        }
        
        for(int i=0; i<phone_book.length; i++){
            for(int j=0; j<phone_book[i].length(); j++){
                if(map.containsKey(phone_book[i].substring(0,j)))
                    return false;
            }
        }
        
        return true;
    }
}