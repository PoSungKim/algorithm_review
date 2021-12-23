import java.util.Arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

class Solution {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // 표준 입력으로 받는 형태 (터미널에 작성하는 방식의 표준 입력으로는 한 줄씩 들어옴)
        scannerReadStdin();

        // 한 줄씩 데이터가 나열되어 있고, 한 줄 내에서는 띄어쓰기 없이 ,로 연결되어 있는 형태
        // scannerReadFile();

        // 한 줄씩 데이터가 나열되어 있고, 한 줄 내에서는 " "(whitespace)로 연결되어 있는 형태
        // scannerReadFile2();

        // String 문자열도 받을 수 있다
        // scannerString();

        // 표준 입력으로 받는 형태 (BufferedReader)
        bufferedReaderStdin();

        // File 읽기로 받는 형태 (BufferedReader)
        bufferedReaderReadFile();
        
        // UTF-8 인코딩 File 읽기로 받는 형태 (BufferedReader)
        bufferedReaderReadFileUtf8()
    }

    public static void scannerReadStdin() {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextLine());
    }

    public static void scannerReadFile() throws FileNotFoundException {
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

    public static void scannerReadFile2() throws FileNotFoundException {
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
    
    public static void scannerString() {   
        Scanner sc = new Scanner("This is Test!");
        while(sc.hasNext()) {
            System.out.println(sc.next()); //This //is //Test!
        }
    }

    public static void bufferedReaderStdin() throws IOException {
        BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
        String hello = BR.readLine();
        String world = BR.readLine();
        System.out.println(hello + " " + world);
        BR.close();
    }

    public static void bufferedReaderReadFile() throws FileNotFoundException, IOException {
        File file = new File("log.txt");
        BufferedReader BR = new BufferedReader(new FileReader(file));
        System.out.println("bufferedReader >> " + BR.readLine());
        BR.close();
    }
    
    public static void bufferedReaderReadFileUtf8() throws FileNotFoundException, IOException {
        File file = new File("log.txt");
        BufferedReader BR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        System.out.println("bufferedReader >> " + BR.readLine());
        BR.close();
    }
}
