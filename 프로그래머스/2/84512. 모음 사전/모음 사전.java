import java.util.*;

class Solution {
    
    public String[] str = {"A", "E", "I", "O", "U"};
    public List<String> dic = new ArrayList<>();

    public void dfs(String sentence){
        
        dic.add(sentence);
        
        if(sentence.length() == 5) return;
        
        for(int i=0; i<str.length; i++){
            dfs(sentence + str[i]);
        }
    }
    
    
    public int solution(String word) {
        int answer = 0;
        
        dfs("");
        
        for(int i=0; i<dic.size(); i++){
            if(dic.get(i).equals(word)){
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}