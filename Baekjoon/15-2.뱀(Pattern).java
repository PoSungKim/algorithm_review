import java.util.*;

public class Main {

    public static boolean[][] Board = new boolean[101][101];
    public static Queue<int[]> Queue = new LinkedList<>();
    public static Deque<int[]> Deque = new ArrayDeque<>();
    public static int N, K, L;

    public static boolean is_not_okay(int c_y, int c_x){
        if ( c_y <= 0 || N < c_y  || c_x <= 0 || N < c_x ) return true;
        for(int[] Body : Deque) if (Arrays.equals(Body, new int[]{c_y, c_x})) return true;
        
        return false;
    }

    public static int solve() {
        int time = 0, c_y = 1, c_x = 1;
        int[] my_dir = new int[]{0, 1};
        Deque.offerFirst(new int[]{c_y, c_x});

        for(int t = 0;;) {
            int[] head = Deque.peekFirst();
            c_y = head[0]; c_x = head[1];

            if (!Queue.isEmpty() && Queue.peek()[0] == t) {
                int[] cur_dir = Queue.poll();
                if (my_dir[0] == 0) my_dir = new int[] { (cur_dir[1] == 0) ? my_dir[1] * -1 : my_dir[1], my_dir[0]};                 
                else my_dir = new int[] {my_dir[1], (cur_dir[1] != 0) ? my_dir[0] * -1 : my_dir[0]};                  
            }
            
            c_y += my_dir[0];
            c_x += my_dir[1];
            t++;
            if (is_not_okay(c_y, c_x)) {
                time = t;
                break;
            }
            Deque.offerFirst(new int[]{c_y, c_x});
            if (!Board[c_y][c_x]) Deque.pollLast();
            else Board[c_y][c_x] = false;
        }
        return time;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); K = sc.nextInt();
        // 사과 위치
        for(int i = 0; i < K; i++) Board[sc.nextInt()][sc.nextInt()] = true;
        L = sc.nextInt();
        // 방향 정보
        for(int i = 0; i < L; i++) Queue.offer(new int[]{sc.nextInt(), (sc.next().equals("L")) ? 0 : 1});
        System.out.println(solve());
    }
}