import java.util.*; 

public class Main {
    public static int[][] Board = new int[1<<6][1<<6], tmpBoard = new int[1<<6][1<<6]; 
    public static int[][] Dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int[] L = new int[1000];
    public static int N, Q;
    
    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); Q = sc.nextInt();
        for(int i = 0; i < (1 << N); i++) for(int j = 0; j < (1 << N); j++) Board[i][j] = sc.nextInt();
        for(int i = 0; i < Q; i++) L[i] = sc.nextInt();
    }

    public static void rotate90(int L) {
        L = 1 << L;

        for(int i = 0; i < (1 << N); i += L) {
            for(int j = 0; j < (1 << N); j += L) {
                for(int r = 0; r < L; r++) {
                    for(int c = 0; c < L; c++) {
                        tmpBoard[i + r][j + c] = Board[i + L - 1 - c][j + r]; // right
                        // else if (rotate == 'L') Board[i + r][j + c] = tmpBoard[i + c][j + L - 1 - r]; // left
                    }
                }
            }
        }

    }

    public static void melt() {
        boolean[][] tmpBoard2 = new boolean [1<<N][1<<N];

        for(int i = 0; i < (1<<N); i++) {
            for(int j = 0; j < (1<<N); j++) {
                if (tmpBoard[i][j] > 0 ) {
                    int cnt = 0;
                    for(int dir = 0; dir < 4; dir++) {
                        int nY = i + Dirs[dir][0];
                        int nX = j + Dirs[dir][1];
        
                        if (nY < 0 || (1 << N) <= nY || nX < 0 || (1 << N) <= nX) continue;
                        if (tmpBoard[nY][nX] == 0) continue;
                        cnt++;
                    }
                    if (cnt < 3) tmpBoard2[i][j] = true;
                }
            }
        }

        for(int i = 0; i < (1<<N); i++) {
            for(int j = 0; j < (1<<N); j++) {
                if (tmpBoard2[i][j]) {
                    tmpBoard[i][j]--;
                }
            }
        }
    }

    public static int countGroups() {
        boolean[][] Visited = new boolean[1 << N][1 << N];
        int cntGroup = 0;
        
        for(int i = 0; i < (1 << N); i++){
            for(int j = 0; j < (1 << N); j++) {
                if (Visited[i][j]) continue;
                if (Board[i][j] == 0) continue;
                cntGroup = Math.max(cntGroup, countGroup(Visited, i, j));
            }
        }

        return cntGroup;
    }

    public static int countGroup (boolean[][] Visited, int y, int x) {
        Queue<int[]> Queue = new LinkedList<>();
        Queue.offer(new int[]{y, x});
        Visited[y][x] = true;
        int cnt = 0;
        while(!Queue.isEmpty()) {
            int[] curPos = Queue.poll();
            cnt++;

            for(int dir = 0; dir < 4; dir++) {
                int nY = curPos[0] + Dirs[dir][0];
                int nX = curPos[1] + Dirs[dir][1];

                if (nY < 0 || (1 << N) <= nY || nX < 0 || (1 << N) <= nX) continue;
                if (Visited[nY][nX]) continue;
                if (Board[nY][nX] == 0) continue;

                Visited[nY][nX] = true;
                Queue.offer(new int[] {nY, nX});
            }
        }

        return cnt;
    }

    public static int countSum() {
        int sum = 0; for(int i = 0; i < (1 << N); i++) for(int j = 0; j < (1 << N); j++) sum += Board[i][j]; return sum;
    }

    public static void solve() {

        for(int i = 0; i < Q; i++) {
            tmpBoard = new int[2 << N][2 << N]; 
            rotate90(L[i]);
            melt();
            Board = tmpBoard;
        }
        
        System.out.println(countSum());
        System.out.println(countGroups());
        return;
    }
    public static void main(String[] args) {
        getInput();
        solve();
    }
}
