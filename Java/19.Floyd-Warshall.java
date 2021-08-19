import java.util.*;
import java.io.*;

public class FloydWarshall {
    public static final int INF = (int)1e9;
    public static final int MAX_N = 500;
    public static int[][] Graph = new int[MAX_N][MAX_N];
    public static int N, E;
    
    public static void dynamicProgramming() {

        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    Graph[i][j] = Math.min(Graph[i][j], Graph[i][k] + Graph[k][j]);
                }
            }
        }

    }

    public static void setUpGraph() throws Exception{
        
        Scanner sc = new Scanner(new File("19.data.txt"));
        N = sc.nextInt(); E = sc.nextInt();
    
        for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) Graph[i][j] = (i == j)? 0 : INF;

        for(int i = 0; i < E; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), cost = sc.nextInt();
            u--; v--;
            Graph[u][v] = cost;
        }

    }

    public static void showGraph() {
        System.out.println();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.format("%2s ", ( Graph[i][j] == INF) ? "* " : Graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception{
        System.out.println("======================================");
        System.out.println("Dynamic Programming으로 FloydWarshall");
        System.out.println("======================================");
        setUpGraph();
        System.out.println("======================================");
        System.out.println("Input Graph");
        System.out.println("======================================");
        showGraph();
        dynamicProgramming();
        System.out.println("======================================");
        System.out.println("Output Graph");
        System.out.println("======================================");
        showGraph();
    }
}