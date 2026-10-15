import java.util.*;

class Solution {
    
    class Disk{
        int idx, start, take;
        
        Disk(int idx, int start, int take){
            this.idx=idx; this.start=start; this.take=take;
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a,b) -> a[0] - b[0]);
        
        PriorityQueue<Disk> pq = new PriorityQueue<>((a,b) -> {
            if(a.take != b.take) return a.take - b.take;
            
            if(a.start != b.start) return a.start - b.start;
            
            return a.idx - b.idx;
        });
        
        
        pq.add(new Disk(0, jobs[0][0], jobs[0][1]));
        
        int idx = 1;
        int time = jobs[0][0];
        
        while(idx <= jobs.length - 1){
            
            while(idx <= jobs.length - 1 && jobs[idx][0] <= time){
                int start = jobs[idx][0];
                int take = jobs[idx][1];
                pq.add(new Disk(idx, start, take));
                
                idx++;
            }
            
            if(!pq.isEmpty()){
                Disk cur = pq.poll();
                time += cur.take;
                answer += (time - cur.start);
            }else{
                time++;
            }
            
        }
        
        while(!pq.isEmpty()){
            Disk cur = pq.poll();
            time += cur.take;
            answer += (time - cur.start);
        }
        
        return answer / jobs.length;
    }
}