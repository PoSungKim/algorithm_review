class BitOperation {

    public static void main(String[] args) {
        System.out.println("=========================");
        System.out.println("Bit Operation Check");
        System.out.println("=========================");
        bitOperCheck();
        System.out.println("=========================");
        System.out.println("Subset of {A, B, C, D}");
        System.out.println("=========================");
        subset(data.length);
        System.out.println("==============================");
        System.out.println("Sum(7) of {1, 2, 3, 4, 5, 6}");
        System.out.println("==============================");
        Sum7(data2.length);
    }

    public static int X = 3; // 00000011
    public static int Y = 6; // 00000110

    public static void bitOperCheck() {
        System.out.format("X    : %08d (%2d)\n", Integer.parseInt(Integer.toString(X, 2)), X);
        System.out.format("Y    : %08d (%2d)\n", Integer.parseInt(Integer.toString(Y, 2)), Y);
        System.out.format("X&Y  : %08d (%2d)\n", Integer.parseInt(Integer.toString(X & Y, 2)), X & Y);
        System.out.format("X|Y  : %08d (%2d)\n", Integer.parseInt(Integer.toString(X | Y, 2)), X | Y);
        System.out.format("X^Y  : %08d (%2d)\n", Integer.parseInt(Integer.toString(X ^ Y, 2)), X ^ Y);
        System.out.format("X<<2 : %08d (%2d)\n", Integer.parseInt(Integer.toString(X << 2, 2)), X << 2);
        System.out.format("Y>>2 : %08d (%2d)\n", Integer.parseInt(Integer.toString(Y >> 2, 2)), Y >> 2);
    }

    public static char[] data = { 'A', 'B', 'C', 'D' };

    public static void subset(int N) {
        for (int i = 0; i < (1 << N); i++) {
            System.out.print("{ ");
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    System.out.print(data[j] + " ");
                }
            }
            System.out.println("}");
        }
    }

    public static int[] data2 = { 1, 2, 3, 4, 5, 6 };

    public static void Sum7(int N) {
        for (int i = 0; i < (1 << N); i++) {
            if (Integer.bitCount(i) != 2)
                continue;

            int sum = 0;
            for (int j = 0; j < N; j++)
                if ((i & (1 << j)) != 0)
                    sum += data2[j];

            if (sum != 7)
                continue;

            for (int j = 0; j < N; j++)
                if ((i & (1 << j)) != 0)
                    System.out.print(data2[j] + " ");

            System.out.println();
        }
    }
}