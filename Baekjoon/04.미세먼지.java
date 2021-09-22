import java.util.*;

public class Main {
    static int[][] Dirs = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int[][] Dusts = new int[50][50];
    static int R, C, T, Fresher;

    static int solve() {

        for(int tc = 0; tc < T; tc++) {
            int[][] Tmp = new int[50][50];

            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if (Dusts[i][j] > 0) {
                        for(int dir = 0; dir < 4; dir++) {
                            int n_i = i + Dirs[dir][0];
                            int n_j = j + Dirs[dir][1];
                            if (n_i < 0 || R <= n_i || n_j < 0 || C <= n_j) continue;
                            if (Dusts[n_i][n_j] == -1) continue;
                            Tmp[n_i][n_j] += Dusts[i][j] / 5;
                            Tmp[i][j] -= Dusts[i][j] /5;
                        }
                    }
                }
            }

            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if (Dusts[i][j] == -1) continue;
                    Dusts[i][j] += Tmp[i][j];
                }
            }

            for(int i = Fresher - 2; i >= 1; i--) 
                Dusts[i][0] = Dusts[i - 1][0];
            for(int j = 0; j <= C - 2; j++)
                Dusts[0][j] = Dusts[0][j + 1];
            for(int i = 0; i <= Fresher - 2; i++)
                Dusts[i][C - 1] = Dusts[i + 1][C - 1];
            for(int j = C - 1; j >= 2; j--)
                Dusts[Fresher - 1][j] = Dusts[Fresher - 1][j - 1];
            Dusts[Fresher - 1][1] = 0;

            for(int i = Fresher + 1; i <= R - 2; i++)
                Dusts[i][0] = Dusts[i + 1][0];
            for(int j = 0; j <= C - 2; j++)
                Dusts[R - 1][j] = Dusts[R - 1][j + 1];
            for(int i = R - 1; i >= Fresher + 1; i--)
                Dusts[i][C - 1] = Dusts[i - 1][C - 1];
            for(int j = C - 1; j >= 2; j--)
                Dusts[Fresher][j] = Dusts[Fresher][j - 1];
            Dusts[Fresher][1] = 0;
        }

        int sum = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if (Dusts[i][j] == -1) continue;
                sum += Dusts[i][j];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt(); C = sc.nextInt(); T = sc.nextInt();

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                Dusts[i][j] = sc.nextInt();
                if (Dusts[i][j] == - 1 && Dusts[i - 1][j] == - 1)
                    Fresher = i;
            }
        }

        System.out.println(solve());

        return ;
    }
}