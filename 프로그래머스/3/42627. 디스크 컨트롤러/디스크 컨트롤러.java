import java.util.*;

class Solution {
    
    static class Job{
        
        int idx, start, take;
        
        Job(int idx, int start, int take){
            this.idx = idx; this.start = start; this.take = take;
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a,b) -> a[0] - b[0]);
        
        PriorityQueue<Job> pq = new PriorityQueue<>((a,b) -> {
            if(a.take != b.take){
                return a.take - b.take;
            }
            
            if(a.start != b.start){
                return a.start - b.start;
            }
            
            return a.idx - b.idx;
        });
        
        int cnt = 0;
        int idx = 0;
        int time = 0;
        
        while(cnt < jobs.length){
            
            while(idx < jobs.length && time >= jobs[idx][0]){
                pq.add(new Job(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if(pq.isEmpty()){
                time++;
            }else{
                Job cur = pq.poll();
                
                time += cur.take;
                answer += (time - cur.start);
                cnt++;
            }
        }
        
        return answer / jobs.length;
    }
}