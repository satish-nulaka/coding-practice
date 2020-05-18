/**
 * 
 */
package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author satishnu
 *
 */
public class MaximumSubstring {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String S = "ababcbacadefegdehijhklij";
    System.out.println(partitionLabels(S));
  }
  
  private static List<Integer> partitionLabels(String S) {
    Map<Character, Integer> posMap = new HashMap<Character, Integer>(); 
    ArrayList<Integer> result = new ArrayList(); 
    
    for(int i = 0; i < S.length(); i++){
        Character c = S.charAt(i); 
        posMap.put(c, i);
    }
    
    int left = 0; int right = 0; int curr = 0; 
    for(int i = 0; i < S.length(); i++){
        Character c = S.charAt(i); 
        curr = posMap.get(c); 

        if(curr > right) right = curr; 
        
        if(i == right){
            result.add(right - left + 1); 
            left = i + 1;                 
        }
    }
    
    return result; 
}
}
