class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int section = (2*w) + 1;
        
        for(int i=0; i<=stations.length; i++){
            int len = 0;
            
            
            if(i==0){
                len = stations[i] - w - 1;
            }
            else if (i == stations.length){
                len = n - (stations[i-1] + w);
            }
            else{
                len = (stations[i] - w) - (stations[i-1] + w) - 1;
            }
            
            if(len > 0) {
                int cnt = len / section;
                int r = len % section;
                
                answer += cnt;
                if(r != 0) answer++;
            }
        }

        return answer;
    }
}