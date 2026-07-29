import java.util.*;

class Solution {
    
    static int n;
    static Map<Long, Long> map;
    
    static long find(long room){
        if(!map.containsKey(room)){
            map.put(room, room+1);
            return room;
        }
        
        long next = find(map.get(room));
        map.put(room, next);
        return next;
    }
    
    public long[] solution(long k, long[] room_number) {
        n = room_number.length;
        long[] answer = new long[n];

        map = new HashMap<>();
        
        for(int i=0; i<n; i++){
            answer[i] = find(room_number[i]);
        }
        
        return answer;
    }
}