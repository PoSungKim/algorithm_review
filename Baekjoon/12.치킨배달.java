import java.util.*;

public class Main {

    public static class Point {
        int y,x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    // 0: 빈 칸, 1: 집, 2: 치킨집
    public static Map<Integer, List<Point>> Map = new HashMap<>(){
        {
            put(1, new ArrayList<Point>());
            put(2, new ArrayList<Point>());
        }
    };
    public static final int INF = 987654321;
    public static int N, M;
    
    public static int get_d(Point a, Point b) {
        return Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
    }

    public static int comb() {
        int min_d = INF;
        for(int i = 1; i < (1 << Map.get(2).size()); i++) {
            if (Integer.bitCount(i) > M ) continue;
            int sum = 0;
            for(Point House : Map.get(1)) {
                int min_d_for_each_house = INF;
                for(int j = 0; j < Map.get(2).size(); j++) {
                    if ((i & 1 << j) != 0) {
                        min_d_for_each_house = Math.min(min_d_for_each_house, get_d(House, Map.get(2).get(j)));
                    }
                }
                sum += min_d_for_each_house;
            }
            min_d = Math.min(min_d, sum);
        }
        
        return min_d;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int V = sc.nextInt();
                if (V != 0) Map.get(V).add(new Point(i, j));
            }
        }
        
        System.out.println(comb());
    }
}