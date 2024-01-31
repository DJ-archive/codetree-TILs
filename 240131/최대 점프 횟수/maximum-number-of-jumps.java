import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.zip.Adler32;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        // 첫번째 (0)자리에서부터 i번째 자리까지 최대 점프 가능한 횟수
        dp[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + i >= j) {
                    if (i != 0 && dp[i] <= 0) {
                        continue;
                    }
                    dp[j] = Math.max(dp[i] + 1, dp[i]);
                }
            }
        }

        int max = 0;
        for (int i = 1; i < n; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }
}