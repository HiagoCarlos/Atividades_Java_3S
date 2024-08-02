import java.util.*;

class Ex10 {
    private final Map<Integer, List<Aresta>> adjacencia = new HashMap<>();

    public void adicionarVertice(int vertice) {
        adjacencia.putIfAbsent(vertice, new ArrayList<>());
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        adjacencia.putIfAbsent(origem, new ArrayList<>());
        adjacencia.putIfAbsent(destino, new ArrayList<>());
        adjacencia.get(origem).add(new Aresta(destino, peso));
        adjacencia.get(destino).add(new Aresta(origem, peso));
    }

    public Map<Integer, Integer> dijkstra(int origem) {
        Map<Integer, Integer> distancias = new HashMap<>();
        PriorityQueue<Aresta> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso));

        adjacencia.keySet().forEach(v -> distancias.put(v, Integer.MAX_VALUE));
        distancias.put(origem, 0);
        heap.add(new Aresta(origem, 0));

        while (!heap.isEmpty()) {
            Aresta atual = heap.poll();
            int verticeAtual = atual.destino;

            for (Aresta vizinho : adjacencia.get(verticeAtual)) {
                int novaDistancia = distancias.get(verticeAtual) + vizinho.peso;
                if (novaDistancia < distancias.get(vizinho.destino)) {
                    distancias.put(vizinho.destino, novaDistancia);
                    heap.add(new Aresta(vizinho.destino, novaDistancia));
                }
            }
        }

        return distancias;
    }

    public static void main(String[] args) {
        Ex10 grafo = new Ex10();
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);
        grafo.adicionarVertice(4);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 2);
        grafo.adicionarAresta(1, 3, 4);
        grafo.adicionarAresta(3, 4, 1);

        Map<Integer, Integer> distancias = grafo.dijkstra(1);
        for (var entry : distancias.entrySet()) {
            System.out.println("Distância do vértice 1 ao vértice " + entry.getKey() + " é " + entry.getValue());
        }
    }
}

class Aresta {
    int destino, peso;

    Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}
