import java.util.*;

class Ex1 {
    private Map<Integer, List<Integer>> adjacencia;

    public Ex1() {
        this.adjacencia = new HashMap<>();
    }

    public void adicionarVertice(int vertice) {
        adjacencia.putIfAbsent(vertice, new ArrayList<>());
    }

    public void adicionarAresta(int origem, int destino) {
        adjacencia.putIfAbsent(origem, new ArrayList<>());
        adjacencia.putIfAbsent(destino, new ArrayList<>());
        adjacencia.get(origem).add(destino);
        adjacencia.get(destino).add(origem);
    }

    public void exibirGrafo() {
        for (var entry : adjacencia.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (var vizinho : entry.getValue()) {
                System.out.print(vizinho + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Ex1 grafo = new Ex1();
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);
        grafo.adicionarAresta(1, 2);
        grafo.adicionarAresta(2, 3);
        grafo.adicionarAresta(1, 3);

        grafo.exibirGrafo();
    }
}
