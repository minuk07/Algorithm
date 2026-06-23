import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        
        long[] ban = new long[bans.length];
        
        for(int i=0; i<bans.length; i++){
            ban[i] = getCnt(bans[i]);
        }
        
        Arrays.sort(ban);
        long target = n;
        
        for(long b : ban){
            if(b <= target){
                target++;    
            }
        }
        
        long remain = target;
        int len = 1;
        long num = 26;
        
        while(remain > num){
            remain -= num;
            len++;
            num *= 26;
        }
        
        return getStr(remain, len);
    }
    
    static long getCnt(String str){
        
        long cnt = 0;
        long num = 26;
        
        for(int i=1; i<str.length(); i++){
            cnt += num;
            num *= 26;
        }
        
        long idx = 0;
        
        for(int i=0; i<str.length(); i++){
            int temp = str.charAt(i) - 'a';
            idx = idx * 26 + temp;
        }
        
        return cnt + idx + 1;
    }
    
    static String getStr(long remain, int len){
        
        remain--;
        
        char[] result = new char[len];
        
        for(int i=len-1; i >=0; i--){
            int temp = (int)(remain % 26);
            result[i] = (char)('a' + temp);
            remain /= 26;
        }
        
        return new String(result);
    }
}