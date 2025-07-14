import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int result = 0;
        List<Integer> list = new ArrayList<>();
        
        for(int i=1; i<=number; i++){
            int cnt=0;
            for(int j=1; j*j<=i; j++){
                if(i%j==0){
                    cnt+=2;
                }
                if(j*j == i) cnt--;
            }
            list.add(cnt);
        }
        
        
        
        for(int i=0; i<list.size(); i++){
            if(list.get(i) > limit){
                result += power;
            }
            else{
                result += list.get(i);
            }
            System.out.print(list.get(i) + " ");
        }
        
        return result;
    }
}