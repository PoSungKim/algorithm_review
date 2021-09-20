import java.util.*;

public class Main {
    public static int[][] Dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public static List<Point> List = new ArrayList<>();
    public static int[][] Board = new int[51][51], Cnt = new int[51][51];
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

    public static void bfs(Point point) {
        Queue<Point> Queue = new LinkedList<>();
        boolean[][] Visited = new boolean[51][51];

        Queue.offer(point);
        Visited[point.y][point.x] = true;

        while(!Queue.isEmpty()) {
            Point curPoint = Queue.poll();
            Cnt[curPoint.y][curPoint.x] = curPoint.turn;
            
            for(int i = 0; i < 4; i++) {
                int new_y = curPoint.y + Dirs[i][0];
                int new_x = curPoint.x + Dirs[i][1];
                if (new_y < 0 || 50 < new_y || new_x < 0 || 50 < new_x)  continue;
                if (Visited[new_y][new_x] == true) continue;
                if (Board[new_y][new_x] != 0) continue;
                if (Cnt[new_y][new_x] <= curPoint.turn + 1) continue;
                
                Visited[new_y][new_x] = true;
                Queue.offer(new Point(new_y, new_x, curPoint.turn + 1));
            }
        }
        System.out.println();
        System.out.println(point);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(Cnt[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) Cnt[i][j] = 100;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                Board[i][j] = sc.nextInt();
                if (Board[i][j] == 2) {
                    List.add(new Point(i, j, 1));
                    Cnt[i][j] = 1;
                }
            }
        
        for(int i = 0; i < (1 << List.size()); i++) {
            for(int j = 0; j < List.size(); j++) {
                for(int k = 0; k < List.size(); k++) 
                    bfs(List.get(i));
            }
        }

        
        System.out.println(List);
        
        int max_t = 0;
        for(int i = 0; i < N; i++) 
            for(int j = 0; j < N; j++) 
                if (Cnt[i][j] != 100)
                    max_t = Math.max(max_t, Cnt[i][j]);
        
        System.out.println(max_t);
        return;
    }
}