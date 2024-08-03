import java.util.*;

class Grafo {
    private final Map<String, Set<String>> adjacencias;

    public Grafo() {
        adjacencias = new HashMap<>();
    }

    public void adicionarAresta(String vertice1, String vertice2) {
        adjacencias.computeIfAbsent(vertice1, k -> new HashSet<>()).add(vertice2);
        adjacencias.computeIfAbsent(vertice2, k -> new HashSet<>()).add(vertice1);
    }

    public int obterGrauVertice(String vertice) {
        return adjacencias.getOrDefault(vertice, Collections.emptySet()).size();
    }

    public int obterSomaGraus() {
        int soma = 0;
        for (String vertice : adjacencias.keySet()) {
            soma += obterGrauVertice(vertice);
        }
        return soma;
    }

    public int contarArestas() {
        int totalArestas = 0;
        for (Set<String> vizinhos : adjacencias.values()) {
            totalArestas += vizinhos.size();
        }
        return totalArestas / 2;
    }

    public void exibirGrafo() {
        for (Map.Entry<String, Set<String>> entrada : adjacencias.entrySet()) {
            System.out.print(entrada.getKey() + ": ");
            for (String vizinho : entrada.getValue()) {
                System.out.print(vizinho + " ");
            }
            System.out.println();
        }
    }

    public void mostrarGraus() {
        for (String vertice : adjacencias.keySet()) {
            System.out.println("Grau do vértice " + vertice + ": " + obterGrauVertice(vertice));
        }
    }
}

public class Ex1 {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.adicionarAresta("6A", "7A");
        grafo.adicionarAresta("6A", "7B");
        grafo.adicionarAresta("6A", "8B");
        grafo.adicionarAresta("6B", "7A");
        grafo.adicionarAresta("6B", "8A");
        grafo.adicionarAresta("6B", "8B");
        grafo.adicionarAresta("7B", "8A");
        grafo.adicionarAresta("7B", "8B");
        grafo.adicionarAresta("8A", "8B");

        System.out.println("Grafo do Campeonato:");
        grafo.exibirGrafo();

        System.out.println("\nGraus dos vértices:");
        grafo.mostrarGraus();

        int somaGraus = grafo.obterSomaGraus();
        System.out.println("\nSoma de todos os graus: " + somaGraus);

        int numArestas = grafo.contarArestas();
        System.out.println("Número de arestas: " + numArestas);

        System.out.println("Observação: A soma dos graus dos vértices é igual ao dobro do número de arestas, pois cada aresta é contada duas vezes.");
    }
}
