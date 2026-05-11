#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ── Pair ─────────────────────────────────────────────────────────── */
typedef struct {
    int first, second;
} Pair;

Pair newPair(int f, int s) {
    Pair p;
    p.first  = f;
    p.second = s;
    return p;
}

int pairCmp(const void* a, const void* b) {
    const Pair* pa = (const Pair*)a;
    const Pair* pb = (const Pair*)b;
    if (pa->first != pb->first) return pa->first - pb->first;
    return pa->second - pb->second;
}

void printPair(const Pair* p) {
    printf("%d %d", p->first, p->second);
}

/* ── Triplet ──────────────────────────────────────────────────────── */
typedef struct {
    int first, second, third;
} Triplet;

Triplet newTriplet(int f, int s, int t) {
    Triplet tr;
    tr.first  = f;
    tr.second = s;
    tr.third  = t;
    return tr;
}

int tripletCmp(const void* a, const void* b) {
    const Triplet* ta = (const Triplet*)a;
    const Triplet* tb = (const Triplet*)b;
    if (ta->first  != tb->first)  return ta->first  - tb->first;
    if (ta->second != tb->second) return ta->second - tb->second;
    return ta->third - tb->third;
}

void printTriplet(const Triplet* t) {
    printf("%d %d %d", t->first, t->second, t->third);
}

/* ── Dynamic array helpers ────────────────────────────────────────── */
typedef struct {
    Pair*  data;
    int    size;
    int    capacity;
} PairList;

void pairListInit(PairList* pl) {
    pl->size     = 0;
    pl->capacity = 4;
    pl->data     = (Pair*)malloc(pl->capacity * sizeof(Pair));
}

void pairListPush(PairList* pl, Pair p) {
    if (pl->size == pl->capacity) {
        pl->capacity *= 2;
        pl->data = (Pair*)realloc(pl->data, pl->capacity * sizeof(Pair));
    }
    pl->data[pl->size++] = p;
}

void pairListFree(PairList* pl) {
    free(pl->data);
    pl->data     = NULL;
    pl->size     = pl->capacity = 0;
}

typedef struct {
    Triplet* data;
    int      size;
    int      capacity;
} TripletList;

void tripletListInit(TripletList* tl) {
    tl->size     = 0;
    tl->capacity = 4;
    tl->data     = (Triplet*)malloc(tl->capacity * sizeof(Triplet));
}

void tripletListPush(TripletList* tl, Triplet t) {
    if (tl->size == tl->capacity) {
        tl->capacity *= 2;
        tl->data = (Triplet*)realloc(tl->data, tl->capacity * sizeof(Triplet));
    }
    tl->data[tl->size++] = t;
}

void tripletListFree(TripletList* tl) {
    free(tl->data);
    tl->data     = NULL;
    tl->size     = tl->capacity = 0;
}

/* ── WeightedUndirectedGraph ──────────────────────────────────────── */
typedef struct {
    PairList*   adjList;   /* adjList[0..n] */
    TripletList edgeList;
    int         n;
} WeightedUndirectedGraph;

void graphInit(WeightedUndirectedGraph* g, int n) {
    g->n       = n;
    g->adjList = (PairList*)malloc((n + 1) * sizeof(PairList));
    for (int i = 0; i <= n; i++)
        pairListInit(&g->adjList[i]);
    tripletListInit(&g->edgeList);
}

void graphAddEdge(WeightedUndirectedGraph* g, int u, int v, int w) {
    tripletListPush(&g->edgeList, newTriplet(u, v, w));
    pairListPush(&g->adjList[u], newPair(v, w));
    pairListPush(&g->adjList[v], newPair(u, w));
}

void graphPrintEdgeList(const WeightedUndirectedGraph* g) {
    printf("Edge List:\n");
    for (int i = 0; i < g->edgeList.size; i++) {
        if (i > 0) printf("\n");
        printTriplet(&g->edgeList.data[i]);
    }
    printf("\n");
}

void graphPrintAdjList(const WeightedUndirectedGraph* g) {
    printf("Adjacency List:\n");
    for (int i = 0; i <= g->n; i++) {
        printf("%d: ", i);
        if (g->adjList[i].size == 0) {
            printf("No neighbors");
        } else {
            for (int j = 0; j < g->adjList[i].size; j++) {
                if (j > 0) printf(" ");
                printf("(");
                printPair(&g->adjList[i].data[j]);
                printf(")");
            }
        }
        printf("\n");
    }
}

void graphFree(WeightedUndirectedGraph* g) {
    for (int i = 0; i <= g->n; i++)
        pairListFree(&g->adjList[i]);
    free(g->adjList);
    tripletListFree(&g->edgeList);
}

/* ── main ─────────────────────────────────────────────────────────── */
int main(void) {
    int t;
    scanf("%d", &t);

    while (t--) {
        int V, E;
        scanf("%d %d", &V, &E);

        WeightedUndirectedGraph g;
        graphInit(&g, V);

        for (int i = 0; i < E; i++) {
            int u, v, w;
            scanf("%d %d %d", &u, &v, &w);
            graphAddEdge(&g, u, v, w);
        }

        /* SOLUTION LOGIC HERE */

        graphPrintEdgeList(&g);
        graphPrintAdjList(&g);

        graphFree(&g);
    }

    return 0;
}
