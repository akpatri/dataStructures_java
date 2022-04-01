package algo;

import java.util.Stack;

public class adjMatrix {
  int ver;
  int[][] edge;

  public adjMatrix(int ver) {
    this.ver = ver;
    edge = new int[ver + 1][ver + 1];
  }

  public void addDirectEdge(int src, int dest, int cost) {
    edge[src][dest] = cost;
  }

  public void addUnDirectEdge(int src, int dest, int cost) {
    edge[src][dest] = cost;
    edge[dest][src] = cost;
  }

  public void showGraph() {
    System.out.print("*" + " ");
    for (int i = 0; i <= 4; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (int src = 0; src <= ver; src++) {
      System.out.print(src + " ");
      for (int dest = 0; dest <= ver; dest++) {
        System.out.print(edge[src][dest] + " ");
      }
      System.out.println();
    }
  }

  public void dfs(int src, boolean[] visit) {}

  public void bfs(int src, boolean[] visit) {}

  public void findPath(int src, int dest, boolean[] visit, Stack<Integer> path) {}

  public int numPath(int src, int dest, boolean[] visit) {}

  public static void main(String args[]) {
    adjMatrix gph = new adjMatrix(4);
    gph.addDirectEdge(0, 1, 1);
    gph.addDirectEdge(0, 2, 1);
    gph.addDirectEdge(1, 3, 1);
    gph.addDirectEdge(2, 4, 1);
    gph.addUnDirectEdge(3, 2, 1);
    gph.addDirectEdge(3, 4, 1);
    gph.showGraph();
  }
}
