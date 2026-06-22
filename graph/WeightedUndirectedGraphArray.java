import java.util.*;
import java.io.*;

class WeightedUndirectedGraph{
  List< List< int[] > > adjList;
  List< int[] > edgeList;
  WeightedUndirectedGraph(int n){
    adjList = new ArrayList< List< int[] > >();
    edgeList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList <>()); // add neighbor list to Adjacency List
    }
  }

  void addEdge(int u, int v, int w){
    edgeList.add(new int[]{u, v, w} ); // add edge to edge list
    adjList.get(u).add(new int[]{v, w}); 
    adjList.get(v).add(new int[]{u, w}); 
  }

  void printEdgeList(){
    System.out.println("Edge List:");
    for(int i = 0; i < edgeList.size(); i++){
      int[] edge = edgeList.get(i);
      System.out.printf("%d %d %d\n", edge[0], edge[1], edge[2]);
    }
  }

  void printAdjList(){
    System.out.println("Adjacency List:");
    for(int i = 0; i < adjList.size(); i++){
      System.out.print(i + ": ");
      if(adjList.get(i).size() == 0)
          System.out.print("No neighbors");
      else{
        for(int j = 0; j < adjList.get(i).size(); j++){
            int[] edge = adjList.get(i).get(j);
            System.out.printf("(%d %d) ", edge[0], edge[1]);
        }
      }
      System.out.println();
    } 
  }
}

class Main {
  public static void main(String[] args) throws Exception {
    /*
      2
      5 7
      2 1 2
      2 3 7
      2 0 6
      1 3 3
      1 4 6
      3 4 5
      0 4 1
      7 10
      0 1 4
      0 2 3
      1 2 1
      1 3 7
      2 3 10
      2 5 5
      3 4 2
      4 5 6
      4 6 8
      5 6 9
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
