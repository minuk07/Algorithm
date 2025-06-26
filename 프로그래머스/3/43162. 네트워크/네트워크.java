class Solution {
    int answer = 0;
    int[] visited;
    
    public int solution(int n, int[][] computers) {
        visited = new int[n];
        
        for(int i=0; i<n; i++){
            if(visited[i] == 0){
                dfs(computers, visited, i, n);
                answer++;
            }
            
        }
        
        return answer;
    }
    
    public void dfs(int[][] arr, int[] visited, int x, int n){
        visited[x] = 1;
        for(int i=0; i<n; i++){
            if(arr[x][i] == 1 && visited[i] == 0){
                dfs(arr,visited,i,n);
            }
        }
    }
}