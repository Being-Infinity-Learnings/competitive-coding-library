import * as readline from "readline";

class Pair {
  constructor(public first: number, public second: number) {}

  compareTo(other: Pair): number {
    if (this.first !== other.first) return this.first - other.first;
    return this.second - other.second;
  }

  toString(): string {
    return `${this.first} ${this.second}`;
  }
}

class Triplet {
  constructor(
    public first: number,
    public second: number,
    public third: number
  ) {}

  compareTo(other: Triplet): number {
    if (this.first !== other.first) return this.first - other.first;
    if (this.second !== other.second) return this.second - other.second;
    return this.third - other.third;
  }

  toString(): string {
    return `${this.first} ${this.second} ${this.third}`;
  }
}

class WeightedUndirectedGraph {
  adjList: Pair[][];
  edgeList: Triplet[];

  constructor(n: number) {
    this.adjList = Array.from({ length: n + 1 }, () => []);
    this.edgeList = [];
  }

  addEdge(u: number, v: number, w: number): void {
    this.edgeList.push(new Triplet(u, v, w));
    this.adjList[u].push(new Pair(v, w));
    this.adjList[v].push(new Pair(u, w));
  }

  printEdgeList(): void {
    console.log("Edge List:");
    console.log(this.edgeList.map((e) => e.toString()).join("\n"));
  }

  printAdjList(): void {
    console.log("Adjacency List:");
    for (let i = 0; i < this.adjList.length; i++) {
      const neighbors = this.adjList[i];
      if (neighbors.length === 0) {
        console.log(`${i}: No neighbors`);
      } else {
        const neighborStr = neighbors.map((p) => `(${p})`).join(" ");
        console.log(`${i}: ${neighborStr}`);
      }
    }
  }
}

async function main(): Promise<void> {
  const rl = readline.createInterface({
    input: process.stdin,
    terminal: false,
  });

  const lines: string[] = [];

  for await (const line of rl) {
    lines.push(line.trim());
  }

  let idx = 0;
  const tokens: string[] = lines.flatMap((line) => line.split(/\s+/));

  const readInt = (): number => parseInt(tokens[idx++], 10);

  let t = readInt();

  while (t-- > 0) {
    const V = readInt();
    const E = readInt();

    const g = new WeightedUndirectedGraph(V);

    for (let i = 0; i < E; i++) {
      const u = readInt();
      const v = readInt();
      const w = readInt();
      g.addEdge(u, v, w);
    }

    // SOLUTION LOGIC HERE

    g.printEdgeList();
    g.printAdjList();
  }
}

main();
