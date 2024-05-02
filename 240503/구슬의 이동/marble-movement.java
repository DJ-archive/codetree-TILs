import java.util.*;
import java.io.*;

class Ball {
    int num; // 구슬 번호 
    int v; // 속도
    int d; // 방향

    public Ball(int num, int v, int d) {
        this.num = num;
        this.v = v;
        this.d = d;
    }

}
public class Main {

    static int n,m,t,k;
    static ArrayList<Ball>[][] board, nxt;
    static int[] dx = {-1, 0, 0, 1}; // 상좌우하 (3-d 변환 편의 고려한 순서 지정)
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 격자 크기
        m = Integer.parseInt(st.nextToken()); // 구슬 개수
        t = Integer.parseInt(st.nextToken()); // 시간
        k = Integer.parseInt(st.nextToken()); // 칸 당 가능한 최대 구슬 수 

        board = new ArrayList[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        // 방향 변환 맵
        Map<String, Integer> dirMap = new HashMap<>();
        dirMap.put("U", 0); 
        dirMap.put("L", 1);
        dirMap.put("R", 2);
        dirMap.put("D", 3);

        for(int i=1;i<=m;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1; 
            int d = dirMap.get(st.nextToken());
            int v = Integer.parseInt(st.nextToken()); 

            Ball ball = new Ball(i, v, d);
            board[r][c].add(ball);
        }


        while(t-->0) {
            // nxt 배열 초기화
            nxt = new ArrayList[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    nxt[i][j] = new ArrayList<>();
                }
            }

            // cur board 배열에서 move -> nxt 배열에 기록 
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(board[i][j].size()>0) {
                        for(Ball b : board[i][j]) {
                            move(i, j, b, nxt);
                        }
                    }
                }
            }

            // nxt배열 사이즈가 k 초과면, 정렬 후 subList
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(nxt[i][j].size()>k) {
                        // 우선순위에 따라 정렬 수행
                        Collections.sort(nxt[i][j], (a,b)-> {
                            if(a.v == b.v) {
                                return b.num - a.num;
                            } else {
                                return b.v - a.v;
                            }
                        });

                        // subList 삭제 
                        nxt[i][j].subList(k, nxt[i][j].size()).clear();
                    }
                }
            }            

            // cur배열에 복사 
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    board[i][j] = nxt[i][j];
                }
            }

        }

        // 시뮬레이션 끝난 후 구슬의 갯수 세기 
        int answer = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                answer += board[i][j].size();
            }
        }

        System.out.println(answer);
    }

    private static void move(int r, int c, Ball b, ArrayList<Ball>[][] nxt) {
        int curX = r;
        int curY = c;
        int curD = b.d;

        // 초당 움직일 수 있는 거리(속도)만큼 이동
        for(int i=0; i<b.v; i++) {
            int nx = curX + dx[curD];
            int ny = curY + dy[curD];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n) { // 벽 충돌할 경우 
                curD = 3-curD;
                curX = curX + dx[curD];
                curY = curY + dy[curD];
            } else { // 격자내에서 움직일 경우 
                curX = nx;
                curY = ny;
            }
        }

        // 최종적인 위치를 nxt에 기록
        nxt[curX][curY].add(b);

    }
}