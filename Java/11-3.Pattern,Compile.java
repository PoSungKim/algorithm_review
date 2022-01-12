// https://codechacha.com/ko/java-regex/
import java.util.Pattern;
import java.util.Matcher;

class Solution {
    // Pattern 
  
    public int Pattern() {
        String regex = "\\bHello\\b";
        Pattern pattern = Pattern.compile(regex);
      
        String target = "Hello Hello Hello Hello World";
        Matcher matcher = pattern.matcher(target);
      
        int cnt = 0;
        while(matcher.find()) {
            cnt++;
            System.out.printf("Count     : %s \n", cnt);
            System.out.printf("Group     : %s \n", matcher.group());
            System.out.printf("Start Idx : %s \n", matcher.start());
            System.out.printf("End Idx   : %s \n", matcher.end());
        }  
    }
}
