import java.util.*;

class Solution {
    
    static boolean[][][] map;
    static int n;
    
    static boolean canBuild(int y, int x, int type){
        
        if(type == 0){ //기둥
            if(y == 0 || map[y-1][x][0] || map[y][x][1] || (x>0 &&map[y][x-1][1])){
                return true;
            }
        }else{ //보
            if(map[y-1][x][0] || (map[y-1][x+1][0]) || ((x>0) && map[y][x-1][1] && map[y][x+1][1])){
                return true;
            }
        }
        
        return false;
    }
    
    static boolean canDelete(){
        
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(map[i][j][0] && !canBuild(i, j, 0)) return false;
                if(map[i][j][1] && !canBuild(i, j, 1)) return false;
            }
        }
        
        return true;
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        
        map = new boolean[n+1][n+1][2];
        this.n = n;
        
        for(int[] frame : build_frame){
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];
            
            if(b == 1){ // 설치
                if(canBuild(y, x, a)){
                    map[y][x][a] = true;
                }
            }else{
                map[y][x][a] = false;
                if(!canDelete()){
                    map[y][x][a] = true;
                }
            }
        }
        
        List<int[]> result = new ArrayList<>();
        
        for(int j=0; j<=n; j++){
            for(int i=0; i<=n; i++){
                if(map[i][j][0]){
                    result.add(new int[]{j, i, 0});
                }
                if(map[i][j][1]){
                    result.add(new int[]{j, i, 1});
                }
            }
        }
        
        return result.toArray(new int[result.size()][3]);
    }
}