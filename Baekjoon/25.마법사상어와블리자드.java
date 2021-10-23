import java.util.*;
import java.io.*;

public class Main {
    public static int[][] magicDirs = new int[][] { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int[][] Dirs = new int[][] { {-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public static int[] magicDir = new int[100], magicDist = new int[100];
    public static Queue<int[]> posQueue = new LinkedList<>();
    public static Queue<Integer> Queue = new LinkedList<>();
    public static Stack<Integer> Stack = new Stack<>();
    public static int[][][] Board = new int[50][50][2];
    public static int N, M;
    public static int cnt = 0, ans = 0;

    public static void getInput() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                Board[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            magicDir[i]  = Integer.parseInt(st.nextToken());
            magicDist[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void rotate(int y, int x, int d) {
        Board[y][x][1] = ++cnt;
        posQueue.offer(new int[] {y, x});
        if (y == 0 && x == 0) return;
        
        int nD = (d+1) % 4;
        int nY = y + Dirs[nD][0];
        int nX = x + Dirs[nD][1];

        if (Board[nY][nX][1] == 0) {
            rotate(nY, nX, nD);
        } else {
            rotate(y + Dirs[d][0], x + Dirs[d][1], d);
        }
    }

    public static void blizzard(int y, int x, int magicDir, int magicDist) {
        for(int i = 1; i <= magicDist; i++) {
            int nY = y + magicDirs[magicDir][0] * i;
            int nX = x + magicDirs[magicDir][1] * i;
            Board[nY][nX][0] = 0;
        }
        
    }

    public static void refill() {
        int cnt = 0;
        Stack.clear();

        for(int[] Pos : posQueue) {
            int y = Pos[0], x = Pos[1];
            int num = Board[y][x][0];
            
            if (num == 0) continue;
            Stack.push(num);
        }

        Queue.clear();
        Queue.addAll(Stack);

        for(int[] Pos : posQueue) {
            if (Queue.size() > 0 ) Board[Pos[0]][Pos[1]][0] = Queue.poll();
            else Board[Pos[0]][Pos[1]][0] = 0;
        }
    }

    public static boolean explode() {
        int initNum = Stack.size();
        int cnt = 0;
        Stack.clear();
        
        for(int[] Pos : posQueue) {
            int y = Pos[0], x = Pos[1];
            int num = Board[y][x][0];

            if (Stack.size() > 0 && Stack.peek() == 0) continue;

            if (Stack.size() == 0) {
                Stack.push(num);
                cnt = 1;
            }
            else {
                if (Stack.size() > 0 && Stack.peek() == num) {
                    cnt++;
                    Stack.push(num);
                } else {
                    if (cnt >= 4) {
                        int beforeNum = Stack.peek();
                        for(int i = 0; i < cnt; i++) Stack.pop();
                        ans += beforeNum * cnt;
                    }
                    Stack.push(num);
                    cnt = 1;
                }
            }
        }
        Queue.clear();
        Queue.addAll(Stack);
        int terNum = Queue.size();

        for(int[] Pos : posQueue) {
            if (Queue.size() > 0 ) Board[Pos[0]][Pos[1]][0] = Queue.poll();
            else Board[Pos[0]][Pos[1]][0] = 0;
        }

        return initNum != terNum;
    }

    public static void regroup() {
        Stack.clear();
        int cnt = 0;

        for(int[] Pos : posQueue) {
            int y = Pos[0], x = Pos[1];
            int num = Board[y][x][0];
            
            if (Stack.isEmpty()) {
                Stack.push(num);
                cnt = 1;
            } else {
                if (Stack.peek() == num) {
                    Stack.push(num); 
                    cnt++;
                } else {
                    int beforeNum = Stack.peek();
                    for(int i = 0; i < cnt; i++) Stack.pop();
                    Stack.push(cnt);
                    Stack.push(beforeNum);
                    
                    Stack.push(num);
                    cnt = 1;
                }
            }
        }

        Queue.clear();
        Queue.addAll(Stack);

        for(int[] Pos : posQueue) {
            int y = Pos[0], x = Pos[1];
            if (Queue.isEmpty()) Board[y][x][0] = 0;
            else Board[y][x][0] = Queue.poll();
        }
    }

    public static int solve() {
        rotate( (N)/2, (N)/2, 0);
        posQueue.poll();

        for(int i = 0; i < M; i++) {
            blizzard((N)/2, (N)/2, magicDir[i] - 1, magicDist[i]);
            refill();
            while(explode());
            regroup();
        }
        
        return ans;
    }
    public static void main(String[] args) throws IOException{
        getInput();
        System.out.println(solve());
    }
}