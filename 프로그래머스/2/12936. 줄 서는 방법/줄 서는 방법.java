import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        
        long t = 1;
        
        for(int i=1; i<=n; i++){
            list.add(i);
            t *= i;
        }
        
        k--;
        
        int idx = 0;
        
        while(idx < n){
            t /= n-idx;
            answer[idx] = list.remove((int) (k/t));
            idx++;
            k %= t;
        }
        
        return answer;
    }
}