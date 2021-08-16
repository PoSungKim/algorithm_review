import java.util.*;

public class DFS {
    static final int MAX_N = 5;
    static int N, E;
    static int[][] Graph = new int[MAX_N][MAX_N];
    static boolean[] visited = new boolean[MAX_N];

    public static void setUpGraph() {
        N = 5;
        E = 6;
        Graph[0][1] = Graph[1][0] = 1;
        Graph[0][2] = Graph[0][2] = 1;
        Graph[1][3] = Graph[3][1] = 1;
        Graph[1][4] = Graph[4][1] = 1;
        Graph[2][4] = Graph[4][2] = 1;
        Graph[3][4] = Graph[4][3] = 1;
    }

    public static void recur(int Node) {
        System.out.print(Node + " ");
        visited[Node] = true;
        for (int Next = 0; Next < N; Next++)
            if (Graph[Node][Next] == 1 && !visited[Next])
                recur(Next);
    }

    public static void stack(int Node) {
        Stack<Integer> S = new Stack<>();
        boolean[] visited = new boolean[MAX_N];
        S.push(Node);

        while (!S.isEmpty()) {
            int cur = S.pop();
            if (visited[cur] == true)
                continue;

            visited[cur] = true;
            System.out.print(cur + " ");

            for (int next = 0; next < N; next++) {
                if (Graph[cur][next] == 1 && !visited[next]) {
                    S.push(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        setUpGraph();
        System.out.println("=========================");
        System.out.println("재귀함수로 DFS");
        System.out.println("=========================");
        recur(0);
        System.out.println("\n=========================");
        System.out.println("Stack으로 DFS");
        System.out.println("=========================");
        stack(0);
        System.out.println("\n=========================");
        System.out.println("Stack으로 floodFill");
        System.out.println("=========================");
        showBoard();
        floodFill();
        showBoard();
    }

    public static int[][] Board = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 1, 1, 1, 1, 0 },
            { 0, 0, 0, 0, 0 } };
    public static int[][] Dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static class Point {
        int row, col;

        Point(int r, int c) {
            row = r;
            col = c;
        }
    }

    public static void floodFill() {
        Stack<Point> S = new Stack<>();
        boolean[][] visited = new boolean[5][5];
        S.push(new Point(0, 0));

        while (!S.empty()) {
            Point cur = S.pop();
            if (visited[cur.row][cur.col])
                continue;
            if (Board[cur.row][cur.col] == 1)
                continue;
            visited[cur.row][cur.col] = true;
            Board[cur.row][cur.col] = 3;
            for (int i = 0; i < 4; i++) {
                Point next = new Point(cur.row + Dirs[i][0], cur.col + Dirs[i][1]);
                if (next.col < 0 || next.col >= 5 || next.row < 0 || next.row >= 5)
                    continue;
                if (visited[next.row][next.col])
                    continue;
                if (Board[next.row][next.col] == 1)
                    continue;
                S.push(next);
            }
        }
    }

    public static void showBoard() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                System.out.print(Board[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}