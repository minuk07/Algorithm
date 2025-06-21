
import java.util.Scanner;

public class Main {
    static int[][] arr;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int resultY;
    static int resultX;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int num = sc.nextInt();

        arr = new int[n][n];

        int cy = 0;
        int cx = 0;
        int idx = 0;
        int arrNum = n*n;
        boolean flag = true;

        arr[cy][cx] = arrNum;

        while(arrNum>1){
            //System.out.println("arrNum: " + arrNum);
            idx = returnIdx(idx);
            int ddy = cy + dy[idx];
            int ddx = cx + dx[idx];

            if(ddy>=0 && ddx >=0 && ddy < n && ddx < n && arr[ddy][ddx] == 0){
                arrNum--;
                arr[ddy][ddx] = arrNum;
//                if(arrNum == 1){
//                    break;
//                }
//                if(num == arrNum){
//                    //System.out.println("num: " + num);
//                    resultY = ddy + 1;
//                    resultX = ddx + 1;
//                    //ystem.out.println("resultY: " + resultY + " resultX: " + resultX);
//
//                }

                cy = ddy;
                cx = ddx;
            }
            else{
                idx++;
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                sb.append(arr[i][j] + " ");
                if(arr[i][j] == num){
                    //System.out.println("num: " + num);
                    resultY = i + 1;
                    resultX = j + 1;
                    //ystem.out.println("resultY: " + resultY + " resultX: " + resultX);

                }
            }
            sb.append("\n");
        }

        sb.append(resultY + " " + resultX);

        System.out.println(sb.toString());
    }

    public static int returnIdx(int n){
        return n % 4;
    }
}
