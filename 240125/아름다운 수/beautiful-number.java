import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    static int ans, n;

    // 2. 아름다운 수를 만들어나가기 (1~4까지이므로 충분히 가능)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        findBeautifulNums(new StringBuilder(), 0);
        System.out.println(ans);
    }

    private static void findBeautifulNums(StringBuilder sb, int cnt) {
        if (cnt == n) {
            ans++;
            return;
        }
        if (sb.length()+1 <= n) {
            sb.append(1);
            findBeautifulNums(sb, cnt+1);
            sb.delete(cnt, cnt+1);
        }
        if (sb.length()+2 <= n) {
            sb.append(22);
            findBeautifulNums(sb, cnt+2);
            sb.delete(cnt, cnt+2);
        }
        if (sb.length()+3 <= n) {
            sb.append(333);
            findBeautifulNums(sb, cnt+3);
            sb.delete(cnt, cnt+3);
        }
        if (sb.length()+4 <= n) {
            sb.append(4444);
            findBeautifulNums(sb, cnt+4);
            sb.delete(cnt, cnt+4);
        }
    }

}