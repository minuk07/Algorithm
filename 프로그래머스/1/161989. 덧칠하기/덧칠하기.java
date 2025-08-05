class Solution {
    public int solution(int n, int m, int[] section) {
        int cur = 0; 
        int cnt = 0;
        
        for(int i=0; i<section.length; i++){
            if(cur <= section[i]){
                cnt++;
                cur = section[i] + m;
            }
        }
        
        return cnt;
    }
}