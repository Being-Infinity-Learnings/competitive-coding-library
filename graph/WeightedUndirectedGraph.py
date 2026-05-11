import sys
from sys import stdin

class Pair:
    def __init__(self, first, second):
        self.first = first
        self.second = second

    def __lt__(self, other):
        if self.first != other.first:
            return self.first < other.first
        return self.second < other.second

    def __eq__(self, other):
        return self.first == other.first and self.second == other.second

    def __repr__(self):
        return f"{self.first} {self.second}"


class Triplet:
    def __init__(self, first, second, third):
        self.first = first
        self.second = second
        self.third = third

    def __lt__(self, other):
        if self.first != other.first:
            return self.first < other.first
        if self.second != other.second:
            return self.second < other.second
        return self.third < other.third

    def __eq__(self, other):
        return (self.first == other.first and
                self.second == other.second and
                self.third == other.third)

    def __repr__(self):
        return f"{self.first} {self.second} {self.third}"


class WeightedUndirectedGraph:
    def __init__(self, n):
        self.adj_list = [[] for _ in range(n + 1)]
        self.edge_list = []

    def add_edge(self, u, v, w):
        self.edge_list.append(Triplet(u, v, w))
        self.adj_list[u].append(Pair(v, w))
        self.adj_list[v].append(Pair(u, w))

    def print_edge_list(self):
        print("Edge List:")
        print("\n".join(str(e) for e in self.edge_list))

    def print_adj_list(self):
        print("Adjacency List:")
        for i, neighbors in enumerate(self.adj_list):
            if not neighbors:
                print(f"{i}: No neighbors")
            else:
                neighbor_str = " ".join(f"({p})" for p in neighbors)
                print(f"{i}: {neighbor_str}")


def main():
    input = stdin.read().split()
    idx = 0

    t = int(input[idx]); idx += 1

    while t:
        t -= 1
        V = int(input[idx]); idx += 1
        E = int(input[idx]); idx += 1

        g = WeightedUndirectedGraph(V)

        for _ in range(E):
            u = int(input[idx]); idx += 1
            v = int(input[idx]); idx += 1
            w = int(input[idx]); idx += 1
            g.add_edge(u, v, w)

        # SOLUTION LOGIC HERE

        g.print_edge_list()
        g.print_adj_list()


if __name__ == "__main__":
    main()
