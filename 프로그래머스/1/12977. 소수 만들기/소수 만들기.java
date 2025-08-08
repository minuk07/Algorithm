class Solution {
    public int solution(int[] nums) {
        
        int cnt = 0;
        int n = nums.length;
        
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                for(int k=j+1; k<n; k++){
                    if(check(nums[i],nums[j],nums[k])) cnt++;
                }
            }
        }
        return cnt;
    }
    
    public boolean check(int a, int b, int c){
        int sum = a + b + c;
        
        for(int i=2; i<sum; i++){
            if(sum % i == 0) return false;
        }
        
        return true;
    }
}