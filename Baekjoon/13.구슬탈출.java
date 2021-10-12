import java.util.*;
// '.' : 빈 칸
// '#' : 장애물 또는 벽
// 'O' : 구멍의 위치
// 'R' : 빨간 구슬의 위치 
// 'B' : 파란 구슬의 위치

public class Main {
    public static class Balls {
        int r_y, r_x, b_y, b_x, cnt;
        public Balls(int r_y, int r_x, int b_y, int b_x, int id) {
            this.r_y = r_y;
            this.r_x = r_x;
            this.b_y = b_y;
            this.b_x = b_x;
            this.cnt = 0;
        }
        @Override
        public String toString () {
            return String.format("\nRED(%s, %s), BLUE(%s, %s) \n", r_y, r_x, b_y, b_x);
        }
    }
    public static Queue<Balls> Queue = new LinkedList<>();
    public static char[][] Board = new char[11][11];
    public static int N, M;

    public static int BFS(){
        
        while(!Queue.isEmpty()) {
            Balls Cur_Balls = Queue.poll();

        }

        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        // Scanner에서는 char을 인식해서 가져오는 Method를 제공하지 않기 때문에
        // next()로 String 값을 가져온 뒤에 toCharArray()로 변환해서 넣어주면 효율적으로 처리 가능
        Balls Start_Ball = new Balls(0,0,0,0,0);
        for(int i = 0; i < N; i++) {
            Board[i] = sc.next().toCharArray();
            for(int j = 0; j < M; j++) {
                if (Board[i][j] == 'R') {
                    Start_Ball.r_y = i;
                    Start_Ball.r_x = j;
                } else if (Board[i][j] == 'B') {
                    Start_Ball.b_y = i;
                    Start_Ball.b_x = j;
                }
            }
        }
        System.out.println(Start_Ball);
        Queue.offer(Start_Ball);
        System.out.println(BFS());
    }
}