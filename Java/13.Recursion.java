public class Recursion {

    public static int[][] Board = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 1, 1, 1, 1, 0 },
            { 0, 0, 0, 0, 0 } };

    public static void main(String[] args) {
        System.out.println("=========================");
        System.out.println("Factorial Recurison");
        System.out.println("=========================");
        System.out.format("4! = %d\n\n", factorial(4));

        System.out.println("=========================");
        System.out.println("Flood Fill Recurison");
        System.out.println("=========================");
        showBoard();
        floodFill(1, 1);
        showBoard();
    }

    public static int factorial(int N) {
        if (N == 0)
            return 1; // Base Case

        return N * factorial(N - 1); // Recursive Case
    }

    public static void showBoard() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                System.out.print(Board[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void floodFill(int y, int x) {
        if (y < 0 || x < 0 || x >= 5 || y >= 5)
            return;
        if (Board[y][x] == 1)
            return;

        Board[y][x] = 1;

        floodFill(y - 1, x);
        floodFill(y + 1, x);
        floodFill(y, x - 1);
        floodFill(y, x + 1);
    }
}