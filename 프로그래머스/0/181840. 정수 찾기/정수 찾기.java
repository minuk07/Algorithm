class Solution {
    public int solution(int[] num_list, int n) {
        int answer = 0;
        
        for (int a : num_list)
        {
            if(a == n){
                answer = 1;
                break;
            }
        }
        return answer;
    }
}