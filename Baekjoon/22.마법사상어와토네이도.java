import java.util.Scanner;

public class Main {
    public static int[][][] Winds = new int[][][] { 
        { {-2, 0}, {-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {0, -2}, {0, 2}, {1, -1}, {1, 1}, {-1, 0} },
        { {0, -2}, {-1, -1}, {1, -1}, {-1, 0}, {1, 0}, {-2, 0}, {2, 0}, {-1, 1}, {1, 1}, {0, -1} },
        { {2, 0}, {1, 1}, {1, -1}, {0, -1}, {0, 1}, {0, -2}, {0, 2}, {-1, -1}, {-1, 1}, {1, 0} },
        { {0, 2}, {-1, 1}, {1, 1}, {-1, 0}, {1, 0}, {-2, 0}, {2, 0}, {-1, -1}, {1, -1}, {0, 1} },
     };

    public static double[] Percents = new double[] {0.05, 0.1, 0.1, 0.07, 0.07, 0.02, 0.02, 0.01, 0.01, 1};
    public static int[][] Dirs = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public static int[][][] Board = new int[500][500][2];
    public static int N, ans = 0;

    public static void getInput() {
        Scanner sc = new Scanner(System.in); N = sc.nextInt();
        for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) Board[i][j][0] = sc.nextInt();
    }

    public static void move(int y, int x, int d) {
        Board[y][x][1] = 1;
        
        int Num = Board[y][x][0];
        int totalMinus = 0;
        for(int pos = 0; pos < 10; pos++) {
            int curSpread = (pos != 9) ? (int)(Percents[pos] * Num) : Num - totalMinus;
            int sY = y + Winds[d][pos][0];
            int sX = x + Winds[d][pos][1];
            
            if (sY < 0 || N -1 < sY || sX < 0 || N -1 < sX) ans += curSpread;
            else Board[sY][sX][0] += curSpread;
            totalMinus += curSpread;
        }
        Board[y][x][0] = 0;
        
        int nD = (d + 1) % 4;
        int nY = y + Dirs[nD][0];
        int nX = x + Dirs[nD][1];
        if (Board[nY][nX][1] == 1) {
            y += Dirs[d][0];
            x += Dirs[d][1];
            
            if (0 <= y && y < N && 0 <= x && x < N) 
                move(y, x, d);

        } else move(nY, nX, nD);
    }

    public static int solve() {
        int y = N / 2;
        int x = N / 2;
        move(y, x, 0);
        
        return 0;
    }
    public static void main(String [] args) {
        getInput();
        solve();
        System.out.println(ans);
    }
}