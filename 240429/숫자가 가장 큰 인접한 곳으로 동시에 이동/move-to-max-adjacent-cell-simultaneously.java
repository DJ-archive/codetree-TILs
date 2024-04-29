import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t;
    static int[][] board, cnt, nxtCnt;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 구슬 위치 (갯수) 배열
        cnt = new int[n][n];
        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            cnt[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]++;
        }

        // 시간에 따른 시뮬레이션 수행
        while(t-->0) {
            nxtCnt = new int[n][n];

            // 탐색
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(cnt[i][j] == 1) {
                        move(i,j,nxtCnt);
                    }
                }
            }

            // cnt 배열에 copy하면서 중복 제거
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(nxtCnt[i][j] > 1) {
                        cnt[i][j] = 0;
                    } else {
                        cnt[i][j] = nxtCnt[i][j];
                    }
                }
            }
        }

        // 총 갯수 세기
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(cnt[i][j]>0) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static void move(int r, int c, int[][] nxtCnt) {
        int maxValue = 0;
        int finalX = 0;
        int finalY = 0;

        for(int i=0; i<4; i++) {
            int nx = dx[i] + r;
            int ny = dy[i] + c;
            if(nx<0 || nx >=n || ny<0 || ny>=n) {
                continue;
            }
            if(maxValue < board[nx][ny]) {
                maxValue = board[nx][ny];
                finalX = nx;
                finalY = ny;
            }
        }

        nxtCnt[finalX][finalY]++;
    }
}