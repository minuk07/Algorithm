import java.util.*;

class Solution {
    public Stack<Integer> st = new Stack<>();
    
    public int solution(int[] order) {
        int answer = 0;
        int index = 0;
        int ordering = 1;
        
        while(true){
            if(index == order.length){ //다 꺼낸 경우
                break;
            }
            else if(ordering == order[index] && ordering<=order.length){ //바로 꺼낼 수 있는 경우
                answer++;
                index++;
                ordering++;
            }
            else if(!st.isEmpty() && order[index] == st.peek()){ //스택에서 꺼낼 수 있는 경우
                answer++;
                index++;
                st.pop();
            }
            else if(order[index] != ordering && ordering <= order.length){//못 꺼내서 스택에 넣어야 하는 경우
                st.push(ordering);
                ordering++;
            }
            
            else if(order[index]!=st.peek() && ordering > order.length){
                break;
            }
        }
    
        
        return answer;
    }
}