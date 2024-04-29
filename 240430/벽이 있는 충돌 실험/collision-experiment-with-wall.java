import java.io.*;
import java.util.*;

public class Main {

    static final int BLANK = -1;
    static final int COLLIDE = -2;
    static int[] dx = {-1, 0, 0, 1}; // 방향 전환 고려 (상,좌,우,하)
    static int[] dy = {0, -1, 1, 0};
    static int T, N, M;
    static int[][] board, curDir, nxtDir;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        for(int t=0;t<T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 격자 크기 
            M = Integer.parseInt(st.nextToken()); // 구슬 갯수 
            // 격자맵
            board = new int[N][N];
            // 방향맵
            Map<Character, Integer> dirMap = new HashMap<>();
            dirMap.put('U', 0);
            dirMap.put('D', 3);
            dirMap.put('L', 1);
            dirMap.put('R', 2);

            // 현재 방향 배열 초기화 
            curDir = new int[N][N];
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    curDir[i][j] = BLANK;
                }
            }
            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                curDir[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = dirMap.get(st.nextToken().charAt(0));
            }

            // 시뮬레이션 
            int cnt = 2 * N;
            while(cnt-->0) {
                simulation();
            }

            // 갯수 세고 결과 반환
            int answer = 0;
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(curDir[i][j] != BLANK) {
                        answer++;
                    }
                }
            }
            System.out.println(answer);

        }


    }
    private static void simulation() {
        // 이동 배열 초기화 
        nxtDir = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                nxtDir[i][j] = BLANK;
            }
        }

        // 이동
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(curDir[i][j] != BLANK) {
                    move(i,j, curDir[i][j]);
                }
            }
        }

        // 현재 배열에 이동 배열 copy + 충돌 검사 
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(nxtDir[i][j] == COLLIDE) {
                    curDir[i][j] = BLANK;
                } else {
                    curDir[i][j] = nxtDir[i][j];
                }
            }
        }
    }

    private static void move(int x, int y, int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        // ** 벽에 닿을 경우, 닿지 않았을 경우 모두 구슬충돌여부 확인해야 함. 
        if(nx>=0 && nx<N && ny>=0 && ny<N) { // 벽에 닿지 않을 경우 
            if(nxtDir[nx][ny]!=BLANK) { // 이미 다른 구슬이 이동해있는지 확인
                nxtDir[nx][ny] = COLLIDE;
            } else {
                nxtDir[nx][ny] = d;
            }
        } else { // 벽에 닿을 경우 
            if(nxtDir[x][y]!=BLANK) { // 이미 다른 구슬이 이동해있는지 확인
                nxtDir[x][y] = COLLIDE;
            } else {
                nxtDir[x][y] = 3-d;
            }
        }


    }
}