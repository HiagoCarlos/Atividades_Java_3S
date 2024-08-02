import java.util.*;

class Ex2 {
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
    Ex2(int vertices) {
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
        System.out.println("Distância da Mercearia para todas as outras localidades:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Mercearia -> " + i + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Ex2 g = new Ex2(8);
        g.adicionarAresta(0, 1, 11);  // Mercearia -> B
        g.adicionarAresta(0, 2, 5);   // Mercearia -> C
        g.adicionarAresta(0, 3, 8);   // Mercearia -> D
        g.adicionarAresta(1, 3, 3);   // B -> D
        g.adicionarAresta(1, 6, 8);   // B -> G
        g.adicionarAresta(2, 3, 2);   // C -> D
        g.adicionarAresta(2, 4, 8);   // C -> E
        g.adicionarAresta(3, 4, 4);   // D -> E
        g.adicionarAresta(3, 6, 12);  // D -> G
        g.adicionarAresta(3, 7, 11);  // D -> H
        g.adicionarAresta(4, 5, 15);  // E -> F
        g.adicionarAresta(4, 7, 4);   // E -> H
        g.adicionarAresta(5, 6, 3);   // F -> G
        g.adicionarAresta(5, 7, 7);   // F -> H
        g.adicionarAresta(6, 7, 2);   // G -> H

        g.dijkstra(0);
    }
}
