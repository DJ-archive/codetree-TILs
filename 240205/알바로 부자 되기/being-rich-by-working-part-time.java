// two pointer + dp로 시간복잡도 O(NlogN)으로 줄이기
// ** 기존 풀이는 각 알바 i에 대해 그 전 알바들 중 e[j] < s[i]를 만족하는 j를 전부 탐색해보는데 O(N)이 소요되어 시간복잡도가 O(N^2)이었다.
// 1. 비효율적인 부분: i가 달라졌을 때 이전에 봤던 j를 다시 봄
//   -> 이전에 e[j] < s[i-1] 조건을 만족하는 j 중 dp[j]의 최대가 되도록 하는 j를 미리 구해두었다면 좀 더 효율적인 풀이 가능
// 2. 풀이
//   -> 시작하는 순 말고도, 끝나는 시간 순서대로 배열 정렬 & 최댓값 j 비교 / 갱신

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static Pair[] sortedByEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] s = new int[N + 1];
        int[] e = new int[N + 1];
        int[] p = new int[N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            e[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        sortedByEnd = new Pair[N+1];
        sortedByEnd[0] = new Pair(0, 0);
        for (int i = 1; i <= N; i++) {
            sortedByEnd[i] = new Pair(e[i], i);
        }
        Arrays.parallelSort(sortedByEnd, 1, N+1);

        int[] dp = new int[N+1]; // 마지막으로 고른 알바가 i번째 알바일 때 얻을 수 있는 최대 수익
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 0부터 i -1 사이의 j중 e[j] < s[i]를 만족하며 dp[j]값이 가장 큰 경우의 j값을 기록
        // 처음에는 0번째 위치
        int maxJ = 0;
        int pt = 1;

        // i가 증가함에 따라 dp값을 최대로 하는 j를 중복 없이 계산해주기 위해서는
        // e[i]가 증가하는 순으로 정렬해놓은 sortedWorks 배열을 활용하여
        // e[j] < s[i]를 만족하는 알바에 대해 그 중 dp값이 가장 큰 j를 계산하면 된다.
        for (int i = 1; i <= N; i++) {
            while (sortedByEnd[pt].e < s[i]) {
                int j = sortedByEnd[pt].idx;
                if (dp[j] > dp[maxJ]) {
                    maxJ = j;
                }
                pt++;
            }
            dp[i] = Math.max(dp[i], dp[maxJ] + p[i]);
        }
        
        int max = 0;
        for (int i = 0; i <= N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);

    }

    // 끝나는 시간 순으로 정렬
    private static class Pair implements Comparable<Pair> {

        int e;
        int idx;

        public Pair(int e, int idx) {
            this.e = e;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair o) {
            if (e == o.e) {
                return idx - o.idx;
            } else {
                return e - o.e;
            }
        }
    }
}