import java.util.*;


public class Main{
    public static final int MAX_N = 100;
    public static int[][] Board = new int[MAX_N + 1][MAX_N + 1];
    public static int R, C, K;
    public static int RN = 3, CN = 3;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt(); C = sc.nextInt(); K = sc.nextInt();
        for(int i = 1; i <= 3; i++) for(int j = 1; j <= 3; j++) Board[i][j] = sc.nextInt();   
    }

    public static void showBoard() {
        System.out.println();
        for(int i = 1; i <= RN; i++)  {
            for(int j = 1; j <= CN; j++) {
                System.out.print(Board[i][j] + " ");
            }
            System.out.println();
        }        
        System.out.println();
    }
    public static void sortRow(int RowN) {
        Map<Integer, Integer> Map = new HashMap<>();
        for(int i = 1; i <= CN; i++) 
            if (Board[RowN][i] != 0)
                Map.put(Board[RowN][i], Map.getOrDefault(Board[RowN][i], 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> PQ = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                if (a.getValue() == b.getValue()) return a.getKey() - b.getKey();
                return a.getValue() - b.getValue();
            }
        });
        PQ.addAll(Map.entrySet());
        int cnt = 0;
        while(!PQ.isEmpty()) {
            Map.Entry<Integer, Integer> entry = PQ.poll();
            if (entry.getKey() == 0) continue;
            cnt++;
            if (cnt > 100) break;
            Board[RowN][cnt] = entry.getKey();
            cnt++;
            Board[RowN][cnt] = entry.getValue();
        }
        for(int i = cnt + 1; i <= 100; i++) Board[RowN][i] = 0;
        CN = Math.max(CN, cnt);
        if (CN > 100) CN = 100;
    }

    public static void ROper() {
        //System.out.println("ROPER");
        for(int i = 1; i <= RN; i++)
            sortRow(i);
    }

    public static void sortCol(int ColN) {
        Map<Integer, Integer> Map = new HashMap<>();
        for(int i = 1; i <= RN; i++) 
            if (Board[i][ColN] != 0)
                Map.put(Board[i][ColN], Map.getOrDefault(Board[i][ColN], 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> PQ = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                if (a.getValue() == b.getValue()) return a.getKey() - b.getKey();
                return a.getValue() - b.getValue();
            }
        });
        PQ.addAll(Map.entrySet());
        int cnt = 0;
        while(!PQ.isEmpty()) {
            Map.Entry<Integer, Integer> entry = PQ.poll();
            if (entry.getKey() == 0) continue;
            cnt++;
            if (cnt > 100) break;
            Board[cnt][ColN] = entry.getKey();
            cnt++;
            Board[cnt][ColN] = entry.getValue();
        }
        for(int i = cnt + 1; i <= 100; i++) Board[i][ColN] = 0;
        RN = Math.max(RN, cnt);
        if (RN > 100) RN = 100;
    }

    public static void COper() {
        //System.out.println("COPER");
        for(int i = 1; i <= CN; i++)
            sortCol(i);
    }

    public static void main(String[] args) {
        getInput();
        //showBoard();
        int ans = 0;
        for(int i = 0; i <= 100; i++) {
            if (Board[R][C] == K) {
                System.out.println(i);
                return;
            }
            if (RN >= CN) 
                ROper();
            else
                COper();
            //showBoard();
        }
        System.out.println(-1);
        return;
    }
}