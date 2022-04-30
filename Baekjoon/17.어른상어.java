
// Obj[][] Board = new Obj[20][20]
// Obj[] Sharks
// 에서 Board 2차 배열의 타입이 Sharks의 인덱스 값을 저장하는 ArrayList로 했어도 좋았을 것 같음
// Board에서 가져온 인덱스 값으로 Obj[] Sharks 정보를 업데이트하는 형태도 하나의 방법 가능

import java.util.*;

public class Main {

    public static class Obj {
        int y, x, num, dir, type, move;
        public Obj(int y, int x, int num, int dir, int type) {
            this.y = y;
            this.x = x;
            this.num = num;
            this.dir = dir;
            this.type = type;
            this.move = K;
        }   
    }
    
    public static int[][] Dirs = new int[][] { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static Obj[][] Board = new Obj[20][20];
    public static int[][][] Pris;
    public static Obj[] Sharks;
    public static int N, M, K;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();
        
        Sharks = new Obj[M + 1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)  {
                int num = sc.nextInt();
                Board[i][j] = new Obj(i, j, num, 0, (num > 0)? 1 : 0); 
                if (num > 0) Sharks[num] = new Obj(i, j, num, 0, 1);
            }
        }

        for(int i = 1; i <= M; i++) {
            Sharks[i].dir = sc.nextInt() - 1;
            Board[Sharks[i].y][Sharks[i].x].dir = Sharks[i].dir;
        }
        
        Pris = new int[M + 1][4][4];
        for(int i = 1; i <= M; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    Pris[i][j][k] = sc.nextInt() - 1;    
                }
            }
        }
        
    }

    public static void print() {
        System.out.println();
        for(int i = 0; i < N; i++)  {
            for(int j = 0; j < N; j++) {
                System.out.format("%2s ", Board[i][j].num);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void spreadSmell() {
        for(int i = 1; i <= M; i++) {
            Obj curShark  = Sharks[i];
            if (curShark.type == 3) continue;

            Obj curBoard  = Board[curShark.y][curShark.x];
            curBoard.num  = -i;
            curBoard.type = 2;
            curBoard.move = K;
        }
    }

    public static void minusSmell() {
        for(int i = 0; i < N; i++)  {
            for(int j = 0; j < N; j++) {
                if (Board[i][j].type == 2) {
                    Board[i][j].move--;
                    if (Board[i][j].move == 0) {
                        Board[i][j].type = 0;
                        Board[i][j].num = 0;
                    }
                }
            }
        }
    }

    public static void sharkMove() {
        for(int i = 1;  i <= M; i++) {
            Obj curShark = Sharks[i];
            if (curShark.type == 3) continue;

            List<int[]> emptySmell = new ArrayList<>();
            List<int[]> mySmell = new ArrayList<>();
            
            for(int dir = 0; dir < 4; dir++) {
                int nD = Pris[curShark.num][curShark.dir][dir];
                int nY = curShark.y + Dirs[nD][0];
                int nX = curShark.x + Dirs[nD][1];
                
                if (nY < 0 || nY > N - 1 || nX < 0 || nX > N - 1) continue;
                
                if (Board[nY][nX].type == 0) emptySmell.add(new int[] {nY, nX, nD});
                else if (Board[nY][nX].type == 2 && Board[nY][nX].num == -i) mySmell.add(new int[] {nY, nX, nD});
            }

            if (emptySmell.size() == 0) {
                int ny = mySmell.get(0)[0];
                int nx = mySmell.get(0)[1];
                int nd = mySmell.get(0)[2];

                Sharks[curShark.num].y   = ny;
                Sharks[curShark.num].x   = nx;
                Sharks[curShark.num].dir = nd;

            } else {
                int ny = emptySmell.get(0)[0];
                int nx = emptySmell.get(0)[1];
                int nd = emptySmell.get(0)[2];

                Sharks[curShark.num].y   = ny;
                Sharks[curShark.num].x   = nx;
                Sharks[curShark.num].dir = nd;   
            }
        }
    }

    public static void checkMulti() {
        for (int i = 1; i <= M; i++) {
            Obj curShark = Sharks[i];
            if (curShark.type == 3) continue;
            for(int j = i + 1; j <= M; j++) {
                Obj oppoShark = Sharks[j];
                if (oppoShark.type == 3) continue;
                
                if (curShark.y == oppoShark.y && curShark.x == oppoShark.x) {
                    oppoShark.type = 3;
                }
            }
        }
    }

    public static void markSharks() {
        for (int k = 1; k <= M; k++) {
            Obj curShark = Sharks[k];
            if (curShark.type == 3) continue;
            Board[curShark.y][curShark.x].num = curShark.num;
            Board[curShark.y][curShark.x].move = K;
            Board[curShark.y][curShark.x].type = 1;
        }
    }

    public static boolean checkAnswer() {

        for (int i = 2; i <= M; i++) {
            Obj curShark = Sharks[i];
            if (curShark.type != 3) return false;
        }

        return true;
    }

    public static int solve(){ 

        int ans = 0;
        for(int r = 0; ;) {
            spreadSmell();
            r++;
            sharkMove();
            minusSmell();
            checkMulti();
            markSharks();
            
            if (checkAnswer()) {
                ans = r;
                break;
            }
            
            if (r >= 1000) {
                ans = -1;
                break;
            }
        }

        return ans;
    }
    public static void main(String[] args){
        // Input
        getInput();

        // Shark Move 
        System.out.println(solve());
    }
}
