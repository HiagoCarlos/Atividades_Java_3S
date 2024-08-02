import java.util.*;

class Ex1 {
    private int vertices;
    private LinkedList<Par<Integer, Integer>>[] adj;

    class Par<U, V> {
        public U first;
        public V second;

        public Par(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    @SuppressWarnings("unchecked")
    Ex1(int vertices) {
        this.vertices = vertices;
        adj = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void adicionarAresta(int origem, int destino, int peso) {
        adj[origem].add(new Par<>(destino, peso));
        adj[destino].add(new Par<>(origem, peso));
    }

    void dijkstra(int src) {
        PriorityQueue<Par<Integer, Integer>> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(a -> a.second));
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Par<>(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().first;

            for (Par<Integer, Integer> par : adj[u]) {
                int v = par.first;
                int weight = par.second;

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Par<>(v, dist[v]));
                }
            }
        }

        printarSolucoes(dist);
    }

    void printarSolucoes(int[] dist) {
        System.out.println("Distância do vértice A para todos os outros vértices:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("A -> " + i + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Ex1 g = new Ex1(11);
        g.adicionarAresta(0, 1, 70);  // A -> B
        g.adicionarAresta(0, 2, 31);  // A -> C
        g.adicionarAresta(0, 3, 26);  // A -> D
        g.adicionarAresta(0, 4, 12);  // A -> E
        g.adicionarAresta(1, 5, 110); // B -> F
        g.adicionarAresta(2, 6, 70);  // C -> G
        g.adicionarAresta(3, 7, 100); // D -> H
        g.adicionarAresta(4, 8, 67);  // E -> I
        g.adicionarAresta(5, 9, 30);  // F -> J
        g.adicionarAresta(6, 10, 126);// G -> K

        g.dijkstra(0);
    }
}
