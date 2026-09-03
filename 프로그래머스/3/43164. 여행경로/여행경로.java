import java.util.*;

class Solution {
    
    static int n;
    static boolean[] visited;
    static ArrayList<String> list = new ArrayList<>();
    
    static void dfs(String start, String routes, int len, String[][] tickets){
        
        if(len == n){
            list.add(routes);
            return;
        }
        
        for(int i=0; i<n; i++){
            if(!visited[i] && start.equals(tickets[i][0])){
                visited[i] = true;
                dfs(tickets[i][1], routes + " " + tickets[i][1], len + 1, tickets);
                visited[i] = false;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        
        n = tickets.length;
        visited = new boolean[n];
        
        dfs("ICN", "ICN", 0, tickets);
        
        Collections.sort(list);
        
        return list.get(0).split(" ");
    }
}