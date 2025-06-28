class Solution {
    public int solution(int angle) {
        int d;
        
        if(angle<90) d = 1;
        else if (angle == 90) d = 2;
        else if (angle > 90 && angle <180) d = 3;
        else d = 4 ;
        
        return d;
    }
}