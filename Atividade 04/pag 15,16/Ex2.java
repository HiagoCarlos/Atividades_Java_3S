import java.util.*;

public class Ex2 {

    private static final Map<String, Map<String, Integer>> GRAFO = new HashMap<>();

    public static void main(String[] args) {
        configurarGrafo();
        Map<String, Integer> distancias = calcularDijkstra("A");

        distancias.forEach((vertice, distancia) -> 
            System.out.println("Distância de A para " + vertice + ": " + distancia)
        );
    }

    private static void configurarGrafo() {
        GRAFO.put("A", Map.of("B", 2, "C", 4));
        GRAFO.put("B", Map.of("A", 2, "C", 1, "D", 7));
        GRAFO.put("C", Map.of("A", 4, "B", 1, "D", 3));
        GRAFO.put("D", Map.of("B", 7, "C", 3));
    }

    private static Map<String, Integer> calcularDijkstra(String origem) {
        Map<String, Integer> distancias = new HashMap<>();
        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(v -> v.distancia));
        Set<String> visitados = new HashSet<>();

        // Inicializa todas as distâncias como infinito
        for (String vertice : GRAFO.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);
        filaPrioridade.add(new Vertice(origem, 0));

        while (!filaPrioridade.isEmpty()) {
            Vertice verticeAtual = filaPrioridade.poll();
            String nomeVerticeAtual = verticeAtual.nome;

            if (!visitados.contains(nomeVerticeAtual)) {
                visitados.add(nomeVerticeAtual);
                Map<String, Integer> vizinhos = GRAFO.get(nomeVerticeAtual);

                for (Map.Entry<String, Integer> entrada : vizinhos.entrySet()) {
                    String vizinho = entrada.getKey();
                    int pesoAresta = entrada.getValue();
                    int novaDistancia = distancias.get(nomeVerticeAtual) + pesoAresta;

                    if (novaDistancia < distancias.get(vizinho)) {
                        distancias.put(vizinho, novaDistancia);
                        filaPrioridade.add(new Vertice(vizinho, novaDistancia));
                    }
                }
            }
        }

        return distancias;
    }

    static class Vertice {
        String nome;
        int distancia;

        Vertice(String nome, int distancia) {
            this.nome = nome;
            this.distancia = distancia;
        }
    }
}
