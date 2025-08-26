import java.util.*;

class Solution {
    public long solution(long n) {
        String str = String.valueOf(n);

        char[] digits = str.toCharArray();

        Arrays.sort(digits);

        StringBuilder sb = new StringBuilder(new String(digits));
        sb.reverse();

        long answer = Long.parseLong(sb.toString());
        
        return answer;
    }
}