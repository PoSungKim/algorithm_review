// if(Math.abs(i - y) + Math.abs(j - x) == 1) : 인접 요소 
import java.util.*;

public class Main {
    public static Map<Integer, List<Integer>> Map = new LinkedHashMap<>();
    public static int[][] Board = new int[20][20];
    public static int N;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 0; i < N * N; i++) {
            int student = sc.nextInt();
            Map.put(student, new ArrayList<>());
            for(int j = 0; j < 4; j++) Map.get(student).add(sc.nextInt());
        }
    }

    public static int[] countAdj(int y, int x, int curStudent) {
        int loveCnt = 0, emptyCnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (i == y && j == x) continue;
                if (Math.abs(i - y) + Math.abs(j - x) == 1) {
                    if (Map.get(curStudent).contains(Board[i][j])) loveCnt++; 
                    if (Board[i][j] == 0) emptyCnt++;
                }
            }
        }

        return new int[] {loveCnt, emptyCnt};
    }

    public static int countSts() {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int curStudent = Board[i][j];
                int[] cntAdj = countAdj(i, j, curStudent);
                
                if (cntAdj[0] == 0) cnt += 0;
                else if(cntAdj[0] == 1) cnt += 1;
                else if (cntAdj[0] == 2) cnt += 10;
                else if (cntAdj[0] == 3) cnt += 100;
                else if (cntAdj[0] == 4) cnt += 1000;
            }
        }
        return cnt;
    }

    public static int solve() {
        
        for(Map.Entry<Integer,List<Integer>> Entry : Map.entrySet()) {
            int curStudent = Entry.getKey();
            List<Integer> curLoves = Entry.getValue();

            int maxAdj = 0, maxY = 0, maxX = 0;
            List<int[]> tmpPos = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if (Board[i][j] != 0) continue;

                    int[] cntAdj = countAdj(i, j, curStudent);
                    int[] sortCnt = new int[]{cntAdj[0], cntAdj[1], i, j};
                    tmpPos.add(sortCnt);

                }
            }

            Collections.sort(tmpPos, (a, b) -> {
                if (b[0] == a[0]) {
                    if (b[1] == a[1]) {
                        if (a[2] == b[2]) {
                            return a[3] - b[3];
                        }
                        return a[2] - b[2];
                    }
                    return b[1] - a[1];
                }
                return b[0] - a[0];
            });
            int[] finalPos = tmpPos.get(0);
            
            Board[finalPos[2]][finalPos[3]] = curStudent;
        }

        return countSts();
    }

    public static void main(String[] args){
        getInput();
        System.out.println(solve());
    }
}
