/**
 * 
 */
package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import practice.Zombies.Positions;

/**
 * @author satishnu
 *
 */
public class Ilands {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Integer [][] zombieMatrix = {
        {1,1,1,1},
        {1,1,1,1},
        {1,1,1,1},
        {1,1,1,1}
    };
    List<List<Integer>> totalList = new ArrayList<>(zombieMatrix.length);

    for (Integer[] record : zombieMatrix) {
      totalList.add(Arrays.asList(record));
    } 
    System.out.println(findIlands(totalList));

  }
  private static List<Positions> findIlands(List<List<Integer>> totalList) {
    AtomicInteger atom = new AtomicInteger(0);
    List<Positions> posilitonList = convertToList(totalList, atom);
    
    if(!posilitonList.isEmpty() && atom.get() != posilitonList.size()) {
      return posilitonList
      .stream()
      .filter(i -> isIland(i, totalList))
      .collect(Collectors.toList());
      
    }
    
    return new ArrayList<>();
  }
  private static boolean isIland(Positions positions, List<List<Integer>> totalList) {
    boolean bool = false;
    int x = positions.getX();
    int y = positions.getY();
    System.out.println("x---->"+ x+" y---->"+ y);
    if ((x-1 < 0 || totalList.get(x-1).get(y) == 0)
        && (x+1 >= totalList.size() || totalList.get(x+1).get(y) == 0)
        && (y-1<=0 || totalList.get(x).get(y-1) == 0)
        && (y+1 >= totalList.get(x).size() || totalList.get(x).get(y+1) == 0)
        && (x-1 <0 || y-1 < 0 ||totalList.get(x-1).get(y-1) == 0)
        && (x+1 >= totalList.size() || y+1 >= totalList.get(x).size() ||totalList.get(x+1).get(y+1) == 0)
        && (x+1 >= totalList.size() || y-1 < 0 ||totalList.get(x+1).get(y-1) == 0)
        && (x-1 <0 || y+1 >= totalList.get(x).size() ||totalList.get(x-1).get(y+1) == 0)) {
      
      bool = true;
    }
    
    return bool;
  }
  private static List convertToList(List<List<Integer>> zombieList, AtomicInteger count) {
    Zombies zombies = new Zombies();
    return IntStream.range(0, zombieList.size())
        .boxed()
        .flatMap(i -> IntStream.range(0, zombieList.get(i).size())
            .mapToObj(j -> zombies.new Positions(i,j,zombieList.get(i).get(j))))
        .parallel()
        .peek(i -> count.getAndIncrement())
        .filter(a -> a.getValue() == 1).collect(Collectors.toList());
  }
  

}
