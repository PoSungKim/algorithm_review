import java.util.*;

public class Main {
    public static class FireBall {
        int r, c, m, s, d;
        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    public static int[][] Dirs = new int[][] { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };
    // ArrayList를 담을 수 있는 2차원 Array 만들기
    public static List<FireBall>[][] BallList = new ArrayList[50][50];
    public static int N, M, K;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();
        
        for(int i = 0; i < N; i++) 
            for(int j = 0; j < N; j++) //2차원 Array의 각 Index에 ArrayList 넣기
                BallList[i][j] = new ArrayList<FireBall>();

        for(int i = 0; i < M; i++) {
            int r = sc.nextInt(); int c = sc.nextInt(); int m = sc.nextInt(); int s = sc.nextInt(); int d = sc.nextInt();
            BallList[r - 1][c - 1].add(new FireBall(r - 1, c - 1, m, s, d));
        }
    } 

    public static void makeOrder() {
        List<FireBall> tmpList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < BallList[i][j].size(); k++) {
                    FireBall Ball = BallList[i][j].get(k);

                    int nR = Ball.r + Dirs[Ball.d][0] * Ball.s;
                    int nC = Ball.c + Dirs[Ball.d][1] * Ball.s;
                    
                    // 다시 되돌아 오는 케이스
                    while (nR < 0) nR = N + nR;
                    while(N - 1 < nR) nR %= N;

                    while (nC < 0) nC = N + nC;
                    while(N - 1 < nC) nC %= N;
                    
                    Ball.r = nR;
                    Ball.c = nC;
                    tmpList.add(Ball);
                }
                // 위 for문에서 .remove()로 바로 삭제하면 for(int k = 0; k < BallList[i][j].size(); k++) 부분이 제대로 실행될 수 없기 때문에
                // 모든 연산이 종료된 이후 .clear()로 삭제
                BallList[i][j].clear();
            }
        }
        // 모든 연산이 끝나고 나서 넣어주기
        for(FireBall Ball : tmpList) BallList[Ball.r][Ball.c].add(Ball);
    }

    public static ArrayList<FireBall> combineAndDivide(List<FireBall> BallList, int r, int c) {
        int totalM = 0;
        int totalS = 0;
        int totalEven = 0;
        for(FireBall Ball : BallList) {
            totalM += Ball.m;
            totalS += Ball.s;
            if (Ball.d % 2 == 0) totalEven++;
        }
        
        int nM = totalM / 5;             
        if (nM == 0) return new ArrayList<>();
        int nS = totalS/BallList.size();

        int[] tmpDirs = new int[4];
        if (totalEven == BallList.size() || totalEven == 0) tmpDirs = new int[] {0, 2, 4, 6};
        else tmpDirs = new int[] {1, 3, 5, 7};
        
        ArrayList<FireBall> tmpList = new ArrayList<>();
        for(int i = 0; i < 4; i++) tmpList.add(new FireBall(r, c, nM, nS, tmpDirs[i]));
        
        return tmpList;
    }

    public static void checkCnt() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (BallList[i][j].size() >= 2) {
                    List<FireBall> tmpList = combineAndDivide(BallList[i][j], i, j);
                    BallList[i][j] = tmpList;
                }
            }
        }
    }

    public static int countM() {
        int totalM = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < BallList[i][j].size(); k++) {
                    totalM += BallList[i][j].get(k).m;
                }
            }
        }

        return totalM;
    }

    public static int solve() {
        for(int round = 0; round < K; round++) {
            makeOrder();
            checkCnt();
        }

        return countM();
    }
     public static void main(String [] args) {
        getInput();
        System.out.println(solve());
    }
}