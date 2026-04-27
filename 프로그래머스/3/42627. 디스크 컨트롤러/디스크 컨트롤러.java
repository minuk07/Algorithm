import java.util.*;

class Solution {
    
    static class Disk{
        int idx, start, take;
        
        Disk(int idx, int start, int take){
            this.idx = idx; this.start = start; this.take = take;
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        int sum = 0;
        
        int[][] disks = new int[jobs.length][3];
        
        for(int i=0; i<jobs.length; i++){
            disks[i][0] = i;
            disks[i][1] = jobs[i][0];
            disks[i][2] = jobs[i][1];
        }
        
        Arrays.sort(disks, (a,b) -> a[1] - b[1]);
        
        PriorityQueue<Disk> pq = new PriorityQueue<>((a,b) -> {
            if(a.take != b.take){
                return a.take - b.take;
            }
            if(a.start != b.start){
                return a.start - b.start;
            }
            
            return a.idx - b.idx;
        });
        
        int idx = 0;
        int time = 0;
        int count = 0;
        
        while(count < disks.length){
            
            while(idx < disks.length && disks[idx][1] <= time){
                pq.add(new Disk(disks[idx][0], disks[idx][1], disks[idx][2]));
                idx++;
            }
            
            if(!pq.isEmpty()){
                Disk cur = pq.poll();
                
                time += cur.take;
                
                sum += (time - cur.start);
                
                count++;
            }
            else{
                time = disks[idx][1];
            }
        }
        
        answer = sum / jobs.length ;
        
        return answer;
    }
}