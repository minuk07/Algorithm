import java.util.*;

class Solution {
    
    static class Task{
        private String name;
        private int start;
        private int time;
        
        public Task(String name, int start, int time){
            this.name = name; this.start = start; this.time = time;
        }
    }
    
    public String[] solution(String[][] plans) {
        
        List<String> list = new LinkedList<>();
        PriorityQueue<Task> pq = new PriorityQueue<>(
            (a,b) -> (a.start - b.start)
        );
        
        for(int i=0; i<plans.length; i++){
            String name = plans[i][0];
            String[] str = plans[i][1].split(":");
            
            int hour = Integer.parseInt(str[0]);
            int minute = Integer.parseInt(str[1]);
            
            int start = hour*60 + minute;
            int time = Integer.parseInt(plans[i][2]);
            
            pq.add(new Task(name, start, time));
        }
        
        Stack<Task> st = new Stack<>();
        
        while(!pq.isEmpty()){
            Task cur = pq.poll();
            
            String curName = cur.name;
            int curTime = cur.start;
            int curPlaytime = cur.time;
            
            Task next = pq.peek();
            
            if(next == null){
                st.push(cur);
                break;
            }
            
            if(curTime + curPlaytime < next.start){
                curTime += curPlaytime;
                list.add(curName);
                
                while(!st.isEmpty()){
                    Task t = st.pop();
                    
                    if(curTime + t.time <= next.start){
                        curTime += t.time;
                        list.add(t.name);
                    }
                    else{
                        st.push(new Task(t.name, next.start, t.time - (next.start - curTime)));
                        break;
                    }
                }
            }
            else if(curTime + curPlaytime == next.start){
                list.add(curName);
            }
            else{
                st.push(new Task(curName, next.start, curPlaytime - (next.start - curTime)));
            }
        }
        
        if(!st.isEmpty()){
            while(!st.isEmpty()){
                Task t = st.pop();
                list.add(t.name);
            }
        }
        
        String[] answer = new String[list.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}