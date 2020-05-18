/**
 * 
 */
package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author satishnu
 *
 */
public class CriticalRouters {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Integer[][] links1 = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}};
    List<List<Integer>> routers = new ArrayList<>(links1.length);
    for (Integer[] record : links1) {
      routers.add(Arrays.asList(record));
    }  
    System.out.println(findCriticalRouters(routers, 7));
    //System.out.println(getCriticalRouters(7, 7, links1));

  }

  private static List<Integer> findCriticalRouters(List<List<Integer>> routers, int numNodes) {
    
    List<List<Integer>> duplicateRoutersList = routers
        .stream()
        .map(i -> {
          List<Integer> list = new ArrayList<Integer>();
          list.add(i.get(1));
          list.add(i.get(0));
          return list;
        }).collect(Collectors.toList());
    System.out.println(duplicateRoutersList);
    routers.addAll(duplicateRoutersList);
    System.out.println("finls list :"+  routers);
    Map<Integer, Set<Integer>> graph = routers.stream()
        .collect(Collectors.groupingBy(i -> i.get(0), 
            Collectors.mapping(j -> j.get(1), 
                Collectors.toSet())));
    System.out.println("graph ->"+ graph);
    List<Integer> result = new ArrayList<>();

    //calculate critical routers
    for(int nodeToRemove=0;nodeToRemove<numNodes;nodeToRemove++) {

      //remove each node and its edges and check if entire graph is connected
      Set<Integer> nodeEdges = graph.get(nodeToRemove);
      int source = 0;
      for(int edge: nodeEdges) {
        graph.get(edge).remove(nodeToRemove);
        source = edge;
      }

      HashSet<Integer> visited = new HashSet<>();
      dfs(graph, source, visited);

      if(visited.size()!=numNodes-1) {
        //this node was a critical router
        result.add(nodeToRemove);
      }

      //add the edges back
      for(int edge: nodeEdges) graph.get(edge).add(nodeToRemove);
    }
    return result;
  }

  public static void dfs (Map<Integer, Set<Integer>> graph, int source, Set<Integer> visited) {
    if(visited.contains(source)) return;

    visited.add(source);

    for(int child: graph.get(source)) dfs(graph, child, visited);
  }
}
