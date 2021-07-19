import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        double d = scan.nextDouble();
        scan.nextLine();
        String s = scan.nextLine();

        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}

* Scanner.nextInt(), scan.nextDouble(): Input Buffer에서 왼쪽에서 오른쪽으로 delimiter들을 무시하다가 Integer, Double가 보이면 데이터만 가져온다 
        * 즉, Input Buffer에서 '\n' 문자가 남게 된다
* Scanner.nextLine(): Enter, 즉 \n이 보일 때까지 읽는다
  * nextInt() 다음에 nextLine()을 호출하면 바로 \n이 보이니, Empty String을 반환한다 

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = Integer.parseInt(scan.nextLine());
        double d = Double.parseDouble(scan.nextLine());
        String s = scan.nextLine();

        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        double d = Double.parseDouble(br.readLine());
        String s = br.readLine();
        
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}
