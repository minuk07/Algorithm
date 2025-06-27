class Solution {
    public int solution(int n, int k) {
        int sum = 0;
        
        sum = n*12000+k*2000;
        int service = n/10;
        
        sum -= service*2000;
        
        return sum;
    }
}