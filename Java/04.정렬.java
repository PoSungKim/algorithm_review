import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class Solution {
    int [] primArr = new int [] {3,5,2,4,1,7};
    Integer [] objArr = Arrays.stream(primArr) 
                              .boxed()              
                              .toArray(Integer[]::new);
    List<Integer> list = new ArrayList<>(Arrays.asList(objArr)); 
    
    public int solution( ) {
        
        // 1.1 Ascending order(Primitve-Type Arrays)
        Arrays.sort(primArr);
        System.out.println(Arrays.toString(primArr));

        // 1.2 Ascending order(Object-Type Arrays)
        Arrays.sort(objArr);
        System.out.println(Arrays.toString(objArr));
        
        // 1.3 Ascending order(Collections)
        Collections.sort(list);
        System.out.println(list);
        
        // 2.1 No Way for Primitve-Type Arrays

        // 2.21 Descending order(Object-Type Arrays)
        Arrays.sort(objArr, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                // 왼쪽 o1이 먼저 앞으로 오려면 return -1;
                // 오른쪽 o2가 먼저 앞으로 오려면 return +;
                // 즉, 왼쪽은 -1 오른쪽은 +1 같으면 0
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(objArr));
        
        // 2.22 Descending order(Object-Type Arrays)
        class myOwnCompare implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }
        
        Arrays.sort(objArr, new myOwnCompare()    );
        System.out.println(Arrays.toString(objArr));
        
        // 2.23 Descending order(Object-Type Arrays)
        Arrays.sort(objArr, (a1,a2) -> a2 - a1);
        System.out.println(Arrays.toString(objArr));
        
        // 2.31 Descending order(Collections)
        // Collections.reverseOrder()는 Comparator 객체 
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
        
        // 2.32 Descending order(Collections)
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);
        
        // 2.33 Descending order(Collections)
        Collections.sort(list, new myOwnCompare());
        System.out.println(list);
        
        return 0;
    }
}
