import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[M + 1][N]; // m일째 되던 날 마지막으로 n번째 옷을 입었을 때 화려함 차이의 최댓값
        for (int i = 0; i < M + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        int[][] clothes = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            clothes[i][0] = Integer.parseInt(st.nextToken()); // s
            clothes[i][1] = Integer.parseInt(st.nextToken()); // e
            clothes[i][2] = Integer.parseInt(st.nextToken()); // v
        }

        for (int i = 0; i < N; i++) {
            if (clothes[i][0] <= 1) {
                dp[1][i] = clothes[i][0];
            }
        }

        for (int i = 2; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                if (clothes[j][0] <= i && clothes[j][1] >= i) {
                    for (int k = 0; k < N; k++) {
                        if (dp[i - 1][k] == -1) {
                            continue;
                        }
                        dp[i][j] = Math.max(dp[i][j],
                                dp[i - 1][k] + Math.abs(clothes[k][2] - clothes[j][2]));
                    }
                }
            }
        }

        int max = -1;
        for (int i = 0; i < N; i++) {
            if (max < dp[M][i]) {
                max = dp[M][i];
            }
        }

        System.out.println(max-1);


    }

}