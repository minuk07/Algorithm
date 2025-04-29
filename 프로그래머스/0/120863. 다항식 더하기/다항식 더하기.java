class Solution {
    public String solution(String polynomial) {
        String answer = "";
        String[] arr = polynomial.split(" ");
        int xnum = 0;
        int num = 0;
        
        for(String str : arr){
            if(str.equals("+")){
                continue;
            }
            else if (str.contains("x")){
                if(str.equals("x")){
                    xnum += 1;
                }
                else{
                    String x = str.substring(0, str.length()-1);
                    xnum += Integer.parseInt(x);
                }
            }
            else{
                int n = Integer.parseInt(str);
                num += n;
            }
        }
        
        
        
        if(xnum!=0 && num == 0){
            if(xnum == 1){
                answer = "x";
            }
            else{
                answer = xnum + "x";
            }
        }
        else if(xnum == 0 && num != 0){
            answer = Integer.toString(num);
        }
        else{
            if(xnum == 1){
                answer = "x" + " + " + num;
            }
            else{
                answer = xnum + "x" + " + " + num;
            }
        }
        
        
        return answer;
    }
}