import java.util.*;
import java.io.*;

public class Main {
    public static int[][] sDirs = new int[][] {{-1,0}, {0, -1},  {0, 1}, {1, 0}};
    public static Board[][] board;
    public static Shark shark;
    public static int N, T;

    public static class Board {
        public List<Fish> fishes;

        public Board() {
            this.fishes = new ArrayList<>();
        }
    }

    public static class Fish {
        int y, x, size;

        public Fish(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }
    }

    public static class Shark {
        int y, x, size, eat;

        public Shark(int y, int x) {
            this.y = y;
            this.x = x;
            this.size = 2;
            this.eat  = 0;
        }

        public int[] findNextPos() {
            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
                if (a[2] == b[2]) {
                    if (a[0] == b[0])
                        return a[1] - b[1];
                    return a[0] - b[0];
                }
                return a[2] - b[2];
            });

            boolean[][] visited = new boolean[N][N];
            queue.offer(new int[] {shark.y, shark.x, 0});
            visited[shark.y][shark.x] = true;

            while(!queue.isEmpty()) {
                int[] pos = queue.poll();
                int cy = pos[0];
                int cx = pos[1];
                int cd = pos[2];

                if (board[cy][cx].fishes.size() > 0 && board[cy][cx].fishes.get(0).size < shark.size) {
                    pq.offer(new int[]{cy, cx, cd});
                }

                for(int dir = 0; dir < 4; dir++) {
                    int ny = cy + sDirs[dir][0];
                    int nx = cx + sDirs[dir][1];
                    int nd = cd + 1;

                    if (isNotWithin(ny, nx)) continue;
                    if (visited[ny][nx]) continue;
                    if (canNotPass(ny, nx)) continue;

                    visited[ny][nx] = true;
                    queue.offer(new int[] {ny, nx, nd});
                }
            }

            if (pq.size() > 0) return pq.poll();
            return new int[] {-1, -1};
        }

        public void move(int[] pos) {
            this.y = pos[0];
            this.x = pos[1];
        }

        public void eat() {
            board[this.y][this.x].fishes = new ArrayList<>();
            this.eat++;
            if (this.size == this.eat) {
                this.size++;
                this.eat = 0;
            }
        }

        public boolean canNotPass(int ny, int nx) {
            return board[ny][nx].fishes.size() > 0 && board[ny][nx].fishes.get(0).size > this.size;
        }
    }

    public static void initSetting() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); T = 0;
        board = new Board[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = new Board();
                int size = Integer.parseInt(st.nextToken());
                if (1 <= size && size <= 6) {
                    board[i][j].fishes.add(new Fish(i, j, size));
                } else if (size == 9) {
                    shark = new Shark(i, j);
                }
            }
        }
    }

    public static boolean checkFish() {

        for(int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j].fishes.size() > 0)
                    return true;

        return false;
    }

    public static int eatFish() {
        int[] pos = shark.findNextPos();
        if (pos[0] == -1 && pos[1] == -1) return -1;
        shark.move(pos);
        shark.eat();

        return pos[2];
    }

    public static boolean isNotWithin(int ny, int nx) {
        return ny < 0  || nx < 0 || N <= ny || N <= nx;
    }

    public static void main(String[] args) throws Exception {
        initSetting();
        while(checkFish()) {
            int t = eatFish();
            if (t == -1) break;
            T += t;
        }
        System.out.printf("%s", T);
    }
}
