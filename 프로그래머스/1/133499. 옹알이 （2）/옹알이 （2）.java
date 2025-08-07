class Solution {
    String[] pro = {"aya", "ye", "woo", "ma"};
    String[] propro = {"ayaaya", "yeye", "woowoo", "mama"};
    
    public int solution(String[] babbling) {
        int answer = 0;
        for(String s : babbling){
            for(String pp : propro){
                s = s.replace(pp, "cant");
            }
            for(String p : pro){
                s = s.replace(p, " ");
            }
            s = s.replace(" ", "");
            if(s.length() == 0) answer++;
        }
        
        return answer;
    }
}