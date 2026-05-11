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

class WeightedUndirectedGraph{
  List< List< Pair > > adjList;
  WeightedUndirectedGraph(int n){
    adjList = new ArrayList< List< Pair > >();
    for (int i = 0; i <= n; i++) {
      adjList.add(new ArrayList < Pair >()); // add neighbor list to Adjacency List
    }
  }
  void addEdge(int u, int v, int w){
    adjList.get(u).add(new Pair (v, w)); 
    adjList.get(v).add(new Pair (u, w)); 
  }
}

class Dijkstra {
  static final int INF = 1000000000;
  WeightedUndirectedGraph g;
  // SOLUTION LOGIC HERE
  Dijkstra(int n, WeightedUndirectedGraph g) {
    this.g = g;
  }

  int[] getDistances(int source) {
    int[] dist = new int[g.adjList.size()];

    Arrays.fill(dist, INF);
    dist[source] = 0;    PriorityQueue< Pair > pq = new PriorityQueue< Pair >((x, y) -> x.second - y.second);
    pq.add(new Pair(0, source)); // (distance, node)
    while(!pq.isEmpty()) {
      Pair current = pq.poll();
      int u = current.second;
      int d = current.first;
      if (d > dist[u]) continue; // Skip if we already have a shorter path
      for (Pair neighbor : g.adjList.get(u)) {
        int v = neighbor.first;
        int w = neighbor.second;
        if (dist[u] + w < dist[v]) {
          dist[v] = dist[u] + w;
          pq.add(new Pair((int)dist[v], v));
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
        int[] distances = dijkstra.getDistances(source); // Get distances from node 0
        for(int i = 1; i <= V; i++)
            pw.print(distances[i]+ " ");
        pw.println();
    }

    br.close();
    pw.close();
  }
}
