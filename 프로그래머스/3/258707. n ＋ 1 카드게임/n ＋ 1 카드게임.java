import java.util.*;

class Solution {
    
    static int n;
    static Set<Integer> hand;
    static Set<Integer> tmp;
    static int[] cards;
    
    public int solution(int coin, int[] cards) {
        int answer = 0;
        
        n = cards.length;
        this.cards = cards;
        
        hand = new HashSet<>();
        tmp = new HashSet<>();
        
        for(int i=0; i<n/3; i++){
            hand.add(cards[i]);
        }
        
        int idx = n/3;
        
        while(true){
            boolean flag = false;
            answer++;
            
            if(idx >= n) break;
            
            tmp.add(cards[idx++]);
            tmp.add(cards[idx++]);
            
            for(int card : hand){
                int target = n+1 - card;

                if(hand.contains(card) && hand.contains(target)){

                    hand.remove(card);
                    hand.remove(target);

                    flag = true;
                    break;
                }
            }
            
            if(flag) continue;
            
            if(coin >= 1){
                for(int card : hand){
                    int target = n+1 - card;
                    
                    if(hand.contains(card) && tmp.contains(target)){
                        flag = true;
                        hand.remove(card);
                        tmp.remove(target);
                        coin--;
                        break;
                    }
                }
            }
            
            if(flag) continue;
            
            if(coin >= 2){
                for(int card : tmp){
                    int target = n+1 - card;
                    
                    if(tmp.contains(card) && tmp.contains(target)){
                        flag = true;
                        tmp.remove(card);
                        tmp.remove(target);
                        coin -= 2;
                        break;
                    }
                }
            }
            
            if(!flag){
                break;
            }
        }
        
        return answer;
    }
}