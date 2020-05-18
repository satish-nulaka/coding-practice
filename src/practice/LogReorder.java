/**
 * 
 */
package practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author satishnu
 *
 */
public class LogReorder {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String []logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
    System.out.println(getSortedLogs(logs));
  }

  private static List<String> getSortedLogs(String []logs) {

    List<String> list1 = splitList(logs, true);
    List<String> list2 = splitList(logs, false);
    Collections.reverse(list2);
    list1.addAll(list2);
    return list1;
  }

  private static List<String> splitList(String []logs, boolean bool) {
    return Arrays.asList(logs)
        .stream()
        .filter(i -> {
          try {
            Integer.parseInt(i.split(" ")[1]);
          } catch (NumberFormatException e) {
            return bool;
          }
          return !bool;})
        .sorted((o1,o2) -> {
          String first = o1.replace(o1.split(" ")[0],"");
          String second = o2.replace(o2.split(" ")[0], "");
          return first.compareTo(second);
        })
        .collect(Collectors.toList());
  }

}
