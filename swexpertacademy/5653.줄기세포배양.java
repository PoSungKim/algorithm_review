import java.lang.reflect.*;
import java.util.*;
import java.io.*;

class Solution
{
    public static class Point {
        public int y, x;
        public Point(int y, int x) {
        	this.y = y;
            this.x = x;
        }
        
        @Override
        public boolean equals(Object obj) {
            Point otherPoint = (Point) obj;
            return otherPoint.y == this.y && otherPoint.x == this.x;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.y, this.x);
        }
    }
    
    public static enum Status {
        INACTIVE, ACTIVE, DEAD;
    }
    
    public static class Cell implements Comparable<Cell> {
        public int initLife, curLife;
        public Status status;
        public Point point;
        
        public Cell(int initLife, Point point) {
            this.initLife = initLife;
            this.curLife = initLife;
            this.status = Status.INACTIVE;
            this.point = point;
        }
        
        @Override
        public int compareTo(Cell otherCell) {
            return otherCell.initLife - this.initLife;
        }
    }
    
    public static int N,M,K;
    public static PriorityQueue<Cell> PQ = new PriorityQueue<>();
    public static Set<Point> Board = new HashSet<>();
    public static int[][] Dirs = new int [][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    
    public static void initSetting(BufferedReader br) throws Exception{
        String NMK = br.readLine();
        StringTokenizer st = new StringTokenizer(NMK);
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
        PQ.clear(); Board.clear();
        
        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
        	for(int x = 0; x < M; x++) {
            	int V = Integer.parseInt(st.nextToken());
                if (V > 0) {
                    Cell cell = new Cell(V, new Point(y, x));
                	PQ.offer(cell);
                    Board.add(cell.point);
                }
            }
        }
    }
    
    public static void startGrowth() {
        for(int i = 0; i < K; i++) {
            Queue<Cell> newCellsQ = new LinkedList<>(),
            					  remainingCellsQ = new LinkedList<>();
            while(!PQ.isEmpty()) {
                Cell curCell = PQ.poll();
                if (curCell.status == Status.INACTIVE) {
                	curCell.curLife--;
                    if (curCell.curLife == 0)  {
                        curCell.status = Status.ACTIVE;
                        curCell.curLife = curCell.initLife;
                    }
                }
                
                else if (curCell.status == Status.ACTIVE) {
                    if (curCell.curLife == curCell.initLife) {
                        for(int[] Dir : Dirs) {
                            int nY = curCell.point.y + Dir[0];
                            int nX = curCell.point.x + Dir[1];
                            Point nPoint = new Point(nY, nX);

                            if (Board.contains(nPoint)) continue;
                            Board.add(nPoint);

                            Cell nCell = new Cell(curCell.initLife, nPoint);
                            newCellsQ.offer(nCell);
                        }
                    }
                	curCell.curLife--;
                    if (curCell.curLife == 0) 
                        curCell.status = Status.DEAD;
                }
                
                if (curCell.status != Status.DEAD) 
                    remainingCellsQ.offer(curCell);
            }
            PQ.addAll(newCellsQ);
            PQ.addAll(remainingCellsQ);
        }
    }
	
    public static void main(String args[]) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int T = Integer.parseInt(br.readLine());
	for(int test_case = 1; test_case <= T; test_case++) {
            initSetting(br);
            startGrowth();
            System.out.printf("#%s %s\n", test_case, PQ.size());
	}
    }
}
