import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        
        Arrays.sort(numbers);
        
        int len = numbers.length;
        
        int a = numbers[len-1];
        int b = numbers[len-2];
        answer = a*b;
        return answer;
    }
}