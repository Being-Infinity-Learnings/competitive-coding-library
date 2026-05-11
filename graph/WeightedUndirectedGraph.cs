using System;
using System.Collections.Generic;

class Pair : IComparable<Pair>
{
    public int First, Second;

    public Pair(int first, int second)
    {
        First = first;
        Second = second;
    }

    public int CompareTo(Pair other)
    {
        if (First != other.First) return First - other.First;
        return Second - other.Second;
    }

    public override string ToString() => First + " " + Second;
}

class Triplet : IComparable<Triplet>
{
    public int First, Second, Third;

    public Triplet(int first, int second, int third)
    {
        First = first;
        Second = second;
        Third = third;
    }

    public int CompareTo(Triplet other)
    {
        if (First != other.First) return First - other.First;
        if (Second != other.Second) return Second - other.Second;
        return Third - other.Third;
    }

    public override string ToString() => First + " " + Second + " " + Third;
}

class WeightedUndirectedGraph
{
    public List<List<Pair>> AdjList;
    public List<Triplet> EdgeList;

    public WeightedUndirectedGraph(int n)
    {
        AdjList = new List<List<Pair>>();
        EdgeList = new List<Triplet>();
        for (int i = 0; i <= n; i++)
            AdjList.Add(new List<Pair>());
    }

    public void AddEdge(int u, int v, int w)
    {
        EdgeList.Add(new Triplet(u, v, w));
        AdjList[u].Add(new Pair(v, w));
        AdjList[v].Add(new Pair(u, w));
    }

    public void PrintEdgeList()
    {
        Console.WriteLine("Edge List:");
        Console.WriteLine(string.Join("\n", EdgeList));
    }

    public void PrintAdjList()
    {
        Console.WriteLine("Adjacency List:");
        for (int i = 0; i < AdjList.Count; i++)
        {
            if (AdjList[i].Count == 0)
                Console.WriteLine(i + ": No neighbors");
            else
            {
                string neighbors = string.Join(" ", AdjList[i].ConvertAll(p => "(" + p + ")"));
                Console.WriteLine(i + ": " + neighbors);
            }
        }
    }
}

class Program
{
    static void Main()
    {
        string[] input = Console.In.ReadToEnd().Split(
            new char[] { ' ', '\n', '\r', '\t' },
            StringSplitOptions.RemoveEmptyEntries
        );

        int idx = 0;
        Func<int> ReadInt = () => int.Parse(input[idx++]);  // ← Fix: Func<int> instead of local function

        int t = ReadInt();

        while (t-- > 0)
        {
            int V = ReadInt();
            int E = ReadInt();

            WeightedUndirectedGraph g = new WeightedUndirectedGraph(V);

            for (int i = 0; i < E; i++)
            {
                int u = ReadInt();
                int v = ReadInt();
                int w = ReadInt();
                g.AddEdge(u, v, w);
            }

            // SOLUTION LOGIC HERE

            g.PrintEdgeList();
            g.PrintAdjList();
        }
    }
}
