public class BruteForce {
    public static int N = 4;
    public static int[] Nums = { 1, 2, 3, 4 };

    public static int perm(int cnt, int val, int flag) {
        if (cnt == 2)
            return val;
        int ret = 0;
        for (int i = 0; i < N; i++) {
            if ((flag & (1 << i)) != 0)
                continue;
            ret = Math.max(ret, perm(cnt + 1, val * 10 + Nums[i], flag | (1 << i))); // 하위 노드들의 결과 값을 현재 노드에 가져오기
        }
        return ret; // 현재 노드의 결과 값을 상위 노드에 보내기
    }

    public static int comb(int cnt, int val, int index) {
        if (cnt == 2)
            return val;
        if (index >= Nums.length)
            return -1; // Max를 구하기 때문에 무시될 수 있는 값을 리턴
        int ret = comb(cnt + 1, val + Nums[index], index + 1); // 머리 속에서 Tree가 만들어져야 한다
        ret = Math.max(ret, comb(cnt, val, index + 1));
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("=========================");
        System.out.println("가장 큰 두 자리 수 구하기");
        System.out.println("=========================");
        System.out.println(perm(0, 0, 0)); // 순열 : 순서 중요 O
        System.out.println("=========================");
        System.out.println("가장 큰 두 수의 합 구하기");
        System.out.println("=========================");
        System.out.println(comb(0, 0, 0)); // 조합 : 순서 중요 X
    }
}