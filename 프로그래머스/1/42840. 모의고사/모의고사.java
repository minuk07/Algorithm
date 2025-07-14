import java.util.*;

class Solution {
    int[] one = {1,2,3,4,5};
    int[] two = {2,1,2,3,2,4,2,5};
    int[] three = {3,3,1,1,2,2,4,4,5,5};
    
    public int[] solution(int[] answers) {
        int[] answer;
        HashMap<Integer, Integer> hash = new HashMap<Integer,Integer>();
        List<Integer> list = new ArrayList<>();
        
        hash.put(1,0);
        hash.put(2,0);
        hash.put(3,0);
        
        for(int i=0; i<answers.length; i++){
            if(answers[i] == one[i%one.length]){
                hash.put(1,hash.get(1) + 1);
            }
            if(answers[i] == two[i%two.length]){
                hash.put(2,hash.get(2) + 1);
            }
            if(answers[i] == three[i%three.length]){
                hash.put(3,hash.get(3) + 1);
            }
        }
        
        int max = 0;
        for(int i=1; i<=3; i++){
            //System.out.println(i+": " + hash.get(i));
            if(max < hash.get(i)){
                list.clear();
                list.add(i);
                max = hash.get(i);
            }
            else if(max == hash.get(i)){
                list.add(i);
            }
            else{
                continue;
            }
        }
        
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
            
        }
        
        return answer;
    }
}