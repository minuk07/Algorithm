class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int n = sticker.length;
        
        int[] dp = new int[n];
        
        if(n == 1) return sticker[0];
        if(n == 2) return (Math.max(sticker[0], sticker[1]));

        dp[0] = sticker[0];
        dp[1] = sticker[0];
        
        for(int i=2; i<n-1; i++){
            dp[i] = Math.max(dp[i-2] + sticker[i], dp[i-1]);
        }
        
        answer = dp[n-2];
        
        dp = new int[n];
        
        dp[0] = 0;
        dp[1] = sticker[1];
        
        for(int i=2; i<n; i++){
            dp[i] = Math.max(dp[i-2] + sticker[i], dp[i-1]);
        }
        
        answer = Math.max(answer, dp[n-1]);

        return answer;
    }
}