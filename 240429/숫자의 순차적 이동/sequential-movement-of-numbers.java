import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] board;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // board 기록
        board = new int[n][n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시뮬레이션
        while(m-->0) {
            int idx = 0;
            loop:
            while(idx++<n*n) {
                for(int i=0; i<n; i++) {
                    for(int j=0; j<n; j++) {
                        if(board[i][j] == idx) {
                            move(i,j,board);
                            continue loop;
                        }
                    }
                }
            }
        }

        // 결과 출력
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void move(int r, int c, int[][] board) {
        int maxValue = 0;
        int finalX = 0;
        int finalY = 0;

        for(int i=0;i<8;i++) {
            int nx = dx[i] + r;
            int ny = dy[i] + c;

            if(nx<0 || nx>=n || ny<0 || ny>=n) {
                continue;
            }

            if(maxValue < board[nx][ny]) {
                maxValue = board[nx][ny];
                finalX = nx;
                finalY = ny;
            }
        }

        // swap
        int tmp = board[r][c];
        board[r][c] = maxValue;
        board[finalX][finalY] = tmp;
    }
}