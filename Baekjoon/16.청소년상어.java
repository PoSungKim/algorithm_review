import java.util.Scanner;

public class Main {
    public static class Fish {
        // 1 <= num <= 16
        // 0 <= dir < 8
        int[] pos = new int[2];
        int num, dir;
        public Fish(int[] pos, int num, int dir) {
            this.pos = pos;
            this.num = num;
            this.dir = dir;
        }
    };
    public static int[][] Dirs = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    public static int[][][] Board = new int[4][4][2];
    public static boolean[] Eaten = new boolean[17];

    public static void fish_move(Fish Shark){
        for(int i = 1; i <= 16; i++) {
            if (Eaten[i]) continue;
            boolean flag = false;
            for(int j = 0; j < 4; j++) {
                if (flag) break;
                for(int k = 0; k < 4; k++) {
                    if (flag) break;
                    if (Board[j][k][0] == i) {
                        int cur_dir = Board[j][k][1];
                        int n_y = j + Dirs[cur_dir][0];
                        int n_x = k + Dirs[cur_dir][1];
                        int attempt = 1;
                        while(n_y < 0 || 3 < n_y || n_x < 0 || 3 < n_x || (Shark.pos[0] == n_y && Shark.pos[1] == n_x)) {
                            if (attempt == 8) break;
                            attempt++;
                            cur_dir = (cur_dir + 1) % 8;
                            n_y = j + Dirs[cur_dir][0];
                            n_x = k + Dirs[cur_dir][1];
                        }

                        if (attempt == 8) break;
                        
                        int tmp_0 = Board[n_y][n_x][0];
                        int tmp_1 = Board[n_y][n_x][1];
                        Board[n_y][n_x][0] = Board[j][k][0];
                        Board[n_y][n_x][1] = cur_dir;
                        if (tmp_0 != 0) {
                            Board[j][k][0] = tmp_0;
                            Board[j][k][1] = tmp_1;
                        }
                        flag = true;
                    }
                }
            }

        }
    }

    public static void print() {
        System.out.println();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.format("%2s ", Board[i][j][0]);
            }
            System.out.println();
        }
    }

    public static int shark_move(Fish Shark) {
        
        fish_move(Shark);
        print();
        int[][][] Board_Backup = new int[4][4][2];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++)  {
                for(int k = 0; k < 2; k++) {
                    Board_Backup[i][j][k] = Board[i][j][k];
                }
            }
        }

        int max_n = -1, cnt = 0;
        int n_y = Shark.pos[0];
        int n_x = Shark.pos[1];
        
        while(true) {
            n_y += Dirs[Shark.dir][0];
            n_x += Dirs[Shark.dir][1];
            if (cnt == 0 && (n_y < 0 || 3 < n_y || n_x < 0 || 3 < n_x || Eaten[Board[n_y][n_x][0]])) return Shark.num;
            cnt++;
            if (n_y < 0 || 3 < n_y || n_x < 0 || 3 < n_x) break;
            if (Eaten[Board[n_y][n_x][0]] || Board[n_y][n_x][0] == 0) continue;

            Eaten[Board[n_y][n_x][0]] = true;
            int tmp0 = Board[Shark.pos[0]][Shark.pos[1]][0];
            int tmp1 = Board[Shark.pos[0]][Shark.pos[1]][1];
            Board[Shark.pos[0]][Shark.pos[1]][0] = 0;
            Board[Shark.pos[0]][Shark.pos[1]][0] = 0;
            int tmp00 = Board[n_y][n_x][0];
            int tmp11 = Board[n_y][n_x][1];
            Board[n_y][n_x][0] = 0;
            Board[n_y][n_x][1] = 0;
            print();
            max_n = Math.max(max_n, shark_move(new Fish(new int[]{n_y, n_x}, Shark.num + tmp00, Board[n_y][n_x][1])));
            
            Eaten[Board[n_y][n_x][0]] = false;
            Board[Shark.pos[0]][Shark.pos[1]][0] = tmp0;
            Board[Shark.pos[0]][Shark.pos[1]][1] = tmp1;
            Board[n_y][n_x][0] = tmp00;
            Board[n_y][n_x][1] = tmp11;

            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++)  {
                    for(int k = 0; k < 2; k++) {
                        Board[i][j][k] = Board_Backup[i][j][k];
                    }
                }
            }

        }
        System.out.println(max_n);
        return max_n;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                Board[i][j][0] = sc.nextInt();
                Board[i][j][1] = sc.nextInt();
                Board[i][j][1]--;
            }
        }

        Fish Shark = new Fish(new int[]{0,0}, Board[0][0][0], Board[0][0][1]);
        Eaten[Shark.num] = true;
        Board[0][0][0] = Board[0][0][1] = 0;
        print();
        System.out.println(shark_move(Shark));
        
        // 물고기는 번호가 작은 물고기부터 이동
        // 물고기 이동 >> 상어 이동
        
        // 물고기의 경우 (한칸 씩 이동)
        // 이동 가능   : 빈칸 or 다른 물고기 칸(서로의 위치를 교환)
        // 이동 불가능 : 상어 or 공간의 경계

        // 이동할 수 있는 방향을 찾을 때까지 45도 반시계 회전
        // 이동할 수 있는 칸이 없으면 이동 X

        // 상어의 경우 (다수의 칸 이동)
        // 이동 가능   : 물고기 (물고기의 방향을 얻는다)
        // 이동 불가능 : 빈칸 >> 집으로 이동

    }
}