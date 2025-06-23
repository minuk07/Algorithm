import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] train;
    static int allZeroBit = 0b00000000000000000000;
    static int allOneBit = 0b11111111111111111111;

    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        train = new int[n];
        for(int i=0; i<n; i++){
            train[i] = allZeroBit;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int cmd = Integer.parseInt(st.nextToken());
            int order = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                 int seat = Integer.parseInt(st.nextToken());
                 train[order-1] = train[order-1] | (1 << seat-1);
                 //String result = String.format("%20s", Integer.toBinaryString(train[order-1])).replace(' ', '0');
                 //System.out.println("1번 명령어 이후 : " + (order-1) + "번째 기차에" + seat +"번째 좌석에 탑승 : " + result);

            }
            else if(cmd == 2){
                int seat = Integer.parseInt(st.nextToken());
                train[order-1] = train[order-1] & ~(1 << seat-1);
            }
            else if(cmd == 3){
                train[order-1] = (train[order-1] << 1) & allOneBit;
                //String result = String.format("%20s", Integer.toBinaryString(train[order-1])).replace(' ', '0');
                //System.out.println("3번 명령어 이후 : " + (order-1) + "번째 기차 왼쪽으로 한칸씩 이동 : " + result);
            }
            else{
                train[order-1] = (train[order-1] >> 1) & allOneBit;
            }
        }

        result = 1;

        for(int i=1; i<n; i++){
            boolean flag = false;

            for(int j=0 ; j<i; j++){
                if(train[i] == train[j]){
                    flag = true;
                    //System.out.println(i+"번째 기차와"+j+"번째 기차가 같아서 통과 못함");
                    break;
                }
            }
            if(!flag) {
                //System.out.println(i + "번째 기차는 통과할 수 있음");
                result++;
            }
        }

       

        System.out.println(result);

    }
}
