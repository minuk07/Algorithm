import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int a, b;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int cnt = 1;
        while(a != b){
            if(b < a){
                cnt = -1;
                break;
            }

            if(b%10 == 1) b/=10;
            else if(b%2 == 0) b/=2;
            else{
                cnt = -1;
                break;
            }

            cnt++;
        }

        System.out.println(cnt);
    }
}