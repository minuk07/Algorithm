import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int[][] book = new int[book_time.length][2];
        
        for(int i=0; i<book_time.length; i++){
            book[i][0] = Integer.parseInt(book_time[i][0].replace(":",""));
            book[i][1] = Integer.parseInt(book_time[i][1].replace(":", "")) + 10;
            
            if(book[i][1] % 100 >= 60) book[i][1] += 40;
        }
        
        Arrays.sort(book, (a,b) -> Integer.compare(a[0], b[0]));
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        
        for(int[] b : book){
            if(pq.isEmpty()) pq.add(b);
            else{
                int tmp[] = pq.peek();
                
                int end = tmp[1];
                if(b[0] >= end) pq.poll();
                pq.add(b);
                
            }
        }
                                                                
        return pq.size();
    }
}