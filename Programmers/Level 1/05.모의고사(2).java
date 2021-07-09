import java.util.*;

class Solution {

    ArrayList<ArrayList<Integer>> pSets = new ArrayList<ArrayList<Integer>>();

    public int[] solution(int[] answers) {

        pSets.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5})));
        pSets.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{2,1,2,3,2,4,2,5})));
        pSets.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{3,3,1,1,2,2,4,4,5,5})));

        Integer one = 0, two = 0, three = 0;

        for(int i = 0; i < answers.length; i++) {
            Integer curVal = answers[i];

            if (curVal == pSets.get(0).get(i % pSets.get(0).size()))
                one++;
            if (curVal == pSets.get(1).get(i % pSets.get(1).size()))
                two++;
            if (curVal == pSets.get(2).get(i % pSets.get(2).size()))
                three++;
        }
        Integer maxNum = Math.max(Math.max(one, two), three);

        ArrayList<Integer> answer = new ArrayList<>();
        for(Integer i = 0; i < 3; i++) 
            if (maxNum == (new int[] {one, two, three})[i])
                answer.add(i + 1);

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
