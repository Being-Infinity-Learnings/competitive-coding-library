#include <bits/stdc++.h>
using namespace std;

struct Pair {
    int first, second;

    Pair(int f, int s) : first(f), second(s) {}

    bool operator<(const Pair& o) const {
        if (first != o.first) return first < o.first;
        return second < o.second;
    }

    bool operator==(const Pair& o) const {
        return first == o.first && second == o.second;
    }

    friend ostream& operator<<(ostream& os, const Pair& p) {
        return os << p.first << " " << p.second;
    }
};

struct Triplet {
    int first, second, third;

    Triplet(int f, int s, int t) : first(f), second(s), third(t) {}

    bool operator<(const Triplet& o) const {
        if (first != o.first) return first < o.first;
        if (second != o.second) return second < o.second;
        return third < o.third;
    }

    bool operator==(const Triplet& o) const {
        return first == o.first && second == o.second && third == o.third;
    }

    friend ostream& operator<<(ostream& os, const Triplet& t) {
        return os << t.first << " " << t.second << " " << t.third;
    }
};

class WeightedUndirectedGraph {
public:
    vector<vector<Pair>> adjList;
    vector<Triplet> edgeList;

    WeightedUndirectedGraph(int n) {
        adjList.resize(n + 1);
    }

    void addEdge(int u, int v, int w) {
        edgeList.push_back(Triplet(u, v, w));
        adjList[u].push_back(Pair(v, w));
        adjList[v].push_back(Pair(u, w));
    }

    void printEdgeList() {
        cout << "Edge List:\n";
        for (int i = 0; i < (int)edgeList.size(); i++) {
            if (i > 0) cout << "\n";
            cout << edgeList[i];
        }
        cout << "\n";
    }

    void printAdjList() {
        cout << "Adjacency List:\n";
        for (int i = 0; i < (int)adjList.size(); i++) {
            cout << i << ": ";
            if (adjList[i].empty()) {
                cout << "No neighbors";
            } else {
                for (int j = 0; j < (int)adjList[i].size(); j++) {
                    if (j > 0) cout << " ";
                    cout << "(" << adjList[i][j] << ")";
                }
            }
            cout << "\n";
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;

    while (t--) {
        int V, E;
        cin >> V >> E;

        WeightedUndirectedGraph g(V);

        for (int i = 0; i < E; i++) {
            int u, v, w;
            cin >> u >> v >> w;
            g.addEdge(u, v, w);
        }

        // SOLUTION LOGIC HERE

        g.printEdgeList();
        g.printAdjList();
    }

    return 0;
}
