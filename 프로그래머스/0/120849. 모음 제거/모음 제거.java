class Solution {
    String[] arr = {"a", "e", "o", "i", "u"};
    
    public String solution(String my_string) {
        
        for(String c : arr){
            my_string = my_string.replace(c,"");
        }
        
        return my_string;
    }
}