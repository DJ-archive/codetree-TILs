import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] arr;
    static int ans, n;

    // 1. 가능한 모든 수를 만들고 아름다운 수인지 검증하는 방식
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        findBeautifulNums(0);
        System.out.println(ans);
    }

    private static void findBeautifulNums(int cnt) {
        if (cnt == n) {
            if (isBeautiful(arr)) {
                ans++;
            }
            return;
        }
        for (int i = 1; i <= 4; i++) {
            arr[cnt] = i;
            findBeautifulNums(cnt + 1);
        }
    }

    // 인덱스 주의
    private static boolean isBeautiful(int[] arr) {
        for (int i = 0; i < n; i += arr[i]) {
            // 연속해야 하는 수를 고려했을 때 길이가 벗어나는 경우 false
            if (arr[i] + i > n) {
                return false;
            }
            // 연속해야하는 수까지 살펴보았을 때 연속하지 않았을 경우
            for (int j = i; j < i + arr[i]; j++) {
                if (arr[j] != arr[i]) {
                    return false;
                }
            }
        }
        // false에 걸리지 않으면 true
        return true;
    }


}