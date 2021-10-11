import java.io.*;
import java.util.*;

public class Main {
    
    public static boolean[][][] Board = new boolean[31][11][2];
    public static final int INF = 987654321;
    public static int N, M, H, ans = INF;
    public static boolean found_answer = false;
    
    public static boolean solve() {
        for(int c = 1; c <= N; c++) {
            int cur_pos = c;
            for(int r = 1; r <= H; r++) 
                if (cur_pos < N && Board[r][cur_pos][1] && Board[r][cur_pos + 1][0]) 
                    cur_pos++;
                else if (0 < cur_pos && Board[r][cur_pos - 1][1] && Board[r][cur_pos][0])
                    cur_pos--;
            if (cur_pos != c) return false;
        }
        return true;
    }
    
    public static void comb(int y, int x, int cnt) {
        if (cnt > 3 || y * 10 + x == N * H) return;
        if (solve()) {
            ans =  Math.min(ans, cnt);
            found_answer = true;
        }
        
        for(int i = 1; i <= H; i++) {
            for(int j = 1; j <= N; j++) {
                if (y * 10 + x > i * 10 + j) continue;
                if (j < N && !Board[i][j][1] && !Board[i][j + 1][0]) {
                    Board[i][j][1] = Board[i][j + 1][0] = true;
                    comb(i, j, cnt + 1);
                    Board[i][j][1] = Board[i][j + 1][0] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); H = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            Board[a][b][1] = Board[a][b + 1][0] = true;
        }
        comb(0, 0, 0);
        System.out.println(found_answer? ans > 3? -1 : ans : -1);
    }
}