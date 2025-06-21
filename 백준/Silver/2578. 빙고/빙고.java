
import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static int result;

    public static void main(String[] args) throws IOException {
        board = new int[5][5];
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                board[i][j] = sc.nextInt();
            }
        }
        result = 0;

        for(int k=1; k<=25; k++){
            int num = sc.nextInt();

            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(board[i][j] == num){
                        board[i][j] = 0;
                    }
                }
            }

            ldiagonal(board);
            rdiagonal(board);
            horizontal(board);
            vertical(board);

            if(result >= 3){
                System.out.println(k);
                break;
            }

            result = 0;
        }


    }

    public static void horizontal(int[][] board){
        for(int i=0; i<5; i++){
            int cnt =0;
            for(int j=0; j<5; j++){
                if(board[i][j] == 0){
                    cnt++;
                }
            }
            if(cnt == 5){
                result++;
            }
        }
    }

    public static void vertical(int[][] board){
        for(int i=0; i<5; i++){
            int cnt =0;
            for(int j=0; j<5; j++){
                if(board[j][i] == 0){
                    cnt++;
                }
            }
            if(cnt == 5){
                result++;
            }
        }
    }

    public static void rdiagonal(int[][] board){
        int cnt = 0;
        for(int i=0; i<5; i++){
            if(board[i][i] == 0){
                cnt++;
            }
        }
        if(cnt == 5){
            result++;
        }
    }

    public static void ldiagonal(int[][] board){
        int cnt =0;
        for(int i=0; i<5; i++){
            if(board[i][4-i]==0){
                cnt++;
            }
            if(cnt == 5){
                result++;
            }
        }
    }
}
