import java.util.*;

public class Main {
    static int[][] Map = new int[21][21];
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 1; i <= N; i++) 
            for(int j = 1; j <= N; j++)
                Map[i][j] = sc.nextInt();
            
        int min_diff = 987654321;
        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                for(int d1 = 1; d1 < y; d1++) {
                    for(int d2 = 1; x + d1 + d2 <= N && y + d2 <= N; d2++) {
                        
                        int[][] Tmp = new int[N + 1][N + 1];
                        for(int i = 0; i <= d1; i++) {
                            Tmp[x + i][y - i] = 5;
                            Tmp[x + d2 + i][y + d2 - i] = 5;
                        }

                        for(int i = 0; i <= d2; i++) {
                            Tmp[x + i][y + i] = 5;
                            Tmp[x + d1 + i][y - d1 + i] = 5;
                        }

                        for(int i = 1; i <= N; i++) {
                            int cnt = 0;
                            int[] pos = new int[2];
                            for(int j = 1; j <= N; j++) {
                                if (Tmp[i][j] == 5) 
                                    pos[cnt++] = j;
                                if (cnt == 2) {
                                    for(int k = pos[0] + 1; k < pos[1]; k++)
                                        Tmp[i][k] = 5;
                                    break;
                                } 
                            }
                        }

                        int[] sum = new int[6];
                        for(int r = 1; r <= N; r++) {
                            for(int c = 1; c <= N; c++) {
                                if (Tmp[r][c] == 5) {
                                    sum[5] += Map[r][c];
                                    continue;
                                }
                                if (r < x + d1 && c <= y) {
                                    Tmp[r][c] = 1;
                                    sum[1] += Map[r][c];
                                }
                                if (r <= x + d2 && y + 1 <= c) {
                                    Tmp[r][c] = 2;
                                    sum[2] += Map[r][c];
                                }
                                if (x + d1 <= r && c < y - d1 + d2) {
                                    Tmp[r][c] = 3;
                                    sum[3] += Map[r][c];
                                }
                                if (x + d2 + 1 <= r && y - d1 + d2 <= c) {
                                    Tmp[r][c] = 4;
                                    sum[4] += Map[r][c];
                                }
                            }
                        }
                        
                        int max_s = -1, min_s = 987654321;
                        for(int i = 1; i <= 5; i++) {
                            min_s = Math.min(min_s, sum[i]);
                            max_s = Math.max(max_s, sum[i]);
                        }
                        
                        min_diff = Math.min(min_diff, max_s - min_s);
                    }
                }
            }
        }

        System.out.println(min_diff);

        return;
    }
}