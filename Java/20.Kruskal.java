import java.util.*;
import java.io.*;

public class Kruskal {
    public static final int MAX_N = 100000;
    public static int[] Parents = new int[MAX_N];
    public static int N, E;

    // 서로소 집합 (Root Node가 같으면 같은 집합; 아니면 서로소 집합)
    // 부모 Arrays 활용
    public static int findParent(int node) {
        if (node == Parents[node]) return node; // Base Case
        return findParent(Parents[node]);       // Recursion Case
    }

    public static void unionParent(int firstNode, int secondNode) {
        int firstRoot  = findParent(firstNode);
        int secondRoot = findParent(secondNode);

        if (firstRoot < secondRoot) Parents[secondRoot] = firstRoot;
        else Parents[firstRoot] = secondRoot;
    }

    public static void unionFind() throws Exception{
        Scanner sc = new Scanner(new File("20.data.txt"));
        N = sc.nextInt(); E = sc.nextInt();

        // 자신의 부모를 자신으로 초기화
        for(int i = 1; i <= N ; i++) Parents[i] = i;

        // Union
        for(int i = 0; i < E; i++){
            int firstNode = sc.nextInt();
            int secondNode = sc.nextInt();
            unionParent(firstNode, secondNode);
        }
    }

    // 서로소 집합 (Root Node가 같으면 같은 집합; 아니면 서로소 집합)
    // 부모 Arrays 활용
    public static int findParent2(int node) {
        if (node == Parents[node]) return node;                 // Base Case
        return Parents[node] = findParent(Parents[node]);       // Recursion Case
    }

    public static void unionParent2(int firstNode, int secondNode) {
        int firstRoot  = findParent2(firstNode);
        int secondRoot = findParent2(secondNode);

        if (firstRoot < secondRoot) Parents[secondRoot] = firstRoot;
        else Parents[firstRoot] = secondRoot;
    }

    public static void unionFind2() throws Exception{
        Scanner sc = new Scanner(new File("20.data.txt"));
        N = sc.nextInt(); E = sc.nextInt();

        // 자신의 부모를 자신으로 초기화
        for(int i = 1; i <= N ; i++) Parents[i] = i;

        // Union
        for(int i = 0; i < E; i++){
            int firstNode = sc.nextInt();
            int secondNode = sc.nextInt();
            unionParent2(firstNode, secondNode);
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("======================================");
        System.out.println("1) 직속 부모를 기록하는 Union-Find 구현");
        System.out.println("======================================");
        unionFind();

        System.out.println("======================================");
        System.out.println("자신이 속한 집합 확인");
        System.out.println("======================================");
        for(int i = 1; i <= N; i++)
            System.out.print(findParent(i) + " ");
        
        System.out.println("\n======================================");
        System.out.println("자신의 부모 확인");
        System.out.println("======================================");    
        for(int i = 1; i <= N; i++)
            System.out.print(Parents[i] + " ");

        System.out.println("\n\n======================================");
        System.out.println("2) 최상 부모만 기록하는 Union-Find 구현");
        System.out.println("======================================");
        unionFind2();

        System.out.println("======================================");
        System.out.println("자신이 속한 집합 확인");
        System.out.println("======================================");
        for(int i = 1; i <= N; i++)
            System.out.print(findParent2(i) + " ");
        
        System.out.println("\n======================================");
        System.out.println("자신의 부모 확인");
        System.out.println("======================================");    
        for(int i = 1; i <= N; i++)
            System.out.print(Parents[i] + " ");
    }
}
