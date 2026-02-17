import java.util.*;
import java.io.*;
class UndirectedGraph{
    List< List<Integer> > adjList;
    int n;
    public UndirectedGraph(int n){
        this.n = n;
        adjList = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    void printGraph(){
        for(int i = 1; i <= n; i++){
            System.out.print(i + " : ");
            for(Integer v: adjList.get(i)){
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
