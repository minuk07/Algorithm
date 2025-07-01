import java.util.*;

class Solution {
    List<Integer> d = new ArrayList<>();
    int result = 0;
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        int n = words.length;
        visited = new boolean[n];
        dfs(begin, target, 0, n, words, begin);
        
        
        return result;
    }
    
    public boolean compareString(String a, String b){
        if(a.length() != b.length()){
            return false;
        }
        int diff = 0;
        
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                diff++;
            }
        }
        if(diff == 1) return true;
        else return false;
    }
    
    public void dfs(String start, String target, int depth, int n, String[] words, String path){
        //System.out.println("start : " + start + " target : " + target);
        
        if(start.equals(target)){
            //System.out.print("same");
            result = depth;
            return;
        }
        
        for(int i=0; i<n; i++){
            if(!visited[i] && compareString(start, words[i])){
                //System.out.println("start : " + start + " next : " + words[i]);
                visited[i] = true;
                dfs(words[i], target, depth+1, n, words, path + " " + words[i]);
                visited[i] = false;
            }
        }
    }
}