/**
 * 
 */
package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author satishnu
 *
 */
public class ProductSuggessions {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String products[] = {"mobile","mouse","moneypot","monitor","mousepad"};
    String searchWord = "mouse";
    System.out.println(searchWord.substring(0,0));
    int k =3;
    System.out.println("suggessions :"+ getSuggessions(products, searchWord, k));
    
    
  }
  private static List<List<String>> getSuggessions(String []products, String searchWord, int k) {
    List<List<String>> suggessonsList = new ArrayList<List<String>>();
    Arrays.sort(products);
    for (int i=1;i<=searchWord.length();i++) {
      AtomicInteger atom = new AtomicInteger(i);
      List list = Arrays.asList(products)
      .stream()
      .filter(j -> j.substring(0,atom.get()).equals(searchWord.substring(0,atom.get())))
      .collect(Collectors.toList());
      
      suggessonsList.add(list.size()>3?list.subList(0, 3):list);
    }
   
    return suggessonsList;
  }

}
