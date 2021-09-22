import java.util.*;

public class Main {
    static int[][] Map = new int[101][101];
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
                for(int d1 = 1; d1 <= N; d1++) {
                    for(int d2 = 1; d2 <= N; d2++) {
                        if (!(x < x + d1 + d2 && x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N)) break;
                        
                        int[][] Tmp = new int[101][101];
                        for(int i = 0; i <= d1; i++) {
                            Tmp[x + i][y - i] = 5;
                        }
                        for(int i = 0; i <= d2; i++) {
                            Tmp[x + i][y + i] = 5;
                        }
                        for(int i = 0; i <= d2; i++){
                            Tmp[x + d1 + i][y - d1 + i] = 5;
                        }
                        for(int i = 0; i <= d1; i++){
                            Tmp[x + d2 + i][y + d2 - i] = 5;
                        }

                        for(int i = 1; i <= N; i++) {
                            int cnt = 0;
                            int[] pos = new int[2];
                            for(int j = 1; j <= N; j++) {
                                if (Tmp[i][j] == 5) {
                                    pos[cnt++] = j;
                                }
                                if (cnt == 2) {
                                    for(int k = pos[0] + 1; k < pos[1]; k++)
                                        Tmp[i][k] = 5;
                                    break;
                                } 
                            }
                        }

                        for(int r = 1; r < x + d1; r++) {
                            for(int c = 1; c <= y; c++) {
                                if (Tmp[r][c] == 5) continue;
                                Tmp[r][c] = 1;
                            }
                        }

                        for(int r = 1; r <= x + d2; r++) {
                            for(int c = y + 1; c <= N; c++) {
                                if (Tmp[r][c] == 5) continue;
                                Tmp[r][c] = 2;
                            }
                        }
                        
                        for(int r = x + d1; r <= N; r++) {
                            for(int c = 1; c < y - d1 + d2; c++) {
                                if (Tmp[r][c] == 5) continue;
                                Tmp[r][c] = 3;
                            }
                        }

                        for(int r = x + d2 + 1; r <= N; r++) {
                            for(int c = y - d1 + d2; c <= N; c++) {
                                if (Tmp[r][c] == 5) continue;
                                Tmp[r][c] = 4;
                            }
                        }
                        
                        int[] sum = new int[6];
                        for(int i = 1; i <= N; i++){
                            for(int j = 1; j <= N; j++) {
                                sum[Tmp[i][j]] += Map[i][j];
                            }
                        }
                        
                        List<Integer> List = new ArrayList<>();
                        for(int i = 1; i <= 5; i++) 
                            List.add(sum[i]);
                        
                        min_diff = Math.min(min_diff, Collections.max(List) - Collections.min(List));
                        
                    }
                }
            }
        }

        System.out.println(min_diff);

        return;
    }
}