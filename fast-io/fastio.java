import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        // FAST IO TEMPLATE
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, false);
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) 
                a[i] = Integer.parseInt(st.nextToken());

            // SOLUTION LOGIC HERE
        
            for(int i = 0; i < a.length; i++){
                if(i == 0)
                    pw.print(a[i]);
                else
                    pw.print(" " + a[i]);
            }
            pw.println();
        }
        br.close();
        pw.close();
    }
}
