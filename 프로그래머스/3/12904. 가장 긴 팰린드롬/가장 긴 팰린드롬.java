import java.util.*;

class Solution
{
    static int[][] dp;
    
    static int isSame(int start, int end, String s){
        if(s.charAt(start) != s.charAt(end)) return -1;
        else{
            if(start == end) return 1;
            else if(end - start == 1) return 2;
            else{
                if(dp[start+1][end-1] == -1) return -1;
                else return dp[start+1][end-1] + 2;
            }
        }
    }
    
    public int solution(String s)
    {
        int answer = 0;
        int len = s.length();

        dp = new int[len][len];
        
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        for(int i=0; i<len; i++){
            dp[i][i] = 1;
        }
        
        for(int i=len-1; i>=0; i--){
            for(int j=i; j<len; j++){
                dp[i][j] = Math.max(dp[i][j], isSame(i, j, s));
                answer = Math.max(answer, dp[i][j]);
            }
        }
        

        return answer;
    }
}