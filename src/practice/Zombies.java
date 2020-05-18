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

/**
 * @author satishnu
 *
 */
public class Zombies {

  public class Positions {
    int x;
    int y;
    int value;
    
    public Positions(int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
    }
    
    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "Positions [x=" + x + ", y=" + y + ", value=" + value
          + "]";
    }
    
    
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
  /* Integer [][] zombieMatrix = {
        {0,1,1,0,1},
        {0,1,0,1,0},
        {0,0,0,0,1},
        {0,1,0,0,0}
    }; */
   /* Integer [][] zombieMatrix = {
        {1,1,1,1,1},
        {1,1,1,1,1},
        {1,1,1,1,1},
        {1,1,1,1,1}
    }; */
    Integer [][] zombieMatrix = {
        {1,0,1,0},
        {0,0,0,0},
        {0,0,1,0},
        {1,0,0,0}
    };
    List<List<Integer>> zombieList = new ArrayList<>(zombieMatrix.length);

    for (Integer[] record : zombieMatrix) {
      zombieList.add(Arrays.asList(record));
    } 
    System.out.println(convertToZombie(zombieList));

  }

  private static int convertToZombie(List<List<Integer>> zombieList) {
    // TODO Auto-generated method stub
    int days =0; 
    AtomicInteger ordinal = new AtomicInteger(0);
    List<Positions> listPosition = convertToList(zombieList, ordinal);
    List<Positions> removedPositions = new ArrayList<>();
    int totalHumans = listPosition.size();
    System.out.println("ordinal value :"+ ordinal.get());
    while(!listPosition.isEmpty() && listPosition.size() != ordinal.get()) {
      listPosition = listPosition.stream().filter(i -> !convertHumanToZombie(i, totalHumans,zombieList, removedPositions))
      .collect(Collectors.toList());
      System.out.println(listPosition.size());
      if(!removedPositions.isEmpty()) {
        removedPositions.forEach(positions -> zombieList.get(positions.getX()).set(positions.getY(), 1));
      }
      days = days+1;
      removedPositions.clear();
      System.out.println(zombieList);
    }
    return days;
  }
  private static boolean convertHumanToZombie(Positions positions, 
      int totalHumans, List<List<Integer>> zombieList, List<Positions> listToBeDeleted) {
    System.out.println(positions);
    boolean bool = false;
    //up
    if(positions.getX() -1 >= 0 && positions.getY() < zombieList.get(positions.getX()-1).size() 
        && zombieList.get(positions.getX()-1).get(positions.getY()) == 1 ) {
      listToBeDeleted.add(positions);
      return true;
    }
    //down
    if(positions.getX() +1 < zombieList.size()  && positions.getY() < zombieList.get(positions.getX()+1).size() 
        && zombieList.get(positions.getX()+1).get(positions.getY()) == 1 ) {
      listToBeDeleted.add(positions);
      return true;
    }
    //right
    if(positions.getX() < zombieList.size()  && positions.getY()+1 < zombieList.get(positions.getX()).size() 
        && zombieList.get(positions.getX()).get(positions.getY()+1) == 1 ) {
      listToBeDeleted.add(positions);
      return true;
    }
    //left
    if(positions.getX() < zombieList.size()  && positions.getY()-1 >= 0 
        && zombieList.get(positions.getX()).get(positions.getY()-1) == 1) {
      listToBeDeleted.add(positions);
      return true;
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
        .filter(a -> a.getValue() == 0).collect(Collectors.toList());
  }
}
