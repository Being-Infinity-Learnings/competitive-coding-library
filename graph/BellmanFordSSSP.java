import java.util.*;
import java.io.*;

class Pair implements Comparable {
  long first, second;

  public Pair(long f, long s) {
    first = f;
    second = s;
  }

  public int compareTo(Object o) {
    if (first != ((Pair)o).first)
      return (int)(first - ((Pair)o).first);
    else
      return (int)(second - ((Pair)o).second);
  }
  public String toString() { return first + " " + second; }
}

class Triplet implements Comparable {
  long first, second, third;

  public Triplet(long f, long s, long t) {
    first = f;
    second = s;
    third = t;
  }

  public int compareTo(Object o) {
    if (first != ((Triplet)o).first)
      return (int)(first - ((Triplet)o).first);
    else if (second != ((Triplet)o).second)
      return (int)(second - ((Triplet)o).second);
    else
      return (int)(this.third - ((Triplet)o).third);
  }

  public String toString() { return first + " " + second + " " + third; }
}

class WeightedGraph{
  List< List< Pair > > adjList;
  List< Triplet > edgeList;
  boolean directed;
  WeightedGraph(int n){
    this(n, false);
  }
  WeightedGraph(int n, boolean directed){
    this.directed = directed;
    adjList = new ArrayList< List< Pair > >();
    edgeList = new ArrayList< Triplet >();
    for (int i = 0; i <= n; i++) {
        adjList.add(new ArrayList < Pair >()); // add neighbor list to Adjacency List
    }
  }

  void addEdge(int u, int v, int w){
    edgeList.add(new Triplet(u, v, w)); // add edge to edge list
    adjList.get(u).add(new Pair (v, w)); 
    if(!directed)
         adjList.get(v).add(new Pair (u, w));
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

class BellmanFord {
  static final long INF = 1000000000000000000L;
  WeightedGraph g;
    long[] dist;

  BellmanFord(int n, WeightedGraph g) {
    this.g = g;
  }

  long[] getDistances(int source) {
    dist = new long[g.adjList.size()];

    Arrays.fill(dist, INF);
    dist[source] = 0;    
    for (int i = 1; i < g.adjList.size(); i++) { // Relax edges repeatedly |V| - 1 times
      for (Triplet edge : g.edgeList) {
        int u = (int)edge.first;
        int v = (int)edge.second;
        int w = (int)edge.third;
        if (dist[u] != INF && dist[u] + w < dist[v]) {
          dist[v] = dist[u] + w;
        }
      }
    }
    return dist;
  }

  boolean hasNegativeCycle() {
    for (Triplet edge : g.edgeList) {
      int u = (int)edge.first;
      int v = (int)edge.second;
      int w = (int)edge.third;
      if (dist[u] != INF && dist[u] + w < dist[v]) {
        return true; // Negative cycle detected
      }
    }
    return false; // No negative cycle
  }

}

class Main {
  public static void main(String[] args) throws Exception {
    /*
    1
    5 6
    1 2 3
    2 3 -2
    1 3 2
    3 4 1
    1 5 -1
    2 3 3
    */

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter pw = new PrintWriter(System.out, false);
      int t = 1; // Integer.parseInt(br.readLine());
      while (t-- != 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        WeightedGraph g = new WeightedGraph(V, true);

        for (int i = 0; i < E; i++) {
          st = new StringTokenizer(br.readLine());
          int u = Integer.parseInt(st.nextToken());
          int v = Integer.parseInt(st.nextToken());
          int w = Integer.parseInt(st.nextToken());
          g.addEdge(u, v, w);
        }

        // SOLUTION LOGIC HERE

        BellmanFord b = new BellmanFord(V, g);
        long[] dist = b.getDistances(1); // Get distances from node 0
        for(int i = 1; i <= V; i++){
            pw.print((dist[i] == BellmanFord.INF) ? -1 : dist[i] + " ");
        }
        pw.println();
    }

    br.close();
    pw.close();
  }
}
