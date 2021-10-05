import java.util.*;

// 개인적으로 가장 뿌듯한 문제
public class Main {

    public static class Point {
        int y, x, type;
        int[][] dir;
        public Point(int y, int x, int type, int[][] dir) {
            this.y = y;
            this.x = x;
            this.type = type;
            this.dir = dir;
        }
    }

    public static class Direction {
        int y, x;
        public Direction(int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public int hashCode() {
            return (y + x) % 4;
        }   
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Direction) {
                Direction curObj = (Direction)obj;
                return (this.y == curObj.y && this.x == curObj.x);
            }
            return false;
        }
    }

    public static int Dirs[][][] = new int[][][] {{{0, 1}}, {{0, -1}, {0, 1}}, {{-1, 0}, {0, 1}}, {{0, -1}, {1, 0}, {0, 1}}, {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}};
    public static Map<Direction, int[]> Map = new HashMap<>(){
        {
            put(new Direction(0, 1), new int[] {1, -1});
            put(new Direction(1, 0), new int[] {-1, -1});
            put(new Direction(0, -1), new int[] {-1, 1});
            put(new Direction(-1, 0), new int[] {1, 1});
        }
    };
    
    public static List<Point> List = new ArrayList<>();
    public static final int INF = 987654321;
    public static int min_space = INF;
    public static int Board[][];
    public static int Order[]; 
    public static int N, M, K;

    public static void solve() {
        
        int Copied_Board[][] = new int[N][M];
        for(int k = 0 ; k <  N; k++) for(int j = 0; j < M; j++) Copied_Board[k][j] = Board[k][j];
        
        int curPoint = 0;
        for(Point CCTV : List) {
            Copied_Board[CCTV.y][CCTV.x] = -1;
            for(int j = 0; j < CCTV.dir.length; j++) {
                int n_y_dir = CCTV.dir[j][0];
                int n_x_dir = CCTV.dir[j][1];
                int cur_y = CCTV.y;
                int cur_x = CCTV.x;
                for(int i = 0; i <= Order[curPoint]; i++) {
                    //System.out.format("(%d, %d)\n", n_y_dir, n_x_dir);
                    int[] change_dir = Map.get(new Direction(n_y_dir, n_x_dir));
                    //System.out.println(Arrays.toString(change_dir));
                    n_y_dir = n_y_dir + change_dir[0];
                    n_x_dir = n_x_dir + change_dir[1];
                }
                while(true) {
                    cur_y = cur_y + n_y_dir;
                    cur_x = cur_x + n_x_dir;
                    if (cur_y < 0 || cur_x < 0 || cur_y >= N || cur_x >= M) break;
                    if (Copied_Board[cur_y][cur_x] == 6) break;
                    Copied_Board[cur_y][cur_x] = -1;
                }
            }
            curPoint++;
        }

        int cnt = 0;
        for(int k = 0 ; k <  N; k++)  {
           // System.out.println();
            for( int j = 0; j < M; j++) {
                //System.out.format("%2d", Copied_Board[k][j]);
                if (Copied_Board[k][j] == 0)
                    cnt++;
            }
        }
        //System.out.println();
        min_space = Math.min(min_space, cnt);
        
        return;
    }
    

    public static void comb(int pos, int size) {

        if (pos == size) {
            //System.out.println(Arrays.toString(Order));
            solve();
            return;
        }

        for(int i = 0; i < 4; i++) {
            Order[pos] = i;
            comb(pos + 1, size);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        Board = new int[N][M];

        for(int i = 0 ; i <  N; i++)  {
            for( int j = 0; j < M; j++) {
                Board[i][j] = sc.nextInt();
                if (Board[i][j] != 0 && Board[i][j] != 6) {
                    int[][] curDir = new int[Dirs[Board[i][j] - 1].length][2];
                    for(int k = 0; k < Dirs[Board[i][j] - 1].length; k++)
                        curDir[k] = Dirs[Board[i][j] - 1][k];
                    List.add(new Point(i, j, Board[i][j], curDir));
                }
            } 
        }
        Order = new int[List.size()];
        comb(0, List.size());
        System.out.println(min_space);
        return;
    }
}