class Solution {
    public int solution(int n) {
        int sum = 0;
        
        for(int a =0; a<=n; a++){
            if(a%2 == 0){
                sum += a;
            }
        }
        
        return sum;
    }
}