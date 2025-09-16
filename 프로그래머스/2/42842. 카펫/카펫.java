class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int area = brown + yellow;
        
        for(int i=3; i<=area; i++){
            if(area % i == 0){
                int h = area / i;
                if(yellow == (h-2) * (i-2)){
                    answer[0] = i;
                    answer[1] = h;
                }
            }
        }
        
        return answer;
    }
}