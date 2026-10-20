import java.util.*;

class Solution {
    
    int n, m;
    
    private void printList(int[][] arr, int len){
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private int[][] rotate(int[][] key){
        int[][] newKey = new int[m][m];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                newKey[j][m-i-1] = key[i][j];
            }
        }
        
        return newKey;
    }
    
    private int[][] copyMap(int[][] map, int[][] copy){
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                copy[i][j] = map[i][j];
            }
        }
        
        return copy;
    }
    
    private boolean canUnlock(int[][] key, int[][] map, int y, int x){
        
        int[][] copy = new int[map.length][map.length];
        copy = copyMap(map, copy);
        
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                copy[i+y][j+x] += key[i][j];
            }
        }
        
        for(int i=m-1; i<n+m-1; i++){
            for(int j=m-1; j<n+m-1; j++){
                if(copy[i][j] != 1) return false;
            }
        }
    
        
        return true;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        
        n = lock.length;
        m = key.length;
        
        int[][] rotate90 = rotate(key);
        int[][] rotate180 = rotate(rotate90);
        int[][] rotate270 = rotate(rotate180);
        
        int[][] map = new int[n + 2*(m-1)][n + 2*(m-1)];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i+m-1][j+m-1] = lock[i][j];
            }
        }
        
        for(int i=0; i<n+(m-1); i++){
            for(int j=0; j<n+(m-1); j++){
                if(canUnlock(key, map, i, j)) return true;
                if(canUnlock(rotate90, map, i, j)) return true;
                if(canUnlock(rotate180, map, i, j)) return true;
                if(canUnlock(rotate270, map, i, j)) return true;
            }
        }
        
        
        return false;
    }
}