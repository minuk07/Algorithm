class Solution {
    String abc = "abcdefghij";
    
    public String solution(int age) {
        String answer = "";
        String ageStr = age + "";
        
        for(int i=0; i<ageStr.length(); i++){
            answer += abc.charAt(ageStr.charAt(i) - '0');
        }
        
        return answer;
    }
}