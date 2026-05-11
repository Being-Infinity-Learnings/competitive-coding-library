const readline = require("readline");

class Pair {
  constructor(first, second) {
    this.first = first;
    this.second = second;
  }

  compareTo(other) {
    if (this.first !== other.first) return this.first - other.first;
    return this.second - other.second;
  }

  toString() {
    return `${this.first} ${this.second}`;
  }
}

class Triplet {
  constructor(first, second, third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  compareTo(other) {
    if (this.first !== other.first) return this.first - other.first;
    if (this.second !== other.second) return this.second - other.second;
    return this.third - other.third;
  }

  toString() {
    return `${this.first} ${this.second} ${this.third}`;
  }
}

class WeightedUndirectedGraph {
  constructor(n) {
    this.adjList = Array.from({ length: n + 1 }, () => []);
    this.edgeList = [];
  }

  addEdge(u, v, w) {
    this.edgeList.push(new Triplet(u, v, w));
    this.adjList[u].push(new Pair(v, w));
    this.adjList[v].push(new Pair(u, w));
  }

  printEdgeList() {
    console.log("Edge List:");
    console.log(this.edgeList.map((e) => e.toString()).join("\n"));
  }

  printAdjList() {
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

function main(input) {
  const tokens = input.split(/\s+/).filter(Boolean);
  let idx = 0;
  const readInt = () => parseInt(tokens[idx++], 10);

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

// Read all stdin then process
let inputData = "";
const rl = readline.createInterface({ input: process.stdin });
rl.on("line", (line) => (inputData += line + "\n"));
rl.on("close", () => main(inputData));
