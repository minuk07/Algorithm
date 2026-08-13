class Solution {
    
    static int n, a, b;
    static int[] g, s, w, t;
    
    static boolean canGetGoldandSilver(long hour){
        
        long gold = 0;
        long silver = 0;
        long total = 0;
        
        for(int i=0; i<n; i++){
            
            long cnt = (hour / (t[i] * 2L));
            if(hour % (t[i] * 2L) >= t[i]) cnt++;
            
            long limit = w[i] * cnt; //옮길 수 있는 최대 광물량
            gold += Math.min(limit, g[i]);
            silver += Math.min(limit, s[i]);
            total += Math.min(g[i] + s[i], limit);
        }
                
        return (gold >= a && silver >= b && (total >= (long)(a + b)));
    }
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        
        this.a = a; this.b = b;
        this.g = g; this.s = s; this.w = w; this.t = t;
        
        n = g.length;
        
        long right = 1_000_000_000_000_000L;
        long left = 0;
        
        while(left < right){
            long mid = (right + left) / 2;
            
            if(canGetGoldandSilver(mid)){
                right = mid;
            }else{ 
                left = mid + 1;
            }
        }
        
        return left;
    }
}