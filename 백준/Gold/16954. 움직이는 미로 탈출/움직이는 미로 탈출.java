import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {0, -1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dx = {0, 0, 0, -1, 1, -1, 1, -1, 1};

    static char[][] map;
    static char[][] temp;

    static void printList(char[][] arr){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<8 && x<8);
    }

    static char[][] copyList(char[][] copy, char[][] origin){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                copy[i][j] = origin[i][j];
            }
        }

        return copy;
    }

    static void moveWall(){
        temp = new char[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                temp[i][j] = '.';
            }
        }

        for(int i=0; i<7; i++){
            for(int j=0; j<8; j++){
                if(map[i][j] == '#'){
                    temp[i+1][j] = '#';
                }
            }
        }

        map = copyList(map, temp);
    }

    static boolean canGo(char[][] map){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(map[i][j] == '#') return false;
            }
        }
        return true;
    }

    static boolean movePerson(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});

        while(!q.isEmpty()){

            int size = q.size(); //현재 q의 사이즈 (동시에 일어날 일들)
            
            for(int s = 0; s<size; s++){
                int[] cur = q.poll();
                int uy = cur[0];
                int ux = cur[1];
    
                if(uy == 0 && ux == 7) {
                    //System.out.println("도착함");
                    return true;
                }
                if(map[uy][ux] == '#') continue;
    
                if(canGo(map)) {
                    //System.out.println("더이상 벽이 없음");
                    return true;
                }
    
                for(int i=0; i<9; i++){
                    int ddy = uy + dy[i];
                    int ddx = ux + dx[i];
                    
                    if(!inRange(ddy, ddx)) continue;
                    if(map[ddy][ddx] == '#') continue;
                    
                    q.add(new int[]{ddy, ddx});
                }
            }
            moveWall();
        }
        
        return false;
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];

        for(int i=0; i<8; i++){
            String str = br.readLine();
            for(int j=0; j<8; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int result = 0;
        if(movePerson(7,0)) result = 1;

        System.out.println(result);
    }
}