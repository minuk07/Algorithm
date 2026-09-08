class Solution {
    
    static int n, m;
    static int[][] users;
    static int[] emoticons;
    
    static int[] discount;
    static int maxPlus = 0;
    static int maxValue = 0;
    
    static void calculate(){
        
        
        int tmpPlus = 0;
        int tmpValue = 0;
        
        for(int[] user : users){
            int ratio = user[0];
            int limit = user[1];
            int myValue = 0;
            
            for(int j=0; j<m; j++){
                if(discount[j] >= ratio){
                    myValue += (int)(emoticons[j] * ((100-discount[j]) * 0.01));
                }
            }
            
            if(myValue >= limit){
                tmpPlus++;
            }else{
                tmpValue += myValue;
            }
            
        }
    
        
        if(tmpPlus > maxPlus){
            maxPlus = tmpPlus;
            maxValue = tmpValue;
        }else if(tmpPlus == maxPlus){
            maxValue = Math.max(maxValue, tmpValue);
        }
    }
    
    static void dfs(int cnt, int start){
        if(cnt == m){
            calculate();
        }
        
        for(int i=start; i<m; i++){
            for(int j=10; j<=40; j+=10){
                if(discount[start] == 0){
                    discount[i] = j;
                    dfs(cnt + 1, i + 1);
                    discount[i] = 0;
                }
            }
        }
    }
    
        
        
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        n = users.length;
        m = emoticons.length;
        
        this.users = users;
        this.emoticons = emoticons;
        
        discount = new int[m];
        
        dfs(0, 0);
        
        answer[0] = maxPlus;
        answer[1] = maxValue;
        
        return answer;
    }
}