import java.util.ArrayList;
import java.util.HashMap;

public class Ex2 {

    static class Grafo {
        private HashMap<Character, ArrayList<Character>> listaAdjacencia;

        public Grafo() {
            listaAdjacencia = new HashMap<>();
        }

        public void adicionarAresta(char vertice1, char vertice2) {
            listaAdjacencia.computeIfAbsent(vertice1, k -> new ArrayList<>()).add(vertice2);
            listaAdjacencia.computeIfAbsent(vertice2, k -> new ArrayList<>()).add(vertice1);
        }

        public void calcularGraus() {
            int somaGraus = 0;
            for (char vertice : listaAdjacencia.keySet()) {
                int grau = listaAdjacencia.get(vertice).size();
                System.out.println("Grau do vértice " + vertice + ": " + grau);
                somaGraus += grau;
            }
            System.out.println("Soma dos graus dos vértices: " + somaGraus);
            System.out.println("Número de arestas: " + (somaGraus / 2));
            System.out.println("Observação: A soma dos graus dos vértices é o dobro do número de arestas.");
        }
    }

    public static void main(String[] args) {
        Grafo[] grafos = criarGrafos();

        for (int i = 0; i < grafos.length; i++) {
            System.out.println("Grafo " + (i + 1) + ":");
            grafos[i].calcularGraus();
            System.out.println();
        }
    }

    private static Grafo[] criarGrafos() {
        Grafo[] grafos = new Grafo[8];

        for (int i = 0; i < grafos.length; i++) {
            grafos[i] = new Grafo();
        }

        grafos[0].adicionarAresta('A', 'B');
        grafos[0].adicionarAresta('A', 'C');
        grafos[0].adicionarAresta('B', 'D');

        grafos[1].adicionarAresta('A', 'B');
        grafos[1].adicionarAresta('A', 'C');
        grafos[1].adicionarAresta('A', 'D');
        grafos[1].adicionarAresta('B', 'D');

        grafos[2].adicionarAresta('A', 'B');
        grafos[2].adicionarAresta('A', 'C');
        grafos[2].adicionarAresta('A', 'D');
        grafos[2].adicionarAresta('B', 'C');
        grafos[2].adicionarAresta('B', 'D');

        grafos[3].adicionarAresta('A', 'B');
        grafos[3].adicionarAresta('A', 'C');
        grafos[3].adicionarAresta('A', 'D');
        grafos[3].adicionarAresta('B', 'C');
        grafos[3].adicionarAresta('B', 'D');
        grafos[3].adicionarAresta('C', 'D');

        grafos[4].adicionarAresta('A', 'B');
        grafos[4].adicionarAresta('A', 'C');
        grafos[4].adicionarAresta('A', 'D');
        grafos[4].adicionarAresta('A', 'E');
        grafos[4].adicionarAresta('B', 'C');
        grafos[4].adicionarAresta('B', 'D');
        grafos[4].adicionarAresta('B', 'E');
        grafos[4].adicionarAresta('C', 'D');
        grafos[4].adicionarAresta('C', 'E');
        grafos[4].adicionarAresta('D', 'E');

        grafos[5].adicionarAresta('A', 'B');
        grafos[5].adicionarAresta('A', 'C');
        grafos[5].adicionarAresta('A', 'D');
        grafos[5].adicionarAresta('A', 'E');
        grafos[5].adicionarAresta('A', 'F');
        grafos[5].adicionarAresta('B', 'C');
        grafos[5].adicionarAresta('B', 'D');
        grafos[5].adicionarAresta('B', 'E');
        grafos[5].adicionarAresta('B', 'F');
        grafos[5].adicionarAresta('C', 'D');
        grafos[5].adicionarAresta('C', 'E');
        grafos[5].adicionarAresta('C', 'F');
        grafos[5].adicionarAresta('D', 'E');
        grafos[5].adicionarAresta('D', 'F');
        grafos[5].adicionarAresta('E', 'F');

        grafos[6].adicionarAresta('A', 'B');
        grafos[6].adicionarAresta('A', 'C');
        grafos[6].adicionarAresta('A', 'D');
        grafos[6].adicionarAresta('A', 'E');
        grafos[6].adicionarAresta('B', 'C');
        grafos[6].adicionarAresta('B', 'D');
        grafos[6].adicionarAresta('B', 'E');
        grafos[6].adicionarAresta('C', 'D');
        grafos[6].adicionarAresta('C', 'E');
        grafos[6].adicionarAresta('D', 'E');
        grafos[6].adicionarAresta('E', 'A');

        grafos[7].adicionarAresta('A', 'B');
        grafos[7].adicionarAresta('A', 'C');
        grafos[7].adicionarAresta('A', 'D');
        grafos[7].adicionarAresta('A', 'E');
        grafos[7].adicionarAresta('A', 'F');
        grafos[7].adicionarAresta('B', 'C');
        grafos[7].adicionarAresta('B', 'D');
        grafos[7].adicionarAresta('B', 'E');
        grafos[7].adicionarAresta('B', 'F');
        grafos[7].adicionarAresta('C', 'D');
        grafos[7].adicionarAresta('C', 'E');
        grafos[7].adicionarAresta('C', 'F');
        grafos[7].adicionarAresta('D', 'E');
        grafos[7].adicionarAresta('D', 'F');
        grafos[7].adicionarAresta('E', 'F');
        grafos[7].adicionarAresta('F', 'A');

        return grafos;
    }
}
