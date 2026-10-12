import java.util.*;

class Solution {
    
    char[] cmd = {'*' , '+', '-'};
    List<String> list;
    long max;
    
    private long getNum(long a, long b, char c){
        if(c == '*'){
            return a * b;
        }else if(c == '+'){
            return a + b;
        }else{
            return a - b;
        }
    }
    
    private void calculate(List<Character> priority){
        
        List<String> copy = new ArrayList<>(list);
        
        for(Character c : priority){
            for(int i=0; i<copy.size(); i++){
                if(copy.get(i).equals(String.valueOf(c))){
                    long n1 = Long.parseLong(copy.get(i-1));
                    long n2 = Long.parseLong(copy.get(i+1));
                    long result = getNum(n1, n2, c);
                    
                    copy.add(i-1, String.valueOf(result));
                    copy.remove(i);
                    copy.remove(i);
                    copy.remove(i);
                    
                    i--;
                }
            }
        }
        
        max = Math.max(max, Math.abs(Long.parseLong(copy.get(0))));
    }
    
    private void dfs(List<Character> combi, boolean[] visited){
        if(combi.size() == 3){
            calculate(combi);
            return;
        }
        
        for(int i=0; i<3; i++){
            if(!visited[i]){
                combi.add(cmd[i]);
                visited[i] = true;
                dfs(combi, visited);
                visited[i] = false;
                combi.remove(combi.size() - 1);
            }
        }
    }
    
    public long solution(String expression) {
        
        max = 0;
        
        list = new ArrayList<>();
        
        String tmp = "";
        
        for(int i=0; i<expression.length(); i++){
            
            char c = expression.charAt(i);
            
            if(c - '0' >= 0 && c - '0' <= 9){
                tmp += c;
                if(i == expression.length() - 1){
                    list.add(tmp);
                }
            }else{
                list.add(tmp);
                list.add(String.valueOf(c));
                tmp = "";
            }
        }
        
        dfs(new ArrayList<Character>(), new boolean[3]);
        
        return max;
    }
}