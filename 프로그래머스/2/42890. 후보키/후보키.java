import java.util.*;

class Solution {
    
    static int col, row;
    static boolean[] visited;
    static List<Integer> list;
    static String[][] relation;
    static List<List<Integer>> result;
    
    static void printList(List<Integer> list){
        for(int l : list){
            System.out.print(l + " ");
        }
        System.out.println();
    }
    
    static boolean isMinimum(List<Integer> list){
        
        for(List<Integer> l : result){
            if(list.containsAll(l)){
                return false;
            }
        }
        
        return true;
    }
    
    static boolean isUnique(List<Integer> list){
        Set<String> set = new HashSet<>();
        
        for(int i=0; i<row; i++){
            
            String tmp = "";
            int idx = 0;
            
            for(int l : list){
                tmp += relation[i][l];
            }
            
            set.add(tmp);
        }
        
        if(set.size() == row){
            return true;
        }
        
        return false;
    }
    
    static void dfs(int idx, int target, int len, List<Integer> list){
        
        if(idx >= col) return;
        
        if(target == len){
            if(isUnique(list)){
                
                if(isMinimum(list)){
                    result.add(new ArrayList<>(list));
                }
            }
            return;
        }
        
        for(int i=idx+1; i<col; i++){
            
            list.add(i);
            dfs(i, target, len+1, list);
            list.remove(list.size() - 1);
        }
    }
    
    public int solution(String[][] relation) {
        
        this.relation = relation;
        
        col = relation[0].length;
        row = relation.length;
        
        result = new ArrayList<>();
        
        for(int target=1; target<=col; target++){
            
            visited = new boolean[col];
            list = new ArrayList<>();
            
            for(int idx=0; idx<col; idx++){
                
                list.add(idx);
                dfs(idx, target, 1, list);
                
                list.remove(0);
            }
        }
        
        return result.size();
    }
}