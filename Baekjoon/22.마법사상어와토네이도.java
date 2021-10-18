import java.util.Scanner;

public class Main {
    public static int[][] Dirs = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public static int[][][] Board = new int[500][500][3];
    public static int N, cnt = 0, ans = 0;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Board[i][j][0] = sc.nextInt();
            }
        }
    }

    public static void print() {
        System.out.println();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.format("%2s ", Board[i][j][0]); 
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isBeyond(int y, int x){
        return !(0 <= y && y < N && 0 <= x && x < N);
    } 

    public static void move(int y, int x, int d) {
        
        Board[y][x][2] = ++cnt;
        Board[y][x][1] = 1;
        //Board[y][x][0] = 0;
        // print();
        // System.out.format("(%s, %s, %s)\n", y, x, d);

        int nD = (d + 1) % 4;
        int nY = y + Dirs[nD][0];
        int nX = x + Dirs[nD][1];
        

        int Num = Board[y][x][0];
        // System.out.format("CURRENT NUM : %s\n", Num);

        if (cnt > 1) {
        
        int right = (d - 1 < 0)? 3 : d - 1;
        int left = (d + 1) % 4;
        int bY = y - Dirs[d][0];
        int bX = x - Dirs[d][1];
        int minus = 0;

        // System.out.format("(bY : %s, bX : %s)\n", bY, bX);

        if (isBeyond(bY + Dirs[right][0], bX + Dirs[right][1])) {
            ans += 0.01 * Num;
            minus += 0.01 * Num;
        } else {
            Board[ bY + Dirs[right][0] ][ bX + Dirs[right][1] ][0] += 0.01 * Num;
            minus += 0.01 * Num;
        }

        if (isBeyond(bY + Dirs[left][0], bX + Dirs[left][1])) {
            ans += 0.01 * Num;
            minus += 0.01 * Num;
        } else {
            Board[ bY + Dirs[left][0] ][ bX + Dirs[left][1] ][0] += 0.01 * Num;
            minus += 0.01 * Num;
        }

        // System.out.format("(y : %s, x : %s)\n", y, x);

        if (isBeyond(y + Dirs[right][0], x + Dirs[right][1])) {
            ans += 0.07 * Num;
            minus += 0.07 * Num;
        } else {
            Board[ y + Dirs[right][0] ][ x + Dirs[right][1] ][0] += 0.07 * Num;
            minus += 0.07 * Num;
        }

        if (isBeyond(y + Dirs[left][0], x + Dirs[left][1] )) {
            ans += 0.07 * Num;
            minus += 0.07 * Num;
        } else {
            Board[ y + Dirs[left][0] ][ x + Dirs[left][1] ][0] += 0.07 * Num;
            minus += 0.07 * Num;
        }

        if (isBeyond(y + Dirs[right][0] * 2, x + Dirs[right][1] * 2)) {
            ans += 0.02 * Num;
            minus += 0.02 * Num;
        } else {
            Board[ y + Dirs[right][0] * 2 ][ x + Dirs[right][1] * 2 ][0] += 0.02 * Num;
            minus += 0.02 * Num;
        }

        if (isBeyond(y + Dirs[left][0] * 2 , x + Dirs[left][1] * 2 )) {
            ans += 0.02 * Num;
            minus += 0.02 * Num;
        } else {
            Board[ y + Dirs[left][0] * 2 ][ x + Dirs[left][1] * 2 ][0] += 0.02 * Num;
            minus += 0.02 * Num;
        }

        int nY2 = y + Dirs[d][0] * 1;
        int nX2 = x + Dirs[d][1] * 1;

        // System.out.format("(nY2 : %s, nX2 : %s)\n", nY2, nX2);

        if (isBeyond(nY2 + Dirs[right][0], nX2 + Dirs[right][1])) {
            ans += 0.1 * Num;
            minus += 0.1 * Num;
        } else {
            Board[ nY2 + Dirs[right][0] ][ nX2 + Dirs[right][1] ][0] += 0.1 * Num;
            minus += 0.1 * Num;
        }

        if (isBeyond(nY2 + Dirs[left][0], nX2 + Dirs[left][1])) {
            ans += 0.1 * Num;
            minus += 0.1 * Num;
        } else {
            Board[ nY2 + Dirs[left][0] ][ nX2 + Dirs[left][1] ][0] += 0.1 * Num;
            minus += 0.1 * Num;
        }

        int nY3 = y + Dirs[d][0] * 2;
        int nX3 = x + Dirs[d][1] * 2;

        // System.out.format("(nY3 : %s, nX3 : %s)\n", nY3, nX3);

        if (isBeyond(nY3 , nX3 )) {
            ans += 0.05 * Num;
            minus += 0.05 * Num;
        } else {
            Board[ nY3 ][ nX3 ][0] += 0.05 * Num;
            minus += 0.05 * Num;
        }

        if (isBeyond(nY2 , nX2)) {
            ans += Num - minus;
            // minus -= Num;
        } else {
            Board[ nY2 ][ nX2 ][0] += Num - minus;
            // minus -= Num;
        }
            Board[y][x][0] = 0;
        }

        if (0 <= nY && nY < N && 0 <= nX && nX < N && Board[nY][nX][1] == 1) {
            y += Dirs[d][0];
            x += Dirs[d][1];
            if (0 <= y && y < N && 0 <= x && x < N) {
                move(y, x, (nD - 1 < 0 ) ? 3: nD - 1 );
            }
        } else {
            move(nY, nX, nD);
        }
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