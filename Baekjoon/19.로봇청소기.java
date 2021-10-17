import java.util.*;

public class Main {
    public static int[][] Dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int[][][] Board = new int[50][50][2];
    public static Robot Robot;
    public static int N, M;
    public static int ans = 0;

    public static class Robot {
        int y, x, d;
        public Robot(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        
        Robot = new Robot(sc.nextInt(),sc.nextInt(),sc.nextInt());

        for(int i = 0; i < N; i++) for(int j = 0; j < M; j++) Board[i][j][0] = sc.nextInt();
    }

    public static void solve(int y, int x, int d, int cnt) {

        int blockedTurn = 0;
        int nD = d;
        int nY = y;
        int nX = x;
        for(int dir = 1; dir <= 4; dir++) {
            nD = (nD - 1 < 0) ? 4 - 1 : nD - 1;
            nY = y + Dirs[nD][0];
            nX = x + Dirs[nD][1];
            
            if (Board[nY][nX][0] != 1 && Board[nY][nX][1] == 0) {
                ans++;
                Board[nY][nX][1] = 1;
                Board[nY][nX][0] = 2;
                solve(nY, nX, nD, cnt + 1);
                break;
            }
            else if (Board[nY][nX][0] == 1 || Board[nY][nX][1] == 1) 
                blockedTurn++;
        }

        if (blockedTurn == 4) {
            nY = y - Dirs[nD][0];
            nX = x - Dirs[nD][1];
            if (Board[nY][nX][0] == 1) return;
            solve(nY, nX, nD, cnt);
        }
    }

    public static void main(String [] args) {
        getInput();
        
        ans++;
        Board[Robot.y][Robot.x][1] = 1;
        Board[Robot.y][Robot.x][0] = 2;
        solve(Robot.y, Robot.x, Robot.d, 1);
        System.out.println(ans);
    }
}