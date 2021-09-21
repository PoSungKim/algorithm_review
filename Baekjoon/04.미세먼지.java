import java.util.*;

public class Main {
    public static class Dust {
        int amount, cnt;
        Dust(int amount, int cnt) {
            this.amount = amount;
            this.cnt = cnt;
        }
    }
    static int[][] Dirs = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; //좌상우하
    static int[][] Dusts = new int[50][50];
    static int[][] Fresher = new int[2][2];
    static int R, C, T;

    public static void main(String[] args){ 
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt(); C = sc.nextInt(); T = sc.nextInt();

        int index = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int cur_num = sc.nextInt();
                Dusts[i][j] = cur_num;
                if (cur_num == -1)  {
                    Fresher[index][0] = i;
                    Fresher[index++][1] = j;
                }
            }
        }

        for(int tc = 0; tc < T; tc++) {
            
            Dust[][] Backup = new Dust[50][50];

            // 1. cnt 계산
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if (Dusts[i][j] != 0 || Dusts[i][j] != -1) {
                        int cnt = 0;
                        for(int dir = 0; dir < 4; dir++) {
                            int n_i = i + Dirs[dir][0];
                            int n_j = j + Dirs[dir][1];
                            if (n_i < 0 || R <= n_i || n_j < 0 || C <= n_j) continue;
                            if (n_i == Fresher[0][0] && n_j == Fresher[0][1] || n_i == Fresher[1][0] && n_j == Fresher[1][1]) continue;
                            cnt++;
                        }
                        Backup[i][j] = new Dust(Dusts[i][j], cnt);
                    }
                }
            }
            
            // 2. 확산
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if (Backup[i][j] != null) {
                        for(int dir = 0; dir < 4; dir++) {
                            int n_i = i + Dirs[dir][0];
                            int n_j = j + Dirs[dir][1];
                            if (n_i < 0 || R <= n_i || n_j < 0 || C <= n_j) continue;
                            if (n_i == Fresher[0][0] && n_j == Fresher[0][1] || n_i == Fresher[1][0] && n_j == Fresher[1][1]) continue;
                            Dusts[n_i][n_j] += Backup[i][j].amount / 5;
                        }
                        Dusts[i][j] -= Backup[i][j].amount / 5 * Backup[i][j].cnt;
                    }
                }
            }

            // 3. 이동
            for(int j = Fresher[0][0] - 1; j > 0; j--)
                Dusts[j][0] = Dusts[j - 1][0];
            
            for(int i = 0; i < C - 1; i++) 
                Dusts[0][i] = Dusts[0][i + 1];
            
            for(int j = 0; j < Fresher[0][0]; j++) 
                Dusts[j][C - 1] = Dusts[j + 1][C - 1];
            
            for(int i = C - 1; i >= 2; i--) 
                Dusts[Fresher[0][0]][i] = Dusts[Fresher[0][0]][i - 1]; 
            
            Dusts[Fresher[0][0]][1] = 0;

            for(int j = Fresher[1][0] + 1; j < R - 1; j++) 
                Dusts[j][0] = Dusts[j + 1][0];
            
            for(int i = 0; i < C - 1; i++) 
                Dusts[R - 1][i] = Dusts[R - 1][i + 1];
            
            for(int j = R - 1; j > Fresher[1][0]; j--) 
                Dusts[j][C - 1] = Dusts[j -1][C - 1];
            
            for(int i = C - 1; i >= 2; i--) 
                Dusts[Fresher[1][0]][i] = Dusts[Fresher[1][0]][i - 1];
            
            Dusts[Fresher[1][0]][1] = 0;
        }

        int sum = 0;
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if (Dusts[i][j] != -1) 
                    sum += Dusts[i][j];
        
        System.out.println(sum);

        return;
    }
}