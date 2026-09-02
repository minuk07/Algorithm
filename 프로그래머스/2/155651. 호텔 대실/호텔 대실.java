import java.util.*;

class Solution {
    
    static class Hotel{
        int start, end;
        
        Hotel(int start, int end){
            this.start = start; this.end = end;
        }
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int[][] book = new int[book_time.length][2];
        
        int idx = 0;
        
        for(String[] b : book_time){
            int start = Integer.parseInt(b[0].replace(":", ""));
            int end = Integer.parseInt(b[1].replace(":","")) + 10;
            
            if(end % 100 >= 60){
                end -= 60;
                end += 100;
            }
            
            book[idx][0] = start; book[idx][1] = end;
            idx++;
        }
        
        Arrays.sort(book, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<Hotel> pq = new PriorityQueue<>((a,b) -> a.end - b.end);
        
        pq.add(new Hotel(book[0][0], book[0][1]));
        answer++;
        
        idx = 1;
        
        while(!pq.isEmpty() && idx <= book.length -1){
            
            Hotel cur = pq.peek();
            int[] next = book[idx];
            
            if(cur.end > next[0]){
                answer++;
                System.out.println(cur.end);
                pq.add(new Hotel(next[0], next[1]));
            }else{
                pq.remove();
                pq.add(new Hotel(next[0], next[1]));
            }
            
            idx++;
        }
        
        return answer;
    }
}