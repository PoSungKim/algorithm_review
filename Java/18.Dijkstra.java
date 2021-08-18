import java.util.*;

public class Dijkstra  {

    public static final int MAX_N = 10;
    public static final int INF = 987654321;
    public static int N, E;
    public static int[][] Graph = new int[MAX_N][MAX_N]; //그래프의 모양 (Edge가 어떻게 연결되어 있는지)
    public static int[] Dist = new int[MAX_N];           //그래프의 노드 간의 거리 (<<시작 노드>>에서 다른 노드까지의 최소거리이기에 1차원 배열)    

    public static void listDist() {
        System.out.println("Listing the Distance");
        for(int i = 0; i < N; i++)
            System.out.print(Dist[i] + " ");
    }

    public static void findShortestPath(int startNode) {
        PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b)-> (a[0] - b[0]));
        boolean[] Visited = new boolean[MAX_N];
        for(int i = 0; i < N; i++) Dist[i] = INF;
        Dist[startNode] = 0;
        PQ.offer(new int[]{0, startNode});

        while(!PQ.isEmpty()) {
            int[] curNode = PQ.poll();
            System.out.println(Arrays.toString(curNode));
            if (Visited[curNode[1]]) continue;
            Visited[curNode[1]] = true;

            for(int i = 0; i < N; i++) {
                if (Dist[i] > Dist[curNode[1]] + Graph[curNode[1]][i]) {
                    Dist[i] = Dist[curNode[1]] + Graph[curNode[1]][i];
                    PQ.offer(new int[]{Dist[i], i});
                }
            }
        }
    }

    public static void setUpGraph() {
        N = 6; E = 9;
        //////////////////////////////////////////
        // Intialize the Graph before any Input //
        //////////////////////////////////////////
        for(int i = 0; i < N; i++) { 
            for(int j = 0; j < N; j++) {
                Graph[i][j] = (i == j)? 0 : INF; // 자기 자신끼리는 거리 = 0, 다른 노드 간의 INF
            }
        }
        //////////////////////////////////////////
        // Apply Distance between Nodes         //
        //////////////////////////////////////////
        Graph[0][1] = Graph[1][0] = 50;
        Graph[0][2] = Graph[2][0] = 30;
        Graph[1][3] = Graph[3][1] = 30;
        Graph[1][4] = Graph[4][1] = 70;
        Graph[2][3] = Graph[3][2] = 20;
        Graph[2][4] = Graph[4][2] = 40;
        Graph[3][4] = Graph[4][3] = 10;
        Graph[3][5] = Graph[5][3] = 80;
        Graph[4][5] = Graph[5][4] = 30;
    }
    public static void showGraph() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.format("%9d ", Graph[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args ) {
        setUpGraph();
        showGraph();
        findShortestPath(0);
        listDist();
    }
}