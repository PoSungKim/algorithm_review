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
        PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)->a[0] - b[0]);
        boolean [] Visited = new boolean[MAX_N];
        for(int i = 0; i < N; i++) Dist[i] = INF;
        
        // 항상 Dist를 업데이트 해주고, PQ에 넣어줘야 한다
        Dist[startNode] = 0;
        PQ.offer(new int[]{0, startNode});

        while(!PQ.isEmpty()) {
            int[] curInfo = PQ.poll();
            int u = curInfo[1];

            // DFS처럼 Visited 확인을 while 문 앞 딴에서 처리 (Priority Queue의 성질 때문)
            if (Visited[u]) continue;
            Visited[u] = true;
            for(int v = 0; v < N; v++) { 
                // Greedy Algorithm (그때 그때 가장 가까운 거리의 노드에 연결)
                // 그때 그때의 거리가 아니라 돌아가는 것이 더 짧을 수도 있다는 생각을 할 수도 있다
                // 하지만, 또 다른 각도에서 생각해보면, 에초에 더 짧은 거리로 갔기 때문에 더 짧은 거리로 돌아할 수도 있는 가능성이 있는거지
                // 더 먼 거리로 갔다가 돌아오면 당연히 더 긴 시간이 걸릴 수밖에 없다 >> 이 부분 때문에 Greedy Algorithm에 속해도 Global Optimum을 찾을 수 있는 것 같다
                if (Dist[v] > Dist[u] + Graph[u][v]) {
                    Dist[v] = Dist[u] + Graph[u][v];
                    PQ.offer(new int[]{Dist[v], v});
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
                Graph[i][j] = (i == j) ? 0 : INF;
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