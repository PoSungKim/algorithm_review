import java.util.*;

public class Main {
    public static class Fish {
        int y, x, dir;
        public Fish(int y, int x, int dir) {
            this.y = y;
            this.x = x; 
            this.dir = dir;
        }
    }
    public static class Shark {
        int y, x, dir, sum;
        public Shark(int y, int x, int dir, int sum) {
            this.y = y;
            this.x = x; 
            this.dir = dir;
            this.sum = sum;
        }
    }
    public static int[][] Dirs = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    public static int[][] Board = new int[4][4];
    public static Fish[] Fish = new Fish[16];
    public static Shark Shark = new Shark(0, 0, 0, 0);

    public static int[][] copyBoard(int[][] Board){
        int[][] tmpBoard = new int[4][4];
        for(int i = 0; i < 4; i++) 
            for(int j = 0; j < 4; j++)
                tmpBoard[i][j] = Board[i][j];

        return tmpBoard;
    }

    public static Fish[] copyFish(Fish[] Fish) {
        Fish[] tmpFish = new Fish[16];
        for(int i = 0; i < 16; i++)
            // 이부분에서 Deep Copy를 해야지 Shallow Copy하면 X
            // tmpFish[i] = Fish[i] 절대로 안됌
            tmpFish[i] = new Fish(Fish[i].y, Fish[i].x, Fish[i].dir);

        return tmpFish;
    }

    public static Shark copyShark(Shark Shark) {
        return new Shark(Shark.y, Shark.x, Shark.dir, Shark.sum);
    }

    public static int dfs() {
        if (Shark.y < 0 || 3 < Shark.y || Shark.x < 0 || 3 < Shark.x)
            return Shark.sum;
        if (Board[Shark.y][Shark.x] == -1)
            return Shark.sum;
        
        // shark eat
        int targetFish = Board[Shark.y][Shark.x];
        Shark.dir   = Fish[targetFish].dir;
        
        Shark.sum += (targetFish + 1);

        Fish[targetFish].y = -1;
        Fish[targetFish].x = -1;
        Fish[targetFish].dir = -1;
        Board[Shark.y][Shark.x] = -1;
        
        // fish move
        for(int i = 0; i < 16; i++) {
            if (Fish[i].y == -1) continue;

            int cY = Fish[i].y;
            int cX = Fish[i].x;
            int cDir = Fish[i].dir;

            int nDir = cDir;
            int nY   = cY + Dirs[nDir][0];
            int nX   = cX + Dirs[nDir][1];
            while(nY < 0 || 3 < nY || nX < 0 || 3 < nX || (nY == Shark.y && nX == Shark.x)) {
                nDir = (nDir + 1) % 8;
                nY   = cY + Dirs[nDir][0];
                nX   = cX + Dirs[nDir][1];   
            }

            if (Board[nY][nX] != -1) {
                int tmpTarget = Board[nY][nX];
                Fish[tmpTarget].y = cY;
                Fish[tmpTarget].x = cX;
                Fish[i].y = nY;
                Fish[i].x = nX;
                Fish[i].dir = nDir;

                Board[nY][nX] = i;
                Board[cY][cX] = tmpTarget;                
            } else {
                Fish[i].y = nY;
                Fish[i].x = nX;
                Fish[i].dir = nDir;

                Board[nY][nX] = i;
                Board[cY][cX] = -1;
            }
        }

        // copy
        int[][] tmpBoard = copyBoard(Board);
        Fish[] tmpFish   = copyFish(Fish);
        Shark tmpShark   = copyShark(Shark);

        // next move
        int max_n = Shark.sum;
        for(int turn = 1; turn < 4; turn++){
            Shark.y = Shark.y + Dirs[Shark.dir][0] * turn;
            Shark.x = Shark.x + Dirs[Shark.dir][1] * turn;
            
            max_n = Math.max(max_n, dfs());
            
            Board = copyBoard(tmpBoard);
            Fish  = copyFish(tmpFish);
            Shark = copyShark(tmpShark);
        }
        return max_n;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                a--; b--;
                Fish[a] = new Fish(i, j, b);
                Board[i][j] = a;
            }
        }
        System.out.println(dfs());
    }
}
