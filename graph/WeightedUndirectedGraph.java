import java.util.*;
import java.io.*;

class Pair implements Comparable {
  Integer first, second;

  public Pair(Integer f, Integer s) {
    first = f;
    second = s;
  }

  public int compareTo(Object o) {
    if (first != ((Pair )o).first)
      return first - ((Pair )o).first;
    else
      return second - ((Pair )o).second;
  }
  public String toString() { return first + " " + second; }
}

class Triplet implements Comparable {
  Integer first, second, third;

  public Triplet(Integer f, Integer s, Integer t) {
    first = f;
    second = s;
    third = t;
  }

  public int compareTo(Object o) {
    if (!this.first.equals(((Triplet)o).first))
      return this.first - ((Triplet)o).first;
    else if (!this.second.equals(((Triplet)o).second))
      return this.second - ((Triplet)o).second;
    else
      return this.third - ((Triplet)o).third;
  }

  public String toString() { return first + " " + second + " " + third; }
}

class WeightedUndirectedGraph{
  List< List< Pair > > adjList;
  List< Triplet > edgeList;
  WeightedUndirectedGraph(int n){
    adjList = new ArrayList< List< Pair > >();
    edgeList = new ArrayList< Triplet >();
    for (int i = 0; i <= n; i++) {
      adjList.add(new ArrayList < Pair >()); // add neighbor list to Adjacency List
    }
  }

  void addEdge(int u, int v, int w){
    edgeList.add(new Triplet(u, v, w)); // add edge to edge list
    adjList.get(u).add(new Pair (v, w)); 
    adjList.get(v).add(new Pair (u, w)); 
  }

  void printEdgeList(){
    System.out.println("Edge List:");
    for(int i = 0; i < edgeList.size(); i++){
      if(i == 0)
          System.out.print(edgeList.get(i));
      else
          System.out.print("\n" + edgeList.get(i));
    }
    System.out.println();
  }

  void printAdjList(){
    System.out.println("Adjacency List:");
    for(int i = 0; i < adjList.size(); i++){
      System.out.print(i + ": ");
       if(adjList.get(i).size() == 0)
          System.out.print("No neighbors");
      for(int j = 0; j < adjList.get(i).size(); j++){
          if(j == 0)                  
            System.out.print("(" + adjList.get(i).get(j) + ")");
          else                      
            System.out.print(" (" + adjList.get(i).get(j) + ")");
      }
      System.out.println();
    } 
  }
}

class Main {
  private static List< List< Pair > > adjList = new ArrayList< List< Pair > >();
  private static List< Triplet > edgeList = new ArrayList< Triplet >();
  public static void main(String[] args) throws Exception {
    /*
      1
      5 7
      2 1 2
      2 3 7
      2 0 6
      1 3 3
      1 4 6
      3 4 5
      0 4 1
    */

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter pw = new PrintWriter(System.out, false);
      int t = Integer.parseInt(br.readLine());
      while (t-- != 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        WeightedUndirectedGraph g = new WeightedUndirectedGraph(V);

        for (int i = 0; i < E; i++) {
          st = new StringTokenizer(br.readLine());
          int u = Integer.parseInt(st.nextToken());
          int v = Integer.parseInt(st.nextToken());
          int w = Integer.parseInt(st.nextToken());
          g.addEdge(u, v, w);
        }

        // SOLUTION LOGIC HERE


        g.printEdgeList();
        g.printAdjList();
    }

    br.close();
    pw.close();
  }
}
