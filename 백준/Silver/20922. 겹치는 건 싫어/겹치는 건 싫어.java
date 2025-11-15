
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();

        int l = 0;
        int r = 0;
        int len = Integer.MIN_VALUE;

        while (true) {

            if (l >= n || r >= n) {
                len = Math.max(len, r - l);
                break;
            }

            if (!map.containsKey(arr[r])) {
                map.put(arr[r], 1);
                r++;
            } else {
                if (map.get(arr[r]) >= k) {

                    len = Math.max(r - l, len);

                    map.put(arr[l], map.get(arr[l]) - 1);
                    if (map.get(arr[l]) == 0) map.remove(arr[l]);
                    l++;
                } else {
                    map.put(arr[r], map.get(arr[r]) + 1);
                    r++;
                }
            }
        }


        System.out.println(len);
    }
}