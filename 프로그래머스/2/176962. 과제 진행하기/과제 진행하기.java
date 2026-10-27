import java.util.*;

class Solution {
    
    List<String> result;
    
    class Homework{
        String name;
        int start, take;
        
        Homework(String name, int start, int take){
            this.name=name; this.start=start; this.take=take;
        }
    }
    
    private int getTime(String start){
        String[] time = start.split(":");
        int hour = Integer.parseInt(time[0]) * 60;
        int minute = Integer.parseInt(time[1]);
        
        return hour + minute;
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = {};
        result = new ArrayList<>();
        int n = plans.length;
        Arrays.sort(plans, (a,b) -> a[1].compareTo(b[1]));
        
        Stack<Homework> stack = new Stack<>();
        
        for(int i=0; i<n-1; i++){
            Homework cur = new Homework(plans[i][0], getTime(plans[i][1]), Integer.parseInt(plans[i][2]));
            Homework next = new Homework(plans[i+1][0], getTime(plans[i+1][1]), Integer.parseInt(plans[i+1][2]));
            
            if(cur.start + cur.take > next.start){
                stack.push(new Homework(cur.name, next.start, cur.start+cur.take-next.start));
            }else{
                result.add(cur.name);
                
                int time = next.start - (cur.start + cur.take);
                
                while(time > 0 && !stack.isEmpty()){
                    Homework now = stack.pop();
                    
                    if(time >= now.take){
                        result.add(now.name);
                        time -= now.take;
                    }else{
                        stack.push(new Homework(now.name, next.start, now.take-time));
                        time = 0;
                    }
                }
            }
        }
        
        result.add(plans[n-1][0]);
        
        while(!stack.isEmpty()){
            result.add(stack.pop().name);
        }
        
        return result.toArray(new String[0]);
    }
}