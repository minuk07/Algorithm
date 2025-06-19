
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] boom;
    static char[][] map;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        boom = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++){
                boom[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int cnt = 0;
                if(map[i][j] == 'x'){
                    if(boom[i][j] == '*'){
                        flag = true;
                    }
                    else{
                        for(int k=0; k<9; k++){
                            int ddy = i + dy[k];
                            int ddx = j + dx[k];
                            if(ddx>=0 && ddx<n && ddy>=0 && ddy<n && boom[ddy][ddx] == '*'){
                                cnt++;
                            }
                        }
                        map[i][j] = (char)(cnt + '0');
                    }
                }
                else{
                    map[i][j] = '.';
                }
            }
        }
        if(flag){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(boom[i][j] == '*'){
                        map[i][j] = '*';
                    }
                }
            }
        }
        for(int i=0; i<n; i++){
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
