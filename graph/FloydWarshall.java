import java.util.*;
import java.io.*;

class FloydWarshall {
    static final long INF = 1000000000000000000L;
    long[][] dist;
    boolean directed;
    FloydWarshall(int n) {
        this(n, false);
    }
    FloydWarshall(int n, boolean directed) {
        this.directed = directed;
        dist = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
    }
    
    void addEdge(int u, int v, int w) {
        dist[u][v] = w;
        if(!directed)
            dist[v][u] = w; 
    }
    
    void computeShortestPaths() {
        for (int k = 1; k < dist.length; k++) {
            for (int i = 1; i < dist.length; i++) {
                for (int j = 1; j < dist.length; j++) {
                if (dist[i][k] != INF && dist[k][j] != INF)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}

class Main {
  public static void main(String[] args) throws Exception {
    /*
    1
    4 3 
    1 2 5
    1 3 9
    2 3 3
    */

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out, false);
    int t = Integer.parseInt(br.readLine());
    while (t-- != 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        FloydWarshall fw = new FloydWarshall(N);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            fw.addEdge(u, v, w);
        }
        fw.computeShortestPaths();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (fw.dist[i][j] == FloydWarshall.INF)
                    pw.print("-1 ");
                else
                    pw.print(fw.dist[i][j] + " ");
            }
            pw.println();
        }
    }
    br.close();
    pw.close();
  }
}
