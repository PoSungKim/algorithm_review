import java.util.*;

public class Main {
    public static int[][] Board = new int[21][21];
    public static int N;

    public static int perm(int[][] Board, int dir, int cnt) {
        int[][] New_Board = deep_copy(Board);
        boolean[][] Visited = new boolean[21][21];
        if (cnt == 5) {
            int max_v = -1;
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    max_v = Math.max(max_v, New_Board[r][c]);
                }
            }
            return max_v;
        }

        if (dir == 0) {
            for(int c = 0; c < N; c++) {
                for(int r = 0; r < N - 1; r++) {
                    for(int k = r + 1; k < N; k++) {
                        if (New_Board[r][c] == New_Board[k][c]) {
                            New_Board[r][c] += New_Board[k][c];
                            New_Board[k][c] = 0;
                            break;
                        } else {
                            if (New_Board[k][c] != 0) break;
                        }
                    }
                }
                for(int r = 0; r < N; r++) {
                    if (New_Board[r][c] != 0) {
                        for(int k = 0; k < r; k++) {
                            if(New_Board[k][c] == 0) {
                                New_Board[k][c] = New_Board[r][c];
                                New_Board[r][c] = 0;
                            }
                        }
                    }
                }
            }
        } else if (dir == 1) {
            for(int c = 0; c < N; c++) {
                for(int r = N - 1; r > 0; r--) {
                    for(int k = r - 1; k >= 0; k--) {
                        if (New_Board[r][c] == New_Board[k][c]) {
                            New_Board[r][c] += New_Board[k][c];
                            New_Board[k][c] = 0;
                            break;
                        }  else {
                            if (New_Board[k][c] != 0) break;
                        }
                    }
                }
                for(int r = N - 1; r >= 0; r--) {
                    if (New_Board[r][c] != 0) {
                        for(int k = N - 1; k > r; k--) {
                            if(New_Board[k][c] == 0) {
                                New_Board[k][c] = New_Board[r][c];
                                New_Board[r][c] = 0;
                            }
                        }
                    }
                }
            }
        } else if (dir == 2) {
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N - 1; c++) {
                    for(int k = c + 1; k < N; k++) {
                        if (New_Board[r][c] == New_Board[r][k]) {
                            New_Board[r][c] += New_Board[r][k];
                            New_Board[r][k] = 0;
                            break;
                        } else {
                            if (New_Board[r][k] != 0) break;
                        }
                    }
                }
                for(int c = 0; c < N; c++) {
                    if (New_Board[r][c] != 0) {
                        for(int k = 0; k < c; k++) {
                            if(New_Board[r][k] == 0) {
                                New_Board[r][k] = New_Board[r][c];
                                New_Board[r][c] = 0;
                            }
                        }
                    }
                }
            }
        } else {
            for(int r = 0; r < N; r++) {
                for(int c = N - 1; c > 0; c--) {
                    for(int k = c - 1; k >= 0; k--) {
                        if (New_Board[r][c] == New_Board[r][k]) {
                            New_Board[r][c] += New_Board[r][k];
                            New_Board[r][k] = 0;
                            break;
                        }  else {
                            if (New_Board[r][k] != 0) break;
                        }
                    }
                }
                for(int c = N - 1; c >= 0; c--) {
                    if (New_Board[r][c] != 0) {
                        for(int k = N - 1; k > c; k--) {
                            if(New_Board[r][k] == 0) {
                                New_Board[r][k] = New_Board[r][c];
                                New_Board[r][c] = 0;
                            }
                        }
                    }
                }
            }
        }
        int max_v = -1;
        for(int i = 0; i < 4; i++)
            max_v = Math.max(max_v, perm(New_Board, i, cnt + 1));
        
        return max_v;
    }

    public static int[][] deep_copy(int[][] Board) {
        int[][] New_Board = new int[21][21];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                New_Board[i][j] = Board[i][j];
            }
        }
        return New_Board;
    }

    public static int solve(int[][] Board) {
        int max_v = -1;
        int[][] New_Board = deep_copy(Board);
        
        for(int dir = 0; dir < 4; dir++)
            max_v = Math.max(max_v, perm(Board, dir, 0));

        return max_v;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) Board[i][j] = sc.nextInt();
        
        System.out.println(solve(Board));
    }
}