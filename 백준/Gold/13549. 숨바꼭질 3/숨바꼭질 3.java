import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int k;
    static int[] move = new int[100001];
    static boolean[] visited = new boolean[100001];

    static int[] dy = {-1, 1, 2};
    
    public static void main(String[] args)throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        int result = Integer.MAX_VALUE;

        Queue<Integer> q = new LinkedList<>();
        move[n] = 0;
        q.add(n);
        visited[n] = true;

        while(!q.isEmpty()){
            int uy = q.poll();

            if(uy == k && move[uy] <= result){
                result = move[uy];
            }

            for(int i=0; i<3; i++){
                if(i == 2){
                    int ddy = uy*dy[i];
                    if(ddy < 0 || ddy > 100000) continue;
                    if(!visited[ddy] || move[ddy] > move[uy]){
                        move[ddy] = move[uy];
                        visited[ddy] = true;
                        q.add(ddy);
                    }
                }
                else{
                    int ddy = uy + dy[i];
                    if(ddy < 0 || ddy > 100000) continue;
                    if(!visited[ddy] || move[ddy] > move[uy] + 1){
                        move[ddy] = move[uy] + 1;
                        visited[ddy] = true;
                        q.add(ddy);
                    }
                }
                
            }
        }

        System.out.print(result);
    }
}