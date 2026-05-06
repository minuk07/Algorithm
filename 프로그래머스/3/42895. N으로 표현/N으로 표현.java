import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        List<Set<Integer>> list = new ArrayList<>();
        
        for(int i=0; i<9; i++){
            list.add(new HashSet<>());
        }
        
        list.get(1).add(N);
        
        for(int i=2; i<9; i++){
            Set<Integer> cur = list.get(i);
            
            for(int j=1; j<=i; j++){
                Set<Integer> set1 = list.get(j);
                Set<Integer> set2 = list.get(i-j);
                
                for(int s1 : set1){
                    for(int s2 : set2){
                        cur.add(s1 + s2);
                        cur.add(s1 - s2);
                        cur.add(s1 * s2);
                        
                        if(s1 != 0 && s2 != 0){
                            cur.add(s1 / s2);
                        }
                    }
                }
            }
            
            int temp = N;
            for(int j=1; j<i; j++){
                temp = temp * 10;
                temp += N;
            }
            cur.add(temp);
        }
        
        for(int i=1; i<9; i++){
            if(list.get(i).contains(number)){
                return i;
            }
        }
        
        return -1;
    }
}