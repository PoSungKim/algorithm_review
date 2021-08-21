import java.util.*;
import java.io.*;

public class BinarySearch {
    public static final int MAX_N = 1000000;
    public static final int MAX_M = 2000000000;
    public static int[] List = new int[MAX_N];
    public static int N, M;

    public static int parametricSearch(int target, int left, int right) {
        int ans = (int)1e9;
        // 범위를 좁혀나가는 단위 : 1 
        // (물론 0.1, 0.01, 0.001... 등 소수점을 작게해서 보다 좁혀나갈 수 있따)
        int rangeDiff = 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            for(int i = 0 ; i < N; i++) {
                int diff = List[i] - mid;
                if (diff >= 0)
                    sum += diff;
            }
            if (target > sum) {
                right = mid - rangeDiff;
            }
            // 최적해를 찾기 위해서 범위를 계속 좁혀나가는 방식으로 
            // left, right가 교차할 때까지 범위를 (선택한 단위의 크기 : 1)만큼 계속 좁혀나간다
            else  { 
                ans = mid;
                left = mid + rangeDiff;
            }
        }
        return ans;
    }

    public static int binarySearch(int target, int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2 ;
            if (List[mid] == target)
                return mid;
            else if (List[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void setUpData() throws Exception{
        Scanner sc = new Scanner(new File("22.data.txt"));
        N = sc.nextInt(); M = sc.nextInt();
        for(int i = 0; i < N; i++) {
            int element = sc.nextInt();
            List[i] = element;
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("=====================================================");
        System.out.println("Sorted List의 Index 범위로 요소를 찾는 Binary Search");
        System.out.println("=====================================================");
        setUpData();
        Arrays.sort(List, 0 , N);
        System.out.print("List : [ "); for(int i = 0 ; i < N; i++) System.out.print(List[i] + " "); System.out.println("]");

        int ret = binarySearch(M, 0, N);
        if (ret == -1)
            System.out.println("There is no element(" + M + ")");
        else
            System.out.println("We found it at the index of " + ret + "(" + List[ret] + ")" );

        System.out.println("=====================================================");
        System.out.println("Parameter의 범위로 *최적값*을 찾는 Parametric Search");
        System.out.println("=====================================================");
        setUpData();
        int maxNum = parametricSearch(M, 0, 19);
        System.out.println("Target Length : " + M);
        System.out.println("절단기의 Optimized Max Length : " + maxNum);
    }
}