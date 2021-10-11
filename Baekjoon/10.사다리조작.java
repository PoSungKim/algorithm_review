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
                if (Board[r][cur_pos][1]) 
                    cur_pos++;
                else if (Board[r][cur_pos][0])
                    cur_pos--;

            if (cur_pos != c) return false;
        }
        return true;
    }
    
    public static void comb2(int y, int x, int cnt) {
        if (cnt > 3) return;
        if (is_okay()) ans =  Math.min(ans, cnt);
        
        // 첫 i 값이 y이기 때문에, 해당 케이스에서만 j 값이 x부터 시작
        // i가 y값일 때의 Iteration이 다 돌면, j는 1부터 돌아도 무방한데, 
        // 이것도 결국에는 2중 for문 Iteration을 발생시키니 비효율적!
        // comb()함수처럼 int pos의 값을 ++ 하면서 %, / 연사으로 정확한 지점에 대한 연산만 진행하는게 효율적
        for(int i = y; i <= H; i++) {
            for(int j = (y == i) ? x : 1; j <= N; j++) {
                if (j < N && !Board[i][j][1] && !Board[i][j + 1][0]) {
                    Board[i][j][1] = Board[i][j + 1][0] = true;
                    comb2(i, j + 2, cnt + 1);
                    Board[i][j][1] = Board[i][j + 1][0] = false;
                }
            }
        }
    }
    // 2차원 배열도 1차원 배열과 같다 >> pos를 +1씩 하면서 /, % 연산으로 올바른 position을 찾을 수 있다
    public static int comb(int pos, int cnt) {
        //왼 : 3개를 골랐을 때
        //오 : 3개 미만을 골랐을 때
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