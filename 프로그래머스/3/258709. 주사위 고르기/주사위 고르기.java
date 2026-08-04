import java.util.*;

class Solution {
    
    static int n;
    static int[][] dice;
    static boolean[] visitedDice;
    static List<Integer> a;
    static List<Integer> b;
    static List<Integer> sumA;
    static List<Integer> sumB;
    
    static int[] bestAnswer;
    static int bestWin;
    
    static int getLowerCount(int max, List<Integer> sums){
        int left = 0;
        int right = sums.size();
        
        while(left < right){
            
            int mid = (left + right) / 2;
            
            if(sums.get(mid) < max){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        
        return left;
    }
    
    static void calculate(List<Integer> list, int sum, int depth, List<Integer> sums){
        if(depth == list.size()){
            sums.add(sum);
            return;
        }
        
        int next = list.get(depth);
        
        for(int i=0; i<6; i++){
            calculate(list, sum + dice[next][i], depth + 1, sums);
        }
    }
    
    static void select(int num, int cnt){
        
        if(cnt == (n/2)){
            
            a = new ArrayList<>();
            b = new ArrayList<>();
            
            for(int i=0; i<n; i++){
                if(visitedDice[i]){
                    a.add(i);
                }else{
                    b.add(i);
                }
            }
            
            sumA = new ArrayList<>();
            sumB = new ArrayList<>();
            
            calculate(a, 0, 0, sumA);
            calculate(b, 0, 0, sumB);

            Collections.sort(sumB);

            int win = 0;

            for(int max : sumA){
                win += getLowerCount(max, sumB);
            }

            if(bestWin < win){
                for(int i=0; i<a.size(); i++){
                    bestAnswer[i] = a.get(i) + 1;
                    bestWin = win;
                }
            }
            
            return;
        }
        
        for(int i=num; i<n; i++){
            if(!visitedDice[i]){
                visitedDice[i] = true;
                select(i+1, cnt+1);
                visitedDice[i] = false;
            }
        }
    }
    
    public int[] solution(int[][] dice) {
        int[] answer = {};
        
        n = dice.length;
        this.dice = dice;
        visitedDice = new boolean[n];
        
        bestAnswer = new int[n/2];
        bestWin = 0;
        
        select(0, 0);
        
        return bestAnswer;
    }
}