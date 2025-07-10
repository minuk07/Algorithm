import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int n = nums.length;
        HashMap<Integer,Integer> h = new HashMap<Integer,Integer>();
        
        for(int num:nums){
            if(h.containsKey(num)){
                h.put(num, h.get(num) + 1);
                continue;
            }
            h.put(num, 0);
        }
        
        return solve(h.size(), n);
    }
    
    public int solve(int kind, int n){
        int canGet = n/2;
        
        if(canGet >= kind){
            return kind;
        }
        else{
            return canGet;
        }
    }
}