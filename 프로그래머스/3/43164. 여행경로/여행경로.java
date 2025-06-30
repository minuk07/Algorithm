import java.util.*;

class Solution {
    List<String> route = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        
        int n = tickets.length;
        visited = new boolean[n];
        
        dfs("ICN", "ICN", 0, n, tickets);
        
        //System.out.print(route.toString());
        
        Collections.sort(route);
        String[] answer = route.get(0).split(" ");
        return answer;
    }
    
    public void dfs(String start, String path, int depth, int n, String[][] tickets){
        if(depth == n){
            route.add(path);
            return;
        }
        
        for(int i=0; i<n; i++){
            if(!visited[i] && start.equals(tickets[i][0])){
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], depth+1 , n , tickets);
                visited[i] = false;  //다 끝나고 다른 경로가 있을 수 도 있으니 설정 (백트래킹)
            }
        }
    }
}