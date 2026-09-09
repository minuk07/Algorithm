import java.util.*;

class Solution {
    
    static List<String> list;
    static int n;
    
    class Work{
        String name;
        int start, take;
        
        Work(String name, int start, int take){
            this.name = name; this.start = start; this.take = take;
        }
    }
    
    public String[] solution(String[][] plans) {
        
        list = new ArrayList<>();
        n = plans.length;
        
        List<Work> plan = new ArrayList<>();
        
        for(String[] p : plans){
            String name = p[0];
            String[] tmp = p[1].split(":");
            int hour = Integer.parseInt(tmp[0]) * 60;
            int minute = Integer.parseInt(tmp[1]);
            
            int start = hour + minute;
            int take = Integer.parseInt(p[2]);
                        
            plan.add(new Work(name, start, take));
        }
        
        Collections.sort(plan, (a,b) -> a.start - b.start);
        
        Stack<Work> stack = new Stack<>();
        
        
        for(int i=0; i<n-1; i++){
            
            Work cur = plan.get(i);
            Work next = plan.get(i+1);
            
            int finish = cur.start + cur.take;
            
            if(finish <= next.start){ //빨리 끝나는 경우
                list.add(cur.name);
                
                int time = next.start - finish;
                
                while(time > 0 && !stack.isEmpty()){
                    
                    Work recent = stack.pop();
                
                    if(recent.take <= time){
                        list.add(recent.name);
                        time -= recent.take;
                    } else{
                        recent.take -= time;
                        stack.push(recent);
                        time = 0;
                    }
                }
            }else{
                
                int remain = finish - next.start;
                
                stack.push(new Work(cur.name, next.start, remain));
            }
        }
        
        list.add(plan.get(n-1).name);
        
        while(!stack.isEmpty()){
            list.add(stack.pop().name);
        }
        
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}