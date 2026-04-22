import java.util.*;

class Solution {
    
    static List<List<Integer>> graph;
    static int[] time;
    
    static void bfs(int start, int n){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        visited[start] = true;
        q.add(start);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int i=0; i<graph.get(cur).size(); i++){
                int ad = graph.get(cur).get(i);
                if(visited[ad]) continue;
                q.add(ad);
                visited[ad] = true;
                time[ad] = time[cur] + 1;
            }
        }
        
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        graph = new ArrayList<>();
        
        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] road : roads){
            int a = road[0]; int b = road[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        time = new int[n+1];
        bfs(destination, n);
        
        for(int i=0; i<sources.length; i++){
            
            if(destination == sources[i]) answer[i] = 0;
            else if(time[sources[i]] == 0) answer[i] = -1;
            else answer[i] = time[sources[i]];
        }
        
        return answer;
    }
}