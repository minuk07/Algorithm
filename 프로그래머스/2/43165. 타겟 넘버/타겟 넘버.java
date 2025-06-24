import java.util.*;

class Solution {
    int result = 0;
    
    public int solution(int[] numbers, int target) {
        
        dfs(numbers, 0, target, 0);
        
        return result;
    }
    
    public void dfs(int[] arr, int depth, int target, int sum){
        if(depth == arr.length){
                        
            if(sum == target){
                result++;
            }
        }
        else{
            depth++;
            dfs(arr, depth, target, sum + arr[depth-1]);
            dfs(arr, depth, target, sum - arr[depth-1]);
        }
    }
}