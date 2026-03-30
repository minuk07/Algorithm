import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int c, n;
    static City[] cities;
    static int[] dp;

    static class City{
        int cost, customer;

        City(int cost, int customer){
            this.cost =  cost; this.customer = customer;
        }
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        cities = new City[n+1];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int cust = Integer.parseInt(st.nextToken());

            cities[i] = new City(cost, cust);
        }

        dp = new int[c + 100];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=1; i<=n; i++){
            int cost = cities[i].cost;
            int cust = cities[i].customer;

            for(int j=cust; j<c+100; j++){
                if(dp[j-cust] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], cost + dp[j-cust]);
            }
        }

        int result = Integer.MAX_VALUE;

        for(int i=c; i<c+100; i++){
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }
}