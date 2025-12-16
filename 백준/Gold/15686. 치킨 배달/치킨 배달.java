import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int m;

    static int[][] map;
    static List<int[]> chickenList;
    static List<int[]> houseList;
    static List<int[]> select;

    static boolean[] visited;

    static int result;

    public static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void back(int depth, int start){

        if(m == depth){
            int sum = 0;
            for(int[] h : houseList){
                int temp = Integer.MAX_VALUE;
                for(int[] c : select){
                    int dist = Math.abs(h[0]-c[0]) + Math.abs(h[1]-c[1]);
                    temp = Math.min(temp, dist);
                }
                sum += temp;
            }
            result = Math.min(sum, result);
            return;
        }

        for(int i=start; i<chickenList.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                select.add(chickenList.get(i));
                back(depth+1, i+1);
                select.remove(select.size()-1);
                visited[i] = false;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();
        select = new ArrayList<>();
        
        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    houseList.add(new int[]{i,j});
                }
                else if(map[i][j] == 2){
                    chickenList.add(new int[]{i,j});
                }
            }
        }

        visited = new boolean[chickenList.size()];
        result = Integer.MAX_VALUE;

        back(0,0);

        System.out.print(result);
        
    }
}