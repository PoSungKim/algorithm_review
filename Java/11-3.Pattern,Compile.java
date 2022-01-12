// https://codechacha.com/ko/java-regex/
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyClass {
    public static void main(String args[]) {
      Pattern pattern = Pattern.compile("\\bhello\\b");
      Matcher matcher = pattern.matcher("hello hello hello hello hello world!!");
      
      int cnt = 0;
      while(matcher.find()) {
          System.out.printf("count : %s\n", ++cnt);
          System.out.printf("group : %s\n", matcher.group());
          System.out.printf("start : %s\n", matcher.start());
          System.out.printf("end   : %s\n\n", matcher.end());
      }
    }
}
