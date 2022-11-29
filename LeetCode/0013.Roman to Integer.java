class Solution {

    public int getNum(String str) {
        return 
            switch(str) {
                case  "I" -> 1;
                case "IV" -> 4;
                case  "V" -> 5;
                case "IX" -> 9;
                case  "X" -> 10;
                case "XL" -> 40;
                case  "L" -> 50;
                case "XC" -> 90;
                case  "C" -> 100;
                case "CD" -> 400;
                case  "D" -> 500;
                case "CM" -> 900;
                case  "M" -> 1000;
                default   -> 0;
        };
    }

    public boolean hasNext(int idx, int length) {
        return idx < length;
    }

    public int count(int sum, int idx, String str) {

        if ( !hasNext(idx, str.length()) ) return sum;

        String one = str.substring(idx, idx + 1);

        if ( hasNext(idx + 1, str.length()) ) {
            String two = str.substring(idx, idx + 2);
            int   twoN = getNum(two);
            if ( twoN != 0 )
                return count(sum + twoN, idx + 2, str);
            else
                return count(sum + getNum(one), idx + 1, str);
        } else 
            return count(sum + getNum(one), idx + 1, str);
    }

    public int romanToInt(String s) {
        return count(0, 0, s);
    }
}
