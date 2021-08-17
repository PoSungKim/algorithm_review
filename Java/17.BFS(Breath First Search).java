import java.util.*;

public class BFS {
    public static int N, E;
    public static final int MAX_N = 10;
    public static int[][] Graph = new int[MAX_N][MAX_N];
    public static boolean[] Visited = new boolean[MAX_N];

    public static void Queue(int Node) {
        Queue<Integer> Queue = new LinkedList<>();
        Visited[Node] = true;
        Queue.offer(Node);

        while (!Queue.isEmpty()) {
            int curNode = Queue.poll();
            System.out.print(curNode + " ");

            for (int nextNode = 0; nextNode < N; nextNode++) {
                if (Visited[nextNode] || Graph[curNode][nextNode] == 0)
                    continue;
                Visited[nextNode] = true;
                Queue.offer(nextNode);
            }
        }
        System.out.println();
    }

    public static void setUpGraph() {
        N = 5;
        E = 6;
        Graph[0][1] = Graph[1][0] = 1;
        Graph[0][2] = Graph[2][0] = 1;
        Graph[1][3] = Graph[3][1] = 1;
        Graph[1][4] = Graph[4][1] = 1;
        Graph[2][4] = Graph[4][2] = 1;
        Graph[4][3] = Graph[3][4] = 1;
    }

    public static int[][] Board = { 
        { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 },{ 0, 0, 0, 0, 0 } 
    };
    public static int[][] Dirs = { 
        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } 
    };

    public static void showBoard() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.format("%3d", Board[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int shortestPath(int y, int x, int dy, int dx) {
        int N = 5;
        int[] Point = new int[]{y, x, 0};
        Queue<int[]> Queue = new LinkedList<>();
        boolean[][] Visited = new boolean[MAX_N][MAX_N];
        
        Queue.offer(Point);
        Visited[Point[0]][Point[1]] = true;

        while(!Queue.isEmpty()) {
            int[] curPoint = Queue.poll();
            Board[curPoint[0]][curPoint[1]] = curPoint[2];
            if (curPoint[0] == dy && curPoint[1] == dx) return curPoint[2];

            for(int i = 0; i < 4; i ++ ){
                int[] nextPoint = new int[]{curPoint[0] + Dirs[i][0], curPoint[1] + Dirs[i][1], curPoint[2] + 1 };
                if (nextPoint[0] < 0 || nextPoint[1] < 0 || N -1 < nextPoint[0] || N - 1 < nextPoint[1]) continue;
                if (Visited[nextPoint[0]][nextPoint[1]]  || Board[nextPoint[0]][nextPoint[1]] == 1)  continue;
                
                Visited[nextPoint[0]][nextPoint[1]] = true;
                Queue.offer(nextPoint);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=========================");
        System.out.println("Queue로 BFS");
        System.out.println("=========================");
        setUpGraph();
        Queue(0);
        System.out.println("=========================");
        System.out.println("BFS로 Shortest Path");
        System.out.println("=========================");
        showBoard();
        System.out.println("Start from (0, 1) to (4, 2)");
        System.out.println("Shortest Path Length : " + shortestPath(0, 1, 4, 2));
        showBoard();
    }
}