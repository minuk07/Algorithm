class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        int num = s / n;
        int r = s % n;
        
        if(num == 0) return new int[]{-1};
        
        for(int i=0; i<n; i++){
            answer[i] = num;
        }
        
        int idx = n-1;
        
        while(r > 0){
            answer[idx] += 1;
            idx--;
            r--;
        }
        
        return answer;
    }
}