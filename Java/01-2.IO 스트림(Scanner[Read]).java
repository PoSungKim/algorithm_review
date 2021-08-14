import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Solution {
    public static void main(String[] args) throws FileNotFoundException {

        // 표준 입력으로 받는 형태 (터미널에 작성하는 방식의 표준 입력으로는 한 줄씩 들어옴)
        readStdin();

        // 한 줄씩 데이터가 나열되어 있고, 한 줄 내에서는 띄어쓰기 없이 ,로 연결되어 있는 형태
        readFile();

        // 한 줄씩 데이터가 나열되어 있고, 한 줄 내에서는 " "(whitespace)로 연결되어 있는 형태
        readFile2();
    }

    public static void readStdin() {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextLine());
        sc.close();
    }

    public static void readFile() throws FileNotFoundException {
        File file = new File("./txtFiles/ScannerRead(,).txt");
        Scanner sc = new Scanner(file);
        int TC = Integer.parseInt(sc.nextLine());
        System.out.println(TC);
        for (int tc = 0; tc < TC; tc++) {
            String curLine = sc.nextLine();
            System.out.println(Arrays.toString(curLine.split(",")));
        }
        sc.close();
    }

    public static void readFile2() throws FileNotFoundException {
        File file = new File("./txtFiles/ScannerRead(\" \").txt");
        Scanner sc = new Scanner(file);
        int TC = Integer.parseInt(sc.nextLine());
        for (int tc = 0; tc < TC; tc++) {
            String curLine = sc.nextLine();
            System.out.println(Arrays.toString(curLine.split(" ")));
        }
        sc.close();

        ////////////////////////////////////////////////////////////////////////////////////
        sc = new Scanner(file);
        sc.nextLine();
        System.out.println(sc.delimiter()); // \p{javaWhitespace}+
        sc.useDelimiter("\n"); // newline으로 변경해서 .next()가 .nextLine()처럼 사용될 수 있음
        for (int tc = 0; tc < TC; tc++) {
            String curLine = sc.next();
            System.out.println(Arrays.toString(curLine.split(" ")));
        }
        sc.close();
    }
}
