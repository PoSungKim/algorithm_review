import java.util.*;

public class Main {
    public static int[][] Dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public static List<Point> List = new ArrayList<>();
    public static int[][] Board = new int[50][50];
    public static final int INF = 987654321;
    public static int N, M;
    
    public static class Point { 
        int x, y, turn;
        public Point(int y, int x, int turn) {
            this.y = y;
            this.x = x;
            this.turn = turn;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", this.y, this.x);
        }
    }

    public static int bfs() {
        
        int min_t = INF;
        for(int i = 0; i < (1 << List.size()); i++) {
            if (Integer.bitCount(i) != M) continue;
            Queue<Point> Queue  = new LinkedList<>();
            boolean[][] Visited = new boolean[50][50];
            
            for(int j = 0; j < List.size(); j++) 
                if ( (i & (1 << j)) != 0) {
                    Queue.offer(List.get(j));
                    Visited[List.get(j).y][List.get(j).x] = true;
                }

            int max_t = 0;
            while(!Queue.isEmpty()) {
                Point curPoint = Queue.poll();
                if (Board[curPoint.y][curPoint.x] == 0)
                    max_t = Math.max(max_t, curPoint.turn);
                
                for(int dir = 0; dir < 4; dir++) {
                    int new_y = curPoint.y + Dirs[dir][0];
                    int new_x = curPoint.x + Dirs[dir][1];        

                    if (new_y < 0 || N <= new_y || new_x < 0 || N <= new_x)  continue;
                    if (Visited[new_y][new_x] || Board[new_y][new_x] == 1)     continue;
                    
                    Visited[new_y][new_x] = true;
                    Queue.offer(new Point(new_y, new_x, curPoint.turn + 1));
                }
            }

            boolean flag = true;
            for(int r = 0; r < N; r++) 
                for(int c = 0; c < N; c++) 
                    if (Board[r][c] == 0 && !Visited[r][c]) {
                        flag = false;
                        break;
                    }    
            
            if (flag)
                min_t = Math.min(min_t, max_t);
        }

        return min_t == INF ? -1 : min_t;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        
        for(int i = 0; i < N; i++) 
            for(int j = 0; j < N; j++) {
                Board[i][j] = sc.nextInt();
                if (Board[i][j] == 2) List.add(new Point(i, j, 0));
            }

        System.out.println(bfs());
        return;
    }
}