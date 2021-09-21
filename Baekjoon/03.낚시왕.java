import java.util.*;

public class Main {
    static int[][] Dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static Shark[][] Sharks = new Shark[100][100];
    static int R, C, M;

    public static class Shark {
        public int s, d, z;
        Shark(int s, int d, int z) {
            this.s = s; // 속력
            this.d = d; // 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
            this.z = z; // 크기
        }
    }

    public static int solve() {
        int sum = 0;
        Shark[][] backup = new Shark[100][100];
        for(int i = 0; i < C; i++) {
            for(int j = 0; j < R; j++) {
                if (Sharks[j][i] != null) {
                    sum += Sharks[j][i].z;
                    Sharks[j][i] = null;
                    break;
                }
            }

            for(int cur_row = 0; cur_row < R; cur_row++) {
                for(int cur_col = 0; cur_col < C; cur_col++) {
                    backup[cur_row][cur_col] = Sharks[cur_row][cur_col];
                    Sharks[cur_row][cur_col] = null;
                }
            }
    
            for(int cur_row = 0; cur_row < R; cur_row++) {
                for(int cur_col = 0; cur_col < C; cur_col++) {
                    Shark cur_shark = backup[cur_row][cur_col];
                    if (cur_shark != null) {
                        int n_r = cur_row + cur_shark.s * Dirs[cur_shark.d][0],
                            n_c = cur_col + cur_shark.s * Dirs[cur_shark.d][1];
                        
                        if (n_r < 0) {
                            n_r = -n_r;
                            cur_shark.d = 1;
                        }
                        if (n_r > R - 1) { 
                            int quotient = n_r / (R - 1);
                            int remain   = n_r % (R - 1);
                            if (quotient % 2 == 0) {
                                n_r = remain;
                            } else {
                                n_r = R - 1 - remain;
                                cur_shark.d = 0;
                            }
                        }
                        if (n_c < 0) {
                            n_c = -n_c;
                            cur_shark.d = 2;
                        }
                        if (n_c > C - 1) {
                            int quotient = n_c / (C - 1);
                            int remain   = n_c % (C - 1);
                            if (quotient % 2 == 0) {
                                n_c = remain;
                            } else {
                                n_c = C - 1 - remain;
                                cur_shark.d = 3;
                            }
                        }
                        if (Sharks[n_r][n_c] == null || Sharks[n_r][n_c] != null && Sharks[n_r][n_c].z < cur_shark.z) {
                            Sharks[n_r][n_c] = cur_shark;
                        }
                    }
                }
            }
        }

        return sum;
    }

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt(); C = sc.nextInt(); M = sc.nextInt();
        
        for(int i = 0; i < M; i++)  {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int s = sc.nextInt();
            int d = sc.nextInt() - 1;
            int z = sc.nextInt();
            Sharks[r][c] = new Shark(s, d, z);
        }

        System.out.println(solve());

        return;
    }
}