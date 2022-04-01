package algo;

import java.util.LinkedList;
import java.util.Stack;

public class adjList {
  int ver;
  LinkedList<LinkedList<Edge>> gph;

  class Edge {
    int val;
    int cost;
    public Edge(int val, int cost) {
      this.val = val;
      this.cost = cost;
    }
  }
  public adjList(int ver) {
    this.ver = ver;
    gph = new LinkedList<>();
    for (int i = 0; i <= ver; i++) {
      gph.add(new LinkedList<>());
    }
  }
  public void addDirectEdge(int src, int dest, int cost) {
    gph.get(src).add(new Edge(dest, cost));
  }
  public void addUnDirectEdge(int src, int dest, int cost) {
    gph.get(src).add(new Edge(dest, cost));
    gph.get(dest).add(new Edge(src, cost));
  }
  public void showGraph() {
    for (int i = 0; i <= ver; i++) {
      System.out.print(i + " :");
      LinkedList<Edge> e1 = gph.get(i);
      for (Edge e : e1) {
        System.out.print(e.val + "->");
      }
      System.out.println();
    }
  }
  public void dfs(int src, boolean[] visit) {
    visit[src] = true;
    System.out.print(src + " ");
    LinkedList<Edge> ver = gph.get(src);
    for (Edge e1 : ver) {
      if (visit[e1.val] == false) {
        dfs(e1.val, visit);
      }
    }
  }
  public void bfs(int src, boolean[] visit, LinkedList<Integer> q) {
    q.add(src);
    visit[src] = true;
    while (q.size() != 0) {
      System.out.print(q.peek() + " ");
      LinkedList<Edge> adj = gph.get(q.remove());
      for (Edge e : adj) {
        if (visit[e.val] == false) {
          q.add(e.val);
          visit[e.val] = true;
        }
      }
    }
  }
  public int numPath(int src,int dest,boolean[] visit){
    if(src==dest)
        return 1;
    else{
      visit[src]=true;
      int count=0;
      LinkedList<Edge> e1=gph.get(src);
      for(Edge e:e1){
        if(visit[e.val]==false){
          count+=numPath(e.val,dest,visit);
        }
      }
      visit[src]=false;
      return count;
    }
  }
  public void findPath(int src,int dest,boolean[] visit,Stack<Integer> path){
    if(src==dest){
      path.add(dest);
      System.out.print(path);
      path.pop();
      return;
    }else{
      visit[src]=true;
      path.push(src);
      LinkedList<Edge> ad=gph.get(src);
      for(Edge e:ad){
        if(visit[e.val]==false){
          findPath(e.val,dest,visit,path);
        }
      }
      visit[src]=false;
      path.pop();
    }
    
  }
  /*
  public boolean isCircle(int src,boolean[] visit,Stack<Integer> cir){
    LinkedList<Edge> ad=gph.get(src);
    visit[src]=true;
    cir.add(src);
    for(Edge e:ad){
      if(visit[e.val]==true){
          System.out.print(cir);
          return true;
      }
      else
          return isCircle(e.val,visit,cir);
    }
    return false;
  }*/
  public void pathExist(int src,int dest,boolean[] visit,int[][] path){
    LinkedList<Edge> e=gph.get(dest);
    path[src][dest]=1;
    visit[dest]=true;
    for(Edge e1:e){
      if(visit[e1.val]==false){
        pathExist(src,e1.val,visit,path);
      }
    }
  }
  public void trnsClosure(boolean[] visit,int[][] pa){
    for(int i=0;i<ver;i++){
      pathExist(i,i,visit,pa);
    }
    System.out.print("*");
    for(int i=0;i<=ver;i++){
      System.out.print(" "+i);
    }
    System.out.println();
    for(int i=0;i<=4;i++){
      System.out.print(i+" ");
      for(int j=0;j<=4;j++){
        System.out.print(pa[i][j]+" ");
      }
      System.out.println();
    }
  }
  public static void main(String[] args) {
    adjList grp = new adjList(4);
    grp.addDirectEdge(0, 1, 2);
    grp.addDirectEdge(0, 2, 3);
    grp.addDirectEdge(1, 3, 4);
    grp.addUnDirectEdge(2, 3, 1);
    grp.addDirectEdge(3, 4, 5);
    grp.addDirectEdge(2, 4, 2);
    grp.showGraph();
    System.out.print("\nDFS :");
    grp.dfs(0, new boolean[5]);
    System.out.print("\n\nBFS :");
    grp.bfs(0, new boolean[5], new LinkedList<>());
    System.out.print("\n\nFindPath :\n");
    grp.findPath(0,4,new boolean[5],new Stack<>());
    System.out.print("\n\nnumPath :\n");
    System.out.print(grp.numPath(0,4,new boolean[5]));
    System.out.print("\n\nisCirclePresent :\n");
    //System.out.print(grp.isCircle(0,new boolean[5],new Stack<>()));
    System.out.println("\n\nPathExist\n");
    grp.trnsClosure(new boolean[5],new int[5][5]);
  }
}
