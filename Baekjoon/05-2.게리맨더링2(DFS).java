import java.util.*;

public class Main {
    static int[][] Map = new int[21][21];
    static int[][] Tmp = new int[21][21];
    static int N;

    public static void fill(int r, int c, int value) {
        if (r <= 0 || r > N || c <= 0 || c > N ) return;
        if (Tmp[r][c] != 0) return;

        Tmp[r][c] = value;
        fill(r - 1, c, value);
        fill(r + 1, c, value);
        fill(r, c - 1, value);
        fill(r, c + 1, value);
    }

    public static int solve() {
        int min_diff = 987654321;
        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                for(int d1 = 1; d1 < y; d1++) {
                    for(int d2 = 1; x + d1 + d2 <= N && y + d2 <= N; d2++) {
                        // int[][] Tmp = new int[21][21]; 처럼 for문 내에서 계속 new하면 메모리 사용량이 68% 정도 상승한다 (비효율적인 메모리 사용)
                        for(int i = 0; i <= 20; i++) Arrays.fill(Tmp[i], 0);

                        for(int i = 0; i <= d1; i++) {
                            Tmp[x + i][y - i] = 5;
                            Tmp[x + d2 + i][y + d2 - i] = 5;
                        }

                        for(int i = 0; i <= d2; i++) {
                            Tmp[x + i][y + i] = 5;
                            Tmp[x + d1 + i][y - d1 + i] = 5;
                        }
                        
                        // Boundary 선 나누기
                        for(int r = x - 1; r > 0; r--) 
                            Tmp[r][y] = 1;

                        for(int c = y + d2 + 1; c <= N; c++) 
                            Tmp[x + d2][c] = 2;
                        
                        for(int c = y - d1 - 1; c > 0; c--)
                            Tmp[x + d1][c] = 3;

                        for(int r = x + d1 + d2 + 1; r <= N; r++)
                            Tmp[r][y - d1 + d2] = 4;
                        
                        // 각 끝점의 값은 항상 일정하기 때문에 fill 메소드 사용 가능
                        fill(1, 1, 1);
                        fill(1, N, 2);
                        fill(N, 1, 3);
                        fill(N, N, 4);

                        int[] sum = new int[6];
                        for(int r = 1; r <= N; r++) 
                            for(int c = 1; c <= N; c++) 
                                sum[Tmp[r][c]] += Map[r][c];
                        
                        sum[5] += sum[0];
                        
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

        return min_diff;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 1; i <= N; i++) 
            for(int j = 1; j <= N; j++)
                Map[i][j] = sc.nextInt();
            
        System.out.println(solve());

        return;
    }
}