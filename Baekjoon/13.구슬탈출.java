import java.util.*;

public class Main {
    public static class Balls {
        int r_y, r_x, b_y, b_x, cnt;
        public Balls(int r_y, int r_x, int b_y, int b_x, int cnt) {
            this.r_y = r_y;
            this.r_x = r_x;
            this.b_y = b_y;
            this.b_x = b_x;
            this.cnt = cnt;
        }
    }
    public static int[][] Dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public static boolean[][][][] Visited = new boolean[11][11][11][11];
    public static Queue<Balls> Queue = new LinkedList<>();
    public static char[][] Board = new char[11][11];
    public static int N, M;

    public static int[] move(int dir, int y, int x) {
        for(;;) {
            y += Dirs[dir][0];
            x += Dirs[dir][1];

            if (Board[y][x] == '#') {
                y -= Dirs[dir][0];
                x -= Dirs[dir][1];
                break;
            }
            if (Board[y][x] == 'O') {
                break;
            }
        }

        return new int[]{y, x};
    }

    public static int BFS(Balls Start_Balls){
        Queue.offer(Start_Balls);
        Visited[Start_Balls.r_y][Start_Balls.r_x][Start_Balls.b_y][Start_Balls.b_x] = true;

        while(!Queue.isEmpty()) {
            Balls Cur_Balls = Queue.poll();
            if (Cur_Balls.cnt > 10) return -1;
            if (Board[Cur_Balls.r_y][Cur_Balls.r_x] == 'O') return Cur_Balls.cnt;
            
            int[] tmp = new int[2];
            for(int dir = 0; dir < 4; dir++) {
                tmp = move(dir, Cur_Balls.r_y, Cur_Balls.r_x);
                int r_y = tmp[0], r_x = tmp[1];
                tmp = move(dir, Cur_Balls.b_y, Cur_Balls.b_x);
                int b_y = tmp[0], b_x = tmp[1];
                
                if (Board[b_y][b_x] == 'O') continue;
                if (r_y == b_y && r_x == b_x) {
                    int r_d = Math.abs(r_y - Cur_Balls.r_y) + Math.abs(r_x - Cur_Balls.r_x);
                    int b_d = Math.abs(b_y - Cur_Balls.b_y) + Math.abs(b_x - Cur_Balls.b_x);
                    if (r_d < b_d) {
                        b_y -= Dirs[dir][0];
                        b_x -= Dirs[dir][1];
                    } else {
                        r_y -= Dirs[dir][0];
                        r_x -= Dirs[dir][1];
                    }
                }
                if (Visited[r_y][r_x][b_y][b_x]) continue;
                Visited[r_y][r_x][b_y][b_x] = true;
                Queue.offer(new Balls(r_y, r_x, b_y, b_x, Cur_Balls.cnt + 1));
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        Balls Start_Balls = new Balls(0,0,0,0,0);
        // Scanner에서는 char을 인식해서 가져오는 Method를 제공하지 않기 때문에
        // next()로 String 값을 가져온 뒤에 toCharArray()로 변환해서 넣어주면 효율적으로 처리 가능
        for(int i = 0; i < N; i++) {
            Board[i] = sc.next().toCharArray();
            for(int j = 0; j < M; j++) {
                if (Board[i][j] == 'R') {
                    Start_Balls.r_y = i;
                    Start_Balls.r_x = j;
                } else if (Board[i][j] == 'B') {
                    Start_Balls.b_y = i;
                    Start_Balls.b_x = j;
                }
            }
        }
        System.out.println(BFS(Start_Balls));
    }
}