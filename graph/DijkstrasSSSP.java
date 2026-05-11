import java.util.*;
import java.io.*;

class Pair implements Comparable {
  long first, second;

  public Pair(long f, long s) {
    first = f;
    second = s;
  }

  public int compareTo(Object o) {
    if (first != ((Pair )o).first)
      return (int)(first - ((Pair )o).first);
    else
      return (int)(second - ((Pair )o).second);
  }
  public String toString() { return first + " " + second; }
}

class WeightedGraph{
  List< List< Pair > > adjList;
  boolean directed;
  WeightedGraph(int n, boolean directed){
    adjList = new ArrayList< List< Pair > >();
    for (int i = 0; i <= n; i++) {
      adjList.add(new ArrayList < Pair >());
    }
    this.directed = directed;
  }
  void addEdge(int u, int v, int w){
    adjList.get(u).add(new Pair (v, w)); 
    if(!directed)
        adjList.get(v).add(new Pair (u, w)); 
  }
}
class WeightedUndirectedGraph extends WeightedGraph{
  WeightedUndirectedGraph(int n){
    super(n, false);
  }
}
class WeightedDirectedGraph extends WeightedGraph{
  WeightedDirectedGraph(int n){
    super(n, true);
  }
}

class Dijkstra {
  static final long INF = 1000000000000000000L;
  WeightedGraph g;
  // SOLUTION LOGIC HERE
  Dijkstra(int n, WeightedGraph g) {
    this.g = g;
  }

  long[] getDistances(int source) {
    long[] dist = new long[g.adjList.size()];

    Arrays.fill(dist, INF);
    dist[source] = 0;    
    PriorityQueue< Pair > pq = new PriorityQueue< Pair >((x, y) -> x.compareTo(y));
    pq.add(new Pair(0, source)); // (distance, node)
    while(!pq.isEmpty()) {
      Pair current = pq.poll();
      long u = current.second;
      long d = current.first;
      if (d > dist[(int)u]) continue; // Skip if we already have a shorter path
      for (Pair neighbor : g.adjList.get((int)u)) {
        long v = neighbor.first;
        long w = neighbor.second;
        if (dist[(int)u] + w < dist[(int)v]) {
          dist[(int)v] = dist[(int)u] + w;
          pq.add(new Pair((int)dist[(int)v], v));
        }
      }
    }
    return dist;
  }
}

class Main {
  public static void main(String[] args) throws Exception {
    /*
      5 7
      1
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
      int t = 1; // Integer.parseInt(br.readLine());
      while (t-- != 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        int source = Integer.parseInt(br.readLine());

        WeightedUndirectedGraph g = new WeightedUndirectedGraph(V);

        for (int i = 0; i < E; i++) {
          st = new StringTokenizer(br.readLine());
          int u = Integer.parseInt(st.nextToken());
          int v = Integer.parseInt(st.nextToken());
          int w = Integer.parseInt(st.nextToken());
          g.addEdge(u, v, w);
        }

        // SOLUTION LOGIC HERE
        
        Dijkstra dijkstra = new Dijkstra(V, g);
        long[] distances = dijkstra.getDistances(source); // Get distances from node 0
        for(int i = 1; i <= V; i++)
            pw.print((distances[i] == Dijkstra.INF) ? -1 : distances[i] + " ");
        pw.println();
    }

    br.close();
    pw.close();
  }
}
