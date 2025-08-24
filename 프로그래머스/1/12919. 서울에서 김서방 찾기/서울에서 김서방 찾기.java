class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        
        String index = "";
        
        for(int i=0; i<seoul.length; i++){
            if(seoul[i].equals("Kim")){
                index = String.valueOf(i);
            }
        }
        
        return "김서방은 " + index+"에 있다";
    }
}