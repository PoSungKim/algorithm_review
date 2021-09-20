import java.util.*;

public class Main {
    public static int R,C,K;
    public static int C_N = 3, R_N = 3;
    public static int[][] Board = new int[100][100];

    public static class Number {
        public int N;
        public int cnt;
        public Number(int N, int cnt) {
            this.N = N;
            this.cnt = cnt;
        }
    }

    public static void getInput() {
        Scanner SC = new Scanner(System.in);
        R = SC.nextInt(); C = SC.nextInt(); K = SC.nextInt();
        R--; C--;

        for(int i = 0 ; i < 3; i++)
            for(int j = 0 ; j < 3; j++)
                Board[i][j] = SC.nextInt();
    }

    public static int solve() {
        for(int t = 0; t <= 100; t++) {
            if (Board[R][C] == K)
                return t;

            if (R_N >= C_N) {
                for(int i = 0; i < R_N; i++) {
                    int[] cnt = new int[101];
                    for(int j = 0; j < C_N; j++) 
                        cnt[Board[i][j]]++;
                    
                    List<Number> List = new ArrayList<>();
                    for(int j = 1; j <= 100; j++) 
                        if (cnt[j] > 0)
                            List.add(new Number(j, cnt[j]));
                    
                    List.sort((a,b)-> a.cnt - b.cnt);
                    
                    int idx = 0;
                    for(Number num : List) {
                        if (idx >= 99)
                            break;

                        Board[i][idx++] = num.N;
                        Board[i][idx++] = num.cnt;
                    }
                    C_N = Math.max(C_N, idx);
                    for(;idx < 100; idx++)
                        Board[i][idx] = 0;
                }
                
            } else {
                for(int i = 0; i < C_N; i++) {
                    int[] cnt = new int[101];
                    for(int j = 0; j < R_N; j++) 
                        cnt[Board[j][i]]++;
                    
                    List<Number> List = new ArrayList<>();
                    for(int j = 1; j <= 100; j++) 
                        if (cnt[j] > 0)
                            List.add(new Number(j, cnt[j]));
                    
                    List.sort((a,b)->a.cnt - b.cnt);

                    int idx = 0;
                    for(Number num : List) {
                        if (idx >= 99)
                            break;
                        Board[idx++][i] = num.N;
                        Board[idx++][i] = num.cnt;
                    }
                    R_N= Math.max(R, idx);
                    for(;idx < 100; idx++)
                        Board[idx][i] = 0;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        getInput();
        System.out.println(solve());
    }
}