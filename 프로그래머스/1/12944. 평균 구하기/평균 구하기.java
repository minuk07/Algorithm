class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        
        for(double a:arr){
            answer += a;
        }
        
        return answer / arr.length;
    }
}