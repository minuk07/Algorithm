import java.util.*;

class Solution {
    
    int n, m;
    int[][] users;
    int[] emoticons;
    
    int bestSum, bestPlus;
    
    private void calculate(List<Integer> list){
        
        int sum = 0;
        int plus = 0;
        
        for(int[] user : users){
            
            int userWant = user[0];
            int userPrice = user[1];
            
            int tmp = 0;
            
            for(int i=0; i<m; i++){
                int emoticon = emoticons[i];
                int percent = list.get(i);
                int emoPrice = emoticon * (100 - percent) / 100;
                
                if(userWant <= percent){
                    tmp += emoPrice;
                }
            }
            
            if(tmp >= userPrice){
                plus++;
            }else{
                sum += tmp;
            }
        }
        
        if(plus > bestPlus){
            bestPlus = plus;
            bestSum = sum;
        }else if(plus == bestPlus){
            if(bestSum < sum){
                bestSum = sum;
            }
        }
    }
    
    private void dfs(int depth, List<Integer> list){
        if(depth == m){
            calculate(list);
            return;
        }
        
        for(int i=10; i<=40; i+=10){
            list.add(i);
            dfs(depth + 1, list);
            list.remove(list.size() - 1);
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        this.users = users;
        this.emoticons = emoticons;
        
        n = users.length;
        m = emoticons.length;
        
        bestSum = 0;
        bestPlus = 0;
        dfs(0, new ArrayList<>());
        
        return new int[]{bestPlus, bestSum};
    }
}