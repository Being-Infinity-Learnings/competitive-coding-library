import java.io.*;
import java.util.*;

class Tree{
    List<List<Integer>> adjList;
    List<List<Integer>> edgeList;
    Tree(int n) {
        adjList = new ArrayList<>();
        edgeList = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
        edgeList.add(Arrays.asList(u, v));
    }
}
class DPOnTrees{
    int levels[];
    Tree tree;
    DPOnTrees(int root, Tree tree) {
        this.tree = tree;
        levels = new int[tree.adjList.size()+ 1];
        // pre-Compute
        levels[root] = 1;
        preComputeLevels(root, -1);
    }

    void preComputeLevels(int u, int par) {
        for(int v : tree.adjList.get(u)) {
            if(v != par) {
                levels[v] = levels[u] + 1;
                preComputeLevels(v, u);
            }
        }
    }

    int levelQuery(int u) {
        return levels[u];
    }
}

class Main {
  public static void main(String[] args) throws Exception {
    /*
        1
        5
        1 2
        2 3
        3 4
        4 5
        10
        1
        2
        3
        4
        5
        5
        4
        3
        2
        1
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, false);
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            Tree tree = new Tree(N);
            for(int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                tree.addEdge(u, v);
            }
            DPOnTrees dp = new DPOnTrees(1, tree);
            int Q = Integer.parseInt(br.readLine());
            for(int i = 0; i < Q; i++) {
                int u = Integer.parseInt(br.readLine());
                pw.print(dp.levelQuery(u) + " ");
            }
            pw.println();
        }
        pw.close();
        br.close();
    }
}
