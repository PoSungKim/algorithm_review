import java.util.*;

public class Main {
    public static int[][] Dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int[][][] Board = new int[50][50][2];
    public static int N, M, rY, rX, rD;
    public static int ans = 0;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        rY = sc.nextInt(); rX = sc.nextInt(); rD = sc.nextInt();
        for(int i = 0; i < N; i++) for(int j = 0; j < M; j++) Board[i][j][0] = sc.nextInt();
    }

    public static void solve(int y, int x, int d) {

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
                solve(nY, nX, nD);
                // 조건이 만족되어 새로운 연산이 실행되면 기존 연산은 끝난 것이기에 Return을 해줘야 함
                break;
            }
            else if (Board[nY][nX][0] == 1 || Board[nY][nX][1] == 1) 
                blockedTurn++;
        }

        if (blockedTurn == 4) {
            nY = y - Dirs[nD][0];
            nX = x - Dirs[nD][1];
            if (Board[nY][nX][0] == 1) return;
            solve(nY, nX, nD);
        }
    }

    public static void main(String [] args) {
        getInput();
        
        ans++;
        Board[rY][rX][1] = 1;
        solve(rY, rX, rD);
        System.out.println(ans);
    }
}