import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        
        List<Set<Integer>> result = new ArrayList<>();
        
        for(int i=0; i<=8; i++){
            result.add(new HashSet<>());
        }
        
        result.get(1).add(N);
        
        for(int i=2; i<=8; i++){
            Set<Integer> cur = result.get(i);
            
            for(int j=1; j<i; j++){
                Set<Integer> set1 = result.get(j);
                Set<Integer> set2 = result.get(i-j);
                
                for(int s1 : set1){
                    for(int s2 : set2){
                        cur.add(s1 + s2);
                        cur.add(s1 - s2);
                        cur.add(s1 * s2);
                        
                        if(s1 != 0 && s2 != 0){
                            if(s1 % s2 == 0){
                                cur.add(s1 / s2);
                            }
                        }
                    }
                }
            }
            
            int tmp = N;
            for(int j=1; j<i; j++){
                tmp *= 10;
                tmp += N;
            }
            cur.add(tmp);
        }
        
        for(int i=1; i<=8; i++){
            if(result.get(i).contains(number)){
                return i;
            }
        }
        
        return answer;
    }
}