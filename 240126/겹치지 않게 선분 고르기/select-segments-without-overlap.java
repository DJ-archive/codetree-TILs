import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] arr;
    static boolean[] selected;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        selected = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        select(0);
        System.out.println(ans);
    }

    private static void select(int cnt) {
        if (cnt == n) {
            // 방문 배열 갱신 필요
            int c = check(selected);
            if (ans < c) {
                ans = c;
            }
            return;
        }
        selected[cnt] = true;
        select(cnt + 1);

        selected[cnt] = false;
        select(cnt + 1);
    }

    private static int check(boolean[] selected) {
        boolean[] visited = new boolean[1001];
        int cnt = 0;
        boolean flag = true;
        loop:
        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                cnt++; // 고른 선분 갯수
                for (int j = arr[i][0]; j <= arr[i][1]; j++) {
                    if (visited[j]) {
                        flag = false;
                        break loop;
                    }
                    visited[j] = true;
                }
            }
        }
        if (flag) {
            return cnt;
        } else {
            return 0;
        }
    }

}