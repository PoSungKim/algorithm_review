import java.util.*;
import java.io.*;

public class Main {
    public static class Fish {
        int y, x, move, size, total;
        public Fish (int y, int x, int move, int size, int total) {
            this.y = y;
            this.x = x;
            this.move = move;
            this.size = size;
            this.total = total;
        }
    }

    public static int[][] Dirs = new int[][] { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static PriorityQueue<Fish> List = new PriorityQueue<>((a, b)-> {
        if (a.y == b.y) return a.x - b.x;
        return a.y - b.y;
    });
    public static boolean[][] Visited = new boolean[20][20];
    public static Queue<Fish> Queue = new LinkedList<>();
    public static Fish Shark = new Fish(0, 0, 0, 2, 0);
    public static int[][] Map = new int[20][20];
    public static int N, M;

    public static int solve() {

        while(true) {
            for(int i = 0 ; i < N; i ++) for(int j = 0; j < N; j++) Visited[i][j] = false;
            List.clear();
            Queue.clear();

            Queue.offer(Shark);
            Visited[Shark.y][Shark.x] = true;

            while(!Queue.isEmpty()) {
                Fish curShark = Queue.poll();
                // 조건문의 길이를 줄이기 위해, 현재 상어가 있는 장소는 -1 값을 갖게 initialize함
                if (Map[curShark.y][curShark.x] > 0 && Map[curShark.y][curShark.x] < curShark.size) {
                    if (List.isEmpty()) {
                        List.offer(curShark);
                    } else {
                        if (List.peek().move < curShark.move) break;
                        List.offer(curShark);
                    }
                }

                for(int dir = 0; dir < 4; dir++) {
                    int n_y = curShark.y + Dirs[dir][0];
                    int n_x = curShark.x + Dirs[dir][1];
                    if (n_y < 0 || n_x < 0 || n_y >= N || n_x >= N) continue;
                    if (Visited[n_y][n_x]) continue;
                    if (Map[n_y][n_x] > curShark.size) continue;

                    Fish newShark = new Fish(n_y, n_x, curShark.move + 1, curShark.size, curShark.total);
                    Visited[n_y][n_x] = true;
                    Queue.offer(newShark);
                }
            }

            if (List.isEmpty()) break;

            Map[Shark.y][Shark.x] = 0;

            Shark = List.peek();
            Map[Shark.y][Shark.x] = -1;
            Shark.total++;
            if (Shark.total == Shark.size) {
                Shark.size++;
                Shark.total = 0;
            }
        }

        return Shark.move - 1;
    }

    public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        
        for(int i = 0; i < N; i++)  {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
                if (Map[i][j] == 9) {
                    Shark = new Fish(i, j, 1, 2, 0);
                    Map[i][j] = -1;
                }
            }
        }

        System.out.println(solve());

        return;
    }
}