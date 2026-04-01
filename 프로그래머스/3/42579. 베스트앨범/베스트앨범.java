import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        List<Integer> list = new ArrayList<>();
        
        HashMap<String, Integer> total = new HashMap<>();
        HashMap<String, HashMap<Integer,Integer>> music = new HashMap<>();
        
        for(int i=0; i<plays.length; i++){
            String key = genres[i];
            int play = plays[i];
            
            if(!total.containsKey(key)){
                HashMap<Integer, Integer> map = new HashMap<>();
                total.put(key, play);
                map.put(i, play);
                music.put(key, map);
            }
            else{
                total.put(key, total.get(key) + play);
                music.get(key).put(i, play);
            }
        }
        
        List<String> keySet = new ArrayList<>(total.keySet());
        Collections.sort(keySet , (a, b) -> total.get(b) - total.get(a));
        
        for(String key : keySet){
            HashMap<Integer, Integer> map = music.get(key);
            List<Integer> gKey = new ArrayList<>(map.keySet());
            
            Collections.sort(gKey, (a,b) -> map.get(b) - map.get(a));
            
            list.add(gKey.get(0));
            if(gKey.size() > 1){
                list.add(gKey.get(1));
            }
        }
        
        int[] answer = new int[list.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}