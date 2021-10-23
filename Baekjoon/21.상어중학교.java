import java.util.*;

public class Main {
    public static class Group implements Comparable<Group>{ 
        public List<int[]> List;
        public int color, cntTotal, cntZero;
        public int[] stdBlock;
        
        public Group(int color, List<int[]> List) {
            this.color = color;
            this.List = List;
            this.cntTotal = this.List.size();
            this.cntZero = findZero();
            this.stdBlock = List.get(0);
            // this.stdBlock = findStdBlock();
        }

        @Override
        public int compareTo(Group b) {
            if (b.cntTotal == cntTotal) {
                if (b.cntZero == cntZero) {
                    if (b.stdBlock[0] == stdBlock[0]) {
                        return b.stdBlock[1] - stdBlock[1];
                    }
                    return b.stdBlock[0] - stdBlock[0];
                }
                return b.cntZero - cntZero;
            }
            return b.cntTotal - cntTotal;
        }

        public int[] findStdBlock() {
            Collections.sort(this.List, (a, b) -> {
                if (Board[a[0]][a[1]][0] == Board[b[0]][b[1]][0]) {
                    if (a[0] == b[0]) return a[1] - b[1];
                    return a[0] - b[0];
                }
                return Board[b[0]][b[1]][0] - Board[a[0]][a[1]][0];
            });
            return this.List.get(0);
        }

        public int findZero() {
            int cnt = 0;
            for(int[] Point : this.List) if (Board[Point[0]][Point[1]][0] == 0) cnt++;
            return cnt;
        }

    }
    
    public static int[][] Dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    public static int[][][] Board = new int[20][20][2];
    public static int N,M;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Board[i][j][0] = sc.nextInt();
            }
        }
    }

    public static void resetBoard() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Board[i][j][1] = 0;
            }
        }
    }

    public static List<int[]> findGroup(int y, int x) {
        List<int[]> tmpList = new ArrayList<>();
        int curColor = Board[y][x][0];
        
        Stack<int[]> Stack = new Stack<>();
        Stack.push(new int[]{y, x});
        
        while(!Stack.isEmpty()) {
            int[] curPos = Stack.pop();
            if (Board[curPos[0]][curPos[1]][1] == 1) continue;
            
            Board[curPos[0]][curPos[1]][1] = 1;
            tmpList.add(curPos);

            for(int dir = 0; dir < 4; dir++) {
                int nY = curPos[0] + Dirs[dir][0];
                int nX = curPos[1] + Dirs[dir][1];
                if (nY < 0 || N - 1 < nY || nX < 0 || N - 1 < nX) continue;

                if (Board[nY][nX][0] == curColor || Board[nY][nX][0] == 0) {
                    Stack.push(new int[] {nY, nX});
                }
            }
        }

        for(int[] Point : tmpList) if (Board[Point[0]][Point[1]][0] == 0) Board[Point[0]][Point[1]][1] = 0;
        
        return tmpList;
    }

    public static int removeBlock(Group Group) {
        int sum = 0;
        for(int[] Point : Group.List) Board[Point[0]][Point[1]][0] = -2;
        return Group.cntTotal * Group.cntTotal;
    }

    public static void dropBlock() {
        for(int i = N - 2; i >= 0; i--) {
            for(int j = 0; j < N; j++) {
                if (Board[i][j][0] >= 0) {
                    int nY = i;
                    for(int k = i + 1; k < N; k++) {
                        if (Board[k][j][0] == -2)  {
                            nY = k;
                        }
                        if (Board[k][j][0] == -1 || Board[k][j][0] >= 0) {
                            nY = k - 1;
                            break;
                        }
                    }
                    if (nY != i) {
                        Board[nY][j][0] = Board[i][j][0];
                        Board[i][j][0] = -2;
                    }
                }
            }
        }
    }

    public static  int[][][] rotate90() {
        int[][][] tmpBoard = new int[20][20][2];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) { 
                tmpBoard[N - 1 - j][i] = Board[i][j];
            }
        }
        return tmpBoard;
    }

    public static int solve() {
        int ans = 0;
        
        while(true) {
            resetBoard();
            List<Group> List = new ArrayList<>();        
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if (Board[i][j][0] > 0 && Board[i][j][1] == 0) {
                        List<int[]> tmpList = findGroup(i, j);
                        if (tmpList.size() < 2) continue;
                        
                        List.add(new Group(Board[i][j][0], tmpList));
                    }
                }
            }
            if (List.size() == 0) break;
            Collections.sort(List);

            int sum = removeBlock(List.get(0));
            ans += sum;

            dropBlock();
            Board = rotate90();
            dropBlock();
        }

        return ans;
    }

    public static void main(String[] args) {
        getInput();
        System.out.println(solve());
    }
}