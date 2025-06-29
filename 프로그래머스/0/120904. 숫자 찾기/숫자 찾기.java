class Solution {
    public int solution(int num, int k) {
        String str = String.valueOf(num);
        int answer = -1;
        
        for(int i=0; i<str.length(); i++){
            int a = str.charAt(i) - '0';
            if(a == k){
                answer = i+1;
                return answer;
            }
        }
        return answer;
    }
}