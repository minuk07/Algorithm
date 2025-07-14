class Solution {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            if(direction.equals("left")){
                answer[i] = numbers[(i+1)%numbers.length];
            }
            else{
                if(i == 0){
                    answer[i] = numbers[numbers.length-1];
                }
                else{
                    answer[i] = numbers[i-1];   
                }
            }
        }
        
        return answer;
    }
}