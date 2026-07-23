import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = (arr.length / 2) + 1;
        
        int[] num = new int[n];
        boolean[] op = new boolean[n-1];
        
        int numIdx = 0;
        int opIdx = 0;
        
        for(int i=0; i<arr.length; i++){
            if(arr[i].equals("+")){
                op[opIdx++] = true;
            }else if(arr[i].equals("-")){
                op[opIdx++] = false;
            }else{
                num[numIdx++] = Integer.parseInt(arr[i]);
            }
        }
        
        int[][] maxDp = new int[n][n];
        int[][] minDp = new int[n][n];
        
        for(int i=0; i<n; i++){
            Arrays.fill(maxDp[i], Integer.MIN_VALUE);
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
            
            maxDp[i][i] = num[i];
            minDp[i][i] = num[i];
        }
        
        for(int len=2; len<=n; len++){
            for(int start=0; start+len-1<n; start++){
                int end = start + len - 1;
                
                for(int k=start; k<end; k++){
                    if(op[k]){ // +
                        maxDp[start][end] = Math.max(maxDp[start][end], maxDp[start][k] + maxDp[k+1][end]);
                        minDp[start][end] = Math.min(minDp[start][end], minDp[start][k] + minDp[k+1][end]);
                    }else{ // -
                        maxDp[start][end] = Math.max(maxDp[start][end], maxDp[start][k] - minDp[k+1][end]);
                        minDp[start][end] = Math.min(minDp[start][end], minDp[start][k] - maxDp[k+1][end]);
                    }
                }
            }
        }
        
        return maxDp[0][n-1];
    }
}