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
class BinaryJumping {
    int binAncestor[][];
    int levels[];
    Tree tree;
    BinaryJumping(int root, Tree tree){
        this.tree = tree;
        binAncestor = new int[tree.adjList.size()][20];
        levels = new int[tree.adjList.size()];
        binAncestor[root][0] = -1;
        levels[root] = 1;
        preFillParentsAndLevels(root, -1);
        for(int j = 1; j < 20; j++) {
            for(int i = 1; i < tree.adjList.size(); i++) {
                int p = binAncestor[i][j - 1];
                if(p == -1) {
                    binAncestor[i][j] = -1;
                } 
                else
                    binAncestor[i][j] = binAncestor[p][j - 1];
            }
        }
    }
    void preFillParentsAndLevels(int u, int p) {
        binAncestor[u][0] = p;
        for(int v : tree.adjList.get(u)) {
            if(v != p) {
                levels[v] = levels[u] + 1;
                preFillParentsAndLevels(v, u);
            }
        }
    }

    int kthAncestor(int u, int k) {
        for(int j = 0; j < 20; j++) {
            if((k & (1 << j)) != 0) {
                u = binAncestor[u][j];
                if(u == -1)
                    return -1;
            }
        }
        return u;
    }

    int lca(int u, int v) {
        if(levels[u] < levels[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = levels[u] - levels[v];
        u = kthAncestor(u, diff);
        if(u == v)
            return u;
        for(int j = 19; j >= 0; j--) {
            if(binAncestor[u][j] != binAncestor[v][j]) {
                u = binAncestor[u][j];
                v = binAncestor[v][j];
            }
        }
        return binAncestor[u][0];
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
            BinaryJumping bj = new BinaryJumping(1, tree);

            // SOLUTION LOGIC HERE
        }
        pw.close();
        br.close();
    }
}
