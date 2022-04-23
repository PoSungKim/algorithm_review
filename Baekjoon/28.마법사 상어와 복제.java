// https://velog.io/@mincho920/%EB%B0%B1%EC%A4%8023290Java-%EB%A7%88%EB%B2%95%EC%82%AC-%EC%83%81%EC%96%B4%EC%99%80-%EB%B3%B5%EC%A0%9C%EC%95%88%EA%B0%84%EB%8B%A8-%ED%92%80%EC%9D%B4
// 주요 연산 : nd = (this.d - i + 8) % 8; 반대 45도로 진행될 때;
// 주요 특이점 : board 클래스를 생성해서 내부에 fishes 리스트, copies 리스트, scent 숫자 정보를 포함해서 Board[][] 2차원 배열로 사용하니 깔끔함
// 주요 체감 정보 : 번거로워도 클래스도 많이 나누고, 메소드도 최대한 나누는게 결국에는 편하다 (Board, Shark, Fish 클래스 및 내부 메소드들...)

import java.io.*;
import java.util.*;

public class Solution {

    public static class Board {
        public List<Fish> fishes = new ArrayList<>();
        public List<Fish> copies = new ArrayList<>();
        public int scent = 0;

        public void copy() {
            for(Fish fish : fishes) {
                copies.add(new Fish(fish.y, fish.x, fish.d));
            }
        }

        public void completeCopy() {
            for(Fish fish : copies) {
                board[fish.y][fish.x].fishes.add(new Fish(fish.y, fish.x, fish.d));
            }
            this.copies = new ArrayList<>();
        }
    }

    public static class Shark {
        int y, x;

        public Shark(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Integer[] findNextMove() {

            Integer[] nextMove = sDirsPerm.get(0);
            int fcnt = -1;

            for(Integer[] curMove : sDirsPerm ) {
                boolean[][] visited = new boolean[4][4];

                int ny = this.y;
                int nx = this.x;
                int cnt = 0;
                boolean isOkay = true;

                for(int curOrder : curMove) {
                    ny += sDirs[curOrder][0];
                    nx += sDirs[curOrder][1];

                    if (ny < 0 || nx < 0 || 4 <= ny || 4 <= nx) {
                        isOkay = false;
                        break;
                    }
                    if (!visited[ny][nx])
                        cnt += board[ny][nx].fishes.size();
                    visited[ny][nx] = true;
                }

                if (isOkay && fcnt < cnt) {
                    nextMove = curMove;
                    fcnt = cnt;
                }
            }

            return nextMove;
        }
    }

    public static class Fish {
        int id, y, x, d;

        public Fish(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }

        public Fish move() {
            int ny, nx, nd;
            for(int i = 0; i < 8; i++) {
                nd = (this.d - i + 8) % 8;
                ny = this.y + fDirs[nd][0];
                nx = this.x + fDirs[nd][1];

                if (ny < 0 || nx < 0 || 4 <= ny || 4 <= nx) continue;
                if (board[ny][nx].scent > 0) continue;
                if (ny == shark.y && nx == shark.x) continue;

                this.y = ny;
                this.x = nx;
                this.d = nd;

                break;
            }

            return this;
        }

        @Override
        public String toString() {
            return String.format("F(y : %s, x : %s, d: %s)", this.y, this.x, this.d);
        }
    }

    // fish dir : 대각선 포함
    public static int[][] fDirs = new int[][] {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    // shark dir : 대각선 미포함
    public static int[][] sDirs = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public static List<Integer[]> sDirsPerm = new ArrayList<>();

    public static int M, S;
    public static Board[][] board = new Board[4][4];
    public static Shark shark;

    public static void initSetting() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); S = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) board[i][j] = new Board();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            board[y][x].fishes.add(new Fish(y, x, d));
        }

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken()) - 1;
        int x = Integer.parseInt(st.nextToken()) - 1;
        shark = new Shark(y, x);

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                for(int k = 0; k < 4; k++)
                    sDirsPerm.add(new Integer[]{i, j, k});
    }

    public static void copyFish() {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                board[i][j].copy();
    }

    public static void moveFish() {
        List<Fish> nextFishes = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                for(Fish fish : board[i][j].fishes)
                    nextFishes.add(fish.move());
                board[i][j].fishes = new ArrayList<>();
            }
        }

        for(Fish fish : nextFishes)
            board[fish.y][fish.x].fishes.add(fish);
    }

    public static void moveShark() {
        Integer[] nextMove = shark.findNextMove();

        for(int curOrder : nextMove) {
            shark.y += sDirs[curOrder][0];
            shark.x += sDirs[curOrder][1];

            if (board[shark.y][shark.x].fishes.size() > 0) {
                board[shark.y][shark.x].scent = 3;
                board[shark.y][shark.x].fishes = new ArrayList<>();
            }
        }
    }

    public static void removeScent() {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if (board[i][j].scent > 0)
                    board[i][j].scent--;
    }

    public static void completeCopy() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                board[i][j].completeCopy();
            }
        }
    }

    public static int countFish() {
        int cnt = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                cnt += board[i][j].fishes.size();
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        initSetting();

        for(int i = 0; i < S; i++) {
            // 1. 모든 물고기 복제
            copyFish();

            // 2. 물고기 1칸 이동
            moveFish();

            // 3. 상어 3칸 이동
            moveShark();

            // 4. 냄새 삭제
            removeScent();

            // 5. 복제 마법 실행
            completeCopy();
        }

        System.out.println(countFish());
    }
}
