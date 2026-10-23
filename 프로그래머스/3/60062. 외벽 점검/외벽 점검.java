import java.util.*;

class Solution {
    
    int n;
    int[] weak;
    int[] dist;
    int[] weakCase;
    boolean[] visited;
    int answer;
    
    private void check(List<Integer> list){
        
        for(int i=0; i<weak.length; i++){
            int start = i;
            int end = i + weak.length - 1;
            int cnt = 1;
            int idx = 0;
            int limit = weakCase[start] + list.get(idx);
            boolean flag = true;
            for(int j=start+1; j<=end; j++){
                if(weakCase[j] > limit){
                    idx++;
                    if(idx >= list.size()){
                        flag = false;
                        break;
                    }
                    limit = weakCase[j] + list.get(idx);
                    cnt++;
                }
            }
            if(flag) answer = Math.min(cnt, answer);
        }
    }
    
    private void dfs(int len, List<Integer> list){
        if(len == dist.length){
            check(list);
            return;
        }
        
        for(int i=0; i<dist.length; i++){
            if(!visited[i]){
                visited[i] = true;
                list.add(dist[i]);
                dfs(len+1, list);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
    
    private void makeWeakCase(){
        for(int i=0; i<weak.length; i++){
            weakCase[i] = weak[i];
            weakCase[i + weak.length] = weak[i] + n;
        }
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        answer = 9;
        
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        
        weakCase = new int[weak.length*2];
        makeWeakCase();
        
        visited = new boolean[dist.length];
        dfs(0, new ArrayList<>());
        
        return answer != 9 ? answer : -1;
    }
}