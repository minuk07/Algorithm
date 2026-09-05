import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;

        int myScore1 = scores[0][0];
        int myScore2 = scores[0][1];
        int mySum = myScore1 + myScore2;
        
        Arrays.sort(scores, (a,b) -> {
            if(a[0] != b[0]){
                return b[0] - a[0];
            }
            
            return a[1] - b[1];
        });
        
        int maxPeerReview = 0;
        
        for(int[] score : scores){
            
            if(score[1] < maxPeerReview){
                if(score[0] == myScore1 && score[1] == myScore2){
                    return -1;
                }
            }else{
                maxPeerReview = Math.max(maxPeerReview, score[1]);
                if(score[1] + score[0] > mySum) answer++;
                
            }
        }
            
        
        return answer;
    }
}