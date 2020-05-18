/**
 * 
 */
package practice;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author satishnu
 *
 */
public class KFrequentKeyWords {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int k = 2;
    String[] keywords = {"anacell", "cetracular", "betacellular"};
    String[] reviews = {
        "Anacell provides the best services in the city",
        "betacellular has awesome services",
    "Best services provided by anacell, everyone should use anacell"};
    //System.out.println("ffgfgfgfg:"+ Arrays.asList(keywords).contains("anacell"));
    System.out.println(getKeywords(reviews, keywords, k));

  }

  private static List<String> getKeywords(String[] reviews, String[] keyWords, int k) {

    Map<String, Long> keyWordsMap = Arrays.asList(reviews).stream()
        .map(i -> i.split(" "))
        .flatMap(i -> Arrays.asList(i).stream())
        .map(i -> i.replaceAll(",", ""))
        .peek(i -> System.out.println("peek :"+ i))
        .filter(i -> {boolean bool = Arrays.asList(keyWords).contains(i);
        System.out.println("boolean value :"+ i+" boolean :"+ bool);
        return bool;
        })
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println("map  ---->"+ keyWordsMap);
    return keyWordsMap.entrySet()
        .stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
        .entrySet()
        .stream()
        .map(i -> i.getKey())
        .collect(Collectors.toList()).subList(0, k);
  }

}
