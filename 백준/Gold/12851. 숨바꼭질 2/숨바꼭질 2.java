import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int k;

    static int[] move = new int[100001];
    static int[] dy = {-1, 1, 2};

    static int minTime;
    static int cnt;
    
    public static void bfs(int start, int dest){
        Queue<Integer> q = new LinkedList<>();
        move[start] = 0;
        q.add(start);

        minTime = Integer.MAX_VALUE;
        cnt = 0;

        while(!q.isEmpty()){
            int uy = q.poll();

            if(uy == dest && move[uy] <= minTime){
                minTime = move[uy];
                cnt++;
            }

            for(int i=0 ;i<3; i++){
                int ddy = (i==2 ? uy*dy[i] : uy + dy[i]);

                if(ddy < 0 || ddy > 100000) continue;
                if(move[ddy] == 0 || move[uy] + 1 <= move[ddy]){
                    q.add(ddy);
                    move[ddy] = move[uy] + 1;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        bfs(n,k);

        StringBuilder sb = new StringBuilder();
        sb.append(minTime).append('\n').append(cnt);

        System.out.print(sb.toString());
    }
    
}