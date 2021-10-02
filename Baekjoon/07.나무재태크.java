import java.util.*;

public class Main {

    public static class Tree {
        int y, x, age;
        public Tree(int y, int x, int age) {
            this.y = y;
            this.x = x;
            this.age = age;
        }
    }

    public static PriorityQueue<Tree> PQ = new PriorityQueue<>((a, b)->{
        return a.age - b.age;
    });

    public static int[][] Dirs = new int[][]{ {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public static Queue<Tree> Q = new LinkedList<>();
    public static int[][] Yang_Bun = new int[10][10];
    public static int[][] Map = new int[10][10];
    public static int N, M, K;

    public static void winter() {
        for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) Map[i][j] += Yang_Bun[i][j];
    }

    public static void fall() {
        // 순서에 상관없이 Traverse만 필요하기 때문에 For Iterate 필요 
        // 삭제하는 로직이 필요없으며, while poll을 했다가 다시 넣는 로직은 PQ에 안 맞음
        for(Tree curTree : PQ) {
            if (curTree.age % 5 == 0) {
                for(int dir = 0; dir < 8; dir++) {
                    int n_y = curTree.y + Dirs[dir][0];
                    int n_x = curTree.x + Dirs[dir][1];
                    if (n_y < 0 || n_x < 0 || N <= n_y || N <= n_x) continue;
                    Q.offer(new Tree(n_y, n_x, 1));
                }
            }
        }
        PQ.addAll(Q);
        Q.clear();
        return;
    }

    public static void summer() {
        while(!Q.isEmpty()) {
            Tree curTree = Q.poll();
            Map[curTree.y][curTree.x] += curTree.age / 2;
        }
        return;
    }

    public static void spring() {
        Queue<Tree> Tmp = new LinkedList<>();

        while(!PQ.isEmpty()) {
            Tree curTree = PQ.poll();
            if (Map[curTree.y][curTree.x] >= curTree.age) {
                Map[curTree.y][curTree.x] -= curTree.age++;
                Tmp.offer(curTree);
            } else {
                Q.offer(curTree);
            }
        }

        PQ.addAll(Tmp);
        return;
    }

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();
        
        for(int i = 0; i < N; i++) 
            for(int j = 0; j < N; j++){
                Yang_Bun[i][j] = sc.nextInt();
                Map[i][j]      = 5;
            }

        for(int i = 0; i < M; i++)  {
            int y = sc.nextInt();
            int x = sc.nextInt();
            int age = sc.nextInt();
            PQ.offer(new Tree(--y, --x, age));
        }

        for(int t = 0; t < K; t++) {
            // 봄   - 양분 먹고 성장, 동일 칸에서는 어린 나무 부터
            spring();
            // 여름 - 죽은 나무 >> 양분 (자신의 나이 / 2), 소수점 버리고
            summer();
            // 가을 - 나이가 5의 배수인 나무 >> 번식 >> 나이 1인 나무 (8 방향)
            fall();
            // 겨울 - 양분 추가
            winter();
        }

        System.out.println(PQ.size());
        return ;
    }
}