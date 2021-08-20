import java.util.*;
import java.io.*;

public class Kruskal {
    public static final int MAX_N = 20;
    public static List<int[]> Edges  = new ArrayList<>();
    public static int[] Parents = new int[MAX_N];
    public static int V, E;
    public static String fileName;

    public static void setUpEdges() throws Exception {
        Scanner sc = new Scanner(new File(fileName));
        V = sc.nextInt(); E = sc.nextInt();

        for(int i = 0; i < E; i++)  {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            Edges.add(new int[]{cost, u, v});
        }
        
    }

    public static void setParents() {
        for(int i = 1; i <= V; i++)
            Parents[i] = i;
    }

    public static void sortEdges() {
        Collections.sort(Edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
    }

    public static void showEdges() {
        for(int i = 0; i < E; i++) 
            System.out.println(Arrays.toString(Edges.get(i)));
    }

    public static int findParent(int u) {
        if (Parents[u] == u) return u;
        return Parents[u] = findParent(Parents[u]);
    }

    public static void unionParent(int u, int v) {
        int uRoot = findParent(u);
        int vRoot = findParent(v);

        if (uRoot > vRoot) Parents[uRoot] = vRoot;
        else Parents[vRoot] = uRoot;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("======================================");
        System.out.println("Union-Find로 Kruskal 구현");
        System.out.println("======================================");
        //fileName = "20-2.data.txt"; 
        fileName = "20-2.data2.txt";
        setUpEdges();
        setParents();
        sortEdges();
        System.out.println("======================================");
        System.out.format("[%s, %s, %s]\n", "u", "v", "cost");
        System.out.println("======================================");
        showEdges();
        int totalCost = 0;
        for(int i = 0; i < E; i++) {
            int[] curEdge = Edges.get(i);
            int cost = curEdge[0], u = curEdge[1], v = curEdge[2];
            if ( findParent(u) != findParent(v) ) {
                unionParent(u, v);
                totalCost += cost;
            }
        }
        System.out.println("======================================");
        System.out.println("Minimum Spanning Tree Cost : " + totalCost);
        System.out.println("======================================");
    }
}