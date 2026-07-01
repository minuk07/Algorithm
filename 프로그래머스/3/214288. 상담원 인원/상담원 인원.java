import java.util.*;

class Solution {
    
    static final int INF = 1000000000;
    
    static class Customer{
        int start, take, type;
        Customer(int start, int take, int type){
            this.start = start; this.take = take; this.type = type;
        }
    }
    
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        
        List<List<Customer>> list = new ArrayList<>();
        
        for(int i=0; i<=k; i++){
            list.add(new ArrayList<>());
        }
        
        for(int[] req : reqs){
            list.get(req[2]).add(new Customer(req[0], req[1], req[2]));
        }
        
        int[][] wait = new int[k+1][n-k+2];
        
        for(int i=1; i<=k; i++){
            for(int j=1; j<=n-k+1; j++){
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                
                for(Customer c : list.get(i)){
                    if(pq.size() < j){
                        pq.add(c.start + c.take);
                    }else{
                        int time = pq.poll();
                        int waitTime = time - c.start;
                        
                        if(waitTime > 0){
                            wait[i][j] += waitTime;
                            pq.add(time + c.take);
                        }
                        else{
                            pq.add(c.start + c.take);
                        }
                    }
                }
            }
            
            
        }
        answer = dfs(n-k+1, wait, 1, k);
        
        return answer;
    }
    
     static int dfs(int remain, int[][] wait, int type, int k){
            if(type > k) return 0;
            int sum = INF;
            for(int i=1; i<= remain; i++){
                sum = Math.min(sum, dfs(remain-i+1, wait, type+1, k) + wait[type][i]);
            }
            return sum;
        }
}