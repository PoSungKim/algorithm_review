import java.io.*;
import java.util.*;

public class Main {
    
    public static boolean[][][] Board = new boolean[31][11][2];
    public static final int INF = 987654321;
    public static int N, M, H, ans = INF;
    
    public static boolean is_okay() {
        for(int c = 0; c < N; c++) {
            int cur_pos = c;
            for(int r = 0; r < H; r++) 
                if (cur_pos < N - 1 && Board[r][cur_pos][1] && Board[r][cur_pos + 1][0]) 
                    cur_pos++;
                else if (0 < cur_pos && Board[r][cur_pos - 1][1] && Board[r][cur_pos][0])
                    cur_pos--;

            if (cur_pos != c) return false;
        }
        return true;
    }
    
    public static void comb2(int y, int x, int cnt) {
        if (cnt > 3 || y * N + x == N * H) return;
        if (is_okay()) ans =  Math.min(ans, cnt);
        
        for(int i = 1; i <= H; i++) {
            for(int j = 1; j <= N; j++) {
                if (y * N + x > i * N + j) continue;
                if (j < N && !Board[i][j][1] && !Board[i][j + 1][0]) {
                    Board[i][j][1] = Board[i][j + 1][0] = true;
                    comb2(i, j, cnt + 1);
                    Board[i][j][1] = Board[i][j + 1][0] = false;
                }
            }
        }
    }

    public static int comb(int pos, int cnt) {
        //왼 : 3개를 골랐을 때
        //오 : 3개 이하를 골랐을 때
        if (cnt == 3 || pos >= N * H) {
            if (is_okay()) return cnt;
            return INF;
        }
        
        int ret = INF;
        int y = pos / N;
        int x = pos % N;
        if (x < N - 1 && !Board[y][x][1] && !Board[y][x + 1][0]) {
            Board[y][x][1] = Board[y][x + 1][0] = true;
            ret = Math.min(ret, comb(pos + 2, cnt + 1));
            Board[y][x][1] = Board[y][x + 1][0] = false;
        }
        ret = Math.min(ret, comb(pos + 1, cnt));

        return ret;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); H = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            //첫 풀이) Board[a][b][1] = Board[a][b + 1][0] = true;
            Board[a - 1][b - 1][1] = Board[a - 1][b][0] = true;
        }
        //첫 풀이) comb2(1, 1, 0);
        //첫 풀이) System.out.println(ans > 3? -1 : ans);
        
        int ret = comb(0, 0);
        System.out.println(ret == INF ? -1 : ret);
    }
}