import java.util.*;
import java.io.*;

class UFDS {
    int[] parent;
    int[] size;

    UFDS(int n) { // Initialise UFDS with n elements
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // Initially all the elements are parent of themselves
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Find the topmost parent of an element
    int findRoot(int v) {
        if (parent[v] == v)
            return v;
        parent[v] = findRoot(parent[v]); // Path compression
        return parent[v];
    }

    void mergeSet(int a, int b) {
        // Find the respective parents
        a = findRoot(a);
        b = findRoot(b);

        // Swap if b has greater size
        if(size[b] > size[a]) {    
            int temp = b;
            b = a;
            a = temp;
        }

        // Set the parent of b as a
        parent[b] = a;
        // Update the size of the set
        size[a] += size[b];
    }

    boolean sameSet(int a, int b) {
        // If the parents are same return true
        return findRoot(a) == findRoot(b);
    }
}

class Main {
  public static void main(String[] args) throws Exception {
    /*
    1
    5 6
    1 1 2
    2 1 2
    2 1 3
    1 3 5
    1 1 5
    2 1 3
    */

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out, false);
    int t = Integer.parseInt(br.readLine());
    while (t-- != 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        UFDS ufds = new UFDS(N);
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(type == 1) {
                ufds.mergeSet(u, v);
            } else {
                pw.println(ufds.sameSet(u, v) ? "YES" : "NO");
            }
        }
        pw.println();
    }
    br.close();
    pw.close();
  }
}
