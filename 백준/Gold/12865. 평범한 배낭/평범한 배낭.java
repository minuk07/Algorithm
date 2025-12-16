import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int k;
    static Item[] items;
    static int[][] dp;

    static class Item{
        int weight, value;
        Item(int weight, int value){
            this.weight = weight; this.value = value;
        }
    }

    public static void knapsack(){
        for(int i=1; i<=n; i++){
            int w = items[i].weight;
            int v = items[i].value;
            //System.out.println("id: "+ i+ " w: " + items[i].weight+ " v: "+items[i].value);
            for(int j=1; j<=k; j++){
                if(w > j){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], v + dp[i-1][j-w]);
                }
               
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        items = new Item[n+1];
        items[0] = new Item(0,0);

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new Item(weight, value);
        }

        dp = new int[n+1][k+1];

        knapsack();

        // for(int i=0; i<=n; i++){
        //     for(int j=0; j<=k; j++){
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        System.out.print(dp[n][k]);
    }
}