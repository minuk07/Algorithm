class Solution {
    boolean solution(String s) {
        int pcnt = 0;
        int ycnt = 0;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'p' || s.charAt(i) == 'P') pcnt++;
            if(s.charAt(i) == 'y' || s.charAt(i) == 'Y') ycnt++;
        }
        
        if(pcnt == ycnt) return true;
        else return false;
    }
}