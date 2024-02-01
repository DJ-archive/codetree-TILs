import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][10]; // dp[i][j] = i회까지 숫자를 고르고 마지막 숫자가 j일 때 가능한 계단 수의 갯수

        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                int cnt = 0;
                if (j >= 1 && dp[i - 1][j - 1] != -1) {
                    cnt++;
                }
                if (j <= 8 && dp[i - 1][j + 1] != -1) {
                    cnt++;
                }
                dp[i][j] = cnt * dp[i - 1][j];
            }
        }
        

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (dp[n - 1][i] != -1) {
                sum += dp[n - 1][i];
            }
        }

        System.out.println(sum);
    }

}