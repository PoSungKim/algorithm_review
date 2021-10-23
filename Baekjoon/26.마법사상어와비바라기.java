import java.util.*;
import java.io.*;

public class Main {
    public static class Cloud {
        int y, x;
        public Cloud(int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public int hashCode() {
            return ( Integer.toString(y) + Integer.toString(x) ).hashCode();
        }
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Cloud) {
                Cloud o = (Cloud) obj;
                return this.y == o.y && this.x == o.x;
            }
            return false;
        }
    }

    public static int[][] Dirs = new int[][] { {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1,1}, {1, 0}, {1, -1} };
    public static List<Cloud> RainClouds = new ArrayList<>();
    public static int[][] Buckets = new int[50][50];
    public static int[][] Moves = new int[100][2];
    public static int N, M;

    public static void getInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) Buckets[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 2; j++) {
                Moves[i][j] = Integer.parseInt(st.nextToken());
                if (j == 0) Moves[i][j]--;
            }
        }
        RainClouds.add(new Cloud(N - 1, 0));
        RainClouds.add(new Cloud(N - 1, 1));
        RainClouds.add(new Cloud(N - 2, 0));
        RainClouds.add(new Cloud(N - 2, 1));
    }

    public static void moveClouds(int[] Move) {
        int dir  = Move[0];
        int dist = Move[1];
        for(int i = 0; i < RainClouds.size(); i++) {
            Cloud curCloud = RainClouds.get(i);
            // Dirs[dir][0] * dist의 범위가 N을 넘어갈 수 있기 때문에, 몇 바퀴를 돌 수 있다.
            int nY = (curCloud.y + N + Dirs[dir][0] * dist % N) % N ;
            int nX = (curCloud.x + N + Dirs[dir][1] * dist % N) % N ;

            Buckets[nY][nX]++;
            curCloud.y = nY;
            curCloud.x = nX;
        }
    }

    public static void copyWater() {
        for(int i = 0; i < RainClouds.size(); i++) {
            Cloud curCloud = RainClouds.get(i);
            int y = curCloud.y;
            int x = curCloud.x;

            for(int dir = 1; dir < 8; dir+= 2){
                int nY = y + Dirs[dir][0] ;
                int nX = x + Dirs[dir][1] ;
                if ( nY < 0 || N <= nY || nX < 0 || N <= nX ) continue;
                if (Buckets[nY][nX] == 0) continue;

                Buckets[y][x]++;
            }

        }
    }

    public static void makeClouds() {
        List<Cloud> tmpList = new ArrayList<>();

        for(int i = 0; i < N; i++) { 
            for(int j = 0; j < N; j++) {
                if (Buckets[i][j] >= 2){ 
                    Cloud newCloud = new Cloud(i, j);
                    if (!RainClouds.contains(newCloud)) {
                        tmpList.add(newCloud);
                        Buckets[i][j] -= 2;
                    }
                }
            }
        }

        RainClouds = tmpList;
    }

    public static int countTotal() {
        int cnt = 0; for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) cnt += Buckets[i][j]; return cnt;
    }

    public static int solve() {
        for(int i = 0; i < M; i++) {
            moveClouds(Moves[i]);
            copyWater();
            makeClouds();
        }

        return countTotal();
    }

    public static void main(String[] args) throws IOException {
        getInput();
        System.out.println(solve());
    }
}