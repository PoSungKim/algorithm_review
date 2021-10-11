import java.util.*;

public class Main {
    public static int[][] Dirs = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public static boolean[][] Board = new boolean [101][101];
    public static List<Integer> List = new ArrayList<>();
    public static int N;

    public static int Count() {
        int cnt = 0;
        for(int i = 0; i < 100; i++) 
            for(int j = 0; j < 100; j++) 
                if (Board[i][j] && Board[i][j + 1] && Board[i + 1][j + 1] && Board[i + 1][j]) 
                    cnt++;
        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for(int i = 0; i < N; i++) {
            List.clear();

            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();
            List.add(d);
            for(int j = 0; j < g; j++) {
                for(int k = List.size() - 1; k >= 0; k--) {
                    List.add((List.get(k) + 1) % 4);
                }
            }
            Board[y][x] = true;
            for(int j = 0; j < List.size(); j++) {
                y = y + Dirs[List.get(j)][0];
                x = x + Dirs[List.get(j)][1];
                Board[y][x] = true;
            }
        }
        System.out.println(Count());
    }
}