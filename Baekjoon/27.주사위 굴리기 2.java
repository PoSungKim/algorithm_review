import java.util.*;

public class Main {

    public static class Dice {
        public int T, B, S1, S2, S3, S4;
        public int curY, curX, curDir;
        public int pt;

        public Dice(int T, int B, int S1, int S2, int S3, int S4) {
            this.T = T;
            this.B = B;
            this.S1 = S1;
            this.S2 = S2;
            this.S3 = S3;
            this.S4 = S4;
            curY = curX = curDir = pt = 0;
        }

        public void move() {

            int nY = curY + dirs[curDir][0];
            int nX = curX + dirs[curDir][1];

            if (nY < 0 || nX < 0 || nY >= N || nX >= M) {
                curY -= dirs[curDir][0];
                curX -= dirs[curDir][1];
                curDir = (curDir + 2) % 4;
            } else {
                curY = nY;
                curX = nX;
            }

            switch(curDir) {
                case 0:
                    moveEast();
                    break;
                case 1:
                    moveSouth();
                    break;
                case 2:
                    moveWest();
                    break;
                case 3:
                    moveNorth();
                    break;
            }

            getPt();

            if (B > map[curY][curX]) {
                curDir = (curDir + 1) % 4;
            } else if (B < map[curY][curX]) {
                curDir = curDir - 1 < 0 ? 3 : curDir - 1;
            }
        }

        public void moveEast() {
            int tmpT = T;
            T  = S1;
            S1 = B;
            B  = S3;
            S3 = tmpT;
        }

        public void moveSouth() {
            int tmpT = T;
            T  = S2;
            S2 = B;
            B  = S4;
            S4 = tmpT;
        }

        public void moveWest() {
            int tmpT = T;
            T  = S3;
            S3 = B;
            B  = S1;
            S1 = tmpT;
        }

        public void moveNorth() {
            int tmpT = T;
            T  = S4;
            S4 = B;
            B  = S2;
            S2 = tmpT;
        }

        public void getPt() {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][M];
            queue.offer(new int[] {curY, curX});
            visited[curY][curX] = true;
            int baseN = map[curY][curX];

            while(!queue.isEmpty()) {
                int[] curPos = queue.poll();
                int y = curPos[0];
                int x = curPos[1];
                pt += map[y][x];

                for(int dir = 0; dir < 4; dir++) {
                    int nY = y + dirs[dir][0];
                    int nX = x + dirs[dir][1];

                    if (nY < 0 || nX < 0 || nY >= N || nX >= M || visited[nY][nX]) continue;
                    if (map[nY][nX] != baseN) continue;

                    visited[nY][nX] = true;
                    queue.offer(new int[] {nY, nX});
                }
            }

        }

    }

    public static int N, M, K;
    public static int[][] map, dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static Dice myDice;

    public static void initSetting() {
        myDice = new Dice(1, 6, 4, 2, 3, 5);
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();
        map = new int[N][M]; for(int i = 0; i < N; i++) for(int j = 0; j < M; j++) map[i][j] = sc.nextInt();
    }

    public static void main(String[] args)  {
        initSetting();
        for(int i = 0; i < K; i++) myDice.move();

        System.out.println(myDice.pt);
    }
}
