import java.util.*;

public class Main {
    public static int[][] Map = new int[101][101], Visited = new int[101][101];
    public static int[][] Dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int N, L, R, ones;

    public static void count(int i, int j, int value) {

        if (Visited[i][j] != 1)
            return;
        
        Visited[i][j] = 2;
        Map[i][j] = value;

        for(int dir = 0; dir < 4; dir++) {
            int n_i = i + Dirs[dir][0], n_j = j + Dirs[dir][1];
            if (n_i < 0 || N <= n_i || n_j < 0 || N <= n_j) continue;

            count(n_i, n_j, value);
        }

        return;
    }

    public static int join(int i, int j, int value) {
        
        if (Visited[i][j] != 0) 
            return 0;

        boolean flag = false;
        if (value != -1) {
            int diff = Math.abs(value - Map[i][j]);
            if (L <= diff && diff <= R) flag = true;
        } else flag = true;

        int sum = 0;
        if (flag) {           
            Visited[i][j] = 1;
            sum = Map[i][j];
            ones++;

            for(int dir = 0; dir < 4; dir++) {
                int n_i = i + Dirs[dir][0], n_j = j + Dirs[dir][1];
                if (n_i < 0 || N <= n_i || n_j < 0 || N <= n_j) continue;
                sum += join(n_i, n_j, Map[i][j]);
            }
        }

        return sum;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); L = sc.nextInt(); R = sc.nextInt();
        for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) Map[i][j] = sc.nextInt();

        boolean flag = false;
        int round = 0;

        do {
            for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) Visited[i][j] = 0;
            flag = false; int move = 0;
            
            for(int i = 0 ; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if (Visited[i][j] == 0) {
                        ones = 0;
                        int sum = join(i, j, -1);
                        if (ones > 1) {
                            count(i, j, sum / ones);
                            move++;
                        } else {
                            Visited[i][j] = 2;
                        }
                    }
                }
            }

            if (move > 0) {
                flag = true;
                round++;
            }
            
        } while(flag);

        System.out.println(round);
    }

}