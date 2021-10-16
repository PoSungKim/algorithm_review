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
    public static int[][] Dirs = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

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

    public static int dfs(int[][] Board, Fish[] Fish, int shark_y, int shark_x, int sum) {
        if (shark_y < 0 || 3 < shark_y || shark_x < 0 || 3 < shark_x)
            return sum;
        if (Board[shark_y][shark_x] == -1)
            return sum;
        
        // copy
        int[][] tmpBoard = copyBoard(Board);
        Fish[] tmpFish   = copyFish(Fish);
        
        // shark eat
        int targetFish = tmpBoard[shark_y][shark_x];
        int sharkDir   = tmpFish[targetFish].dir;
        
        sum += (targetFish + 1);

        tmpFish[targetFish].y = -1;
        tmpFish[targetFish].x = -1;
        tmpFish[targetFish].dir = -1;
        tmpBoard[shark_y][shark_x] = -1;
        
        // fish move
        for(int i = 0; i < 16; i++) {
            if (tmpFish[i].y == -1) continue;

            int cY = tmpFish[i].y;
            int cX = tmpFish[i].x;
            int cDir = tmpFish[i].dir;

            int nDir = cDir;
            int nY   = cY + Dirs[nDir][0];
            int nX   = cX + Dirs[nDir][1];
            while(nY < 0 || 3 < nY || nX < 0 || 3 < nX || (nY == shark_y && nX == shark_x)) {
                nDir = (nDir + 1) % 8;
                nY   = cY + Dirs[nDir][0];
                nX   = cX + Dirs[nDir][1];   
            }

            if (tmpBoard[nY][nX] != -1) {
                int tmpTarget = tmpBoard[nY][nX];
                tmpFish[tmpTarget].y = cY;
                tmpFish[tmpTarget].x = cX;
                tmpFish[i].y = nY;
                tmpFish[i].x = nX;
                tmpFish[i].dir = nDir;

                tmpBoard[nY][nX] = i;
                tmpBoard[cY][cX] = tmpTarget;                
            } else {
                tmpFish[i].y = nY;
                tmpFish[i].x = nX;
                tmpFish[i].dir = nDir;

                tmpBoard[nY][nX] = i;
                tmpBoard[cY][cX] = -1;
            }
        }
        
        // next move
        int max_n = sum;
        for(int turn = 1; turn < 4; turn++){
            int nY = shark_y + Dirs[sharkDir][0] * turn;
            int nX = shark_x + Dirs[sharkDir][1] * turn;

            max_n = Math.max(max_n, dfs(tmpBoard, tmpFish, nY, nX, sum));
        }
        return max_n;
    }
    public static void main(String[] args) {
        int[][] Board = new int[4][4];
        Fish[] Fish = new Fish[16];
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
        System.out.println(dfs(Board, Fish, 0, 0, 0));
    }
}
