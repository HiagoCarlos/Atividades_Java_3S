import java.util.*;

public class Ex3 {

    // Classe interna para representar o grafo
    static class Grafo {
        private final int totalVertices;
        private final List<Set<Integer>> listaAdjacencia;

        // Construtor para inicializar o grafo com o número especificado de vértices
        public Grafo(int totalVertices) {
            this.totalVertices = totalVertices;
            listaAdjacencia = new ArrayList<>();
            for (int i = 0; i < totalVertices; i++) {
                listaAdjacencia.add(new HashSet<>());
            }
        }

        // Método para adicionar uma aresta entre dois vértices
        public void adicionarAresta(int de, int para) {
            listaAdjacencia.get(de).add(para);
            listaAdjacencia.get(para).add(de);
        }

        // Método para obter os graus dos vértices no grafo
        public int[] calcularGraus() {
            int[] graus = new int[totalVertices];
            for (int i = 0; i < totalVertices; i++) {
                graus[i] = listaAdjacencia.get(i).size();
            }
            return graus;
        }

        // Método para verificar se existe um ciclo de tamanho 3 no grafo
        public boolean possuiCiclo3() {
            for (int i = 0; i < totalVertices; i++) {
                for (int adjacente1 : listaAdjacencia.get(i)) {
                    for (int adjacente2 : listaAdjacencia.get(adjacente1)) {
                        if (adjacente2 != i && listaAdjacencia.get(adjacente2).contains(i)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    // Método para verificar se dois grafos são isomorfos
    public static boolean saoIsomorfos(Grafo grafo1, Grafo grafo2) {
        int[] grausGrafo1 = grafo1.calcularGraus();
        int[] grausGrafo2 = grafo2.calcularGraus();

        // Ordenando os graus dos vértices para comparação
        Arrays.sort(grausGrafo1);
        Arrays.sort(grausGrafo2);

        // Verificando se os graus dos vértices são iguais
        if (!Arrays.equals(grausGrafo1, grausGrafo2)) {
            return false;
        }

        // Verificando se ambos os grafos têm ciclos de tamanho 3
        boolean ciclo3NoGrafo1 = grafo1.possuiCiclo3();
        boolean ciclo3NoGrafo2 = grafo2.possuiCiclo3();

        return ciclo3NoGrafo1 == ciclo3NoGrafo2;
    }

    public static void main(String[] args) {
        // Criando Grafo 1.11
        Grafo primeiroGrafo = new Grafo(4);
        primeiroGrafo.adicionarAresta(0, 1);
        primeiroGrafo.adicionarAresta(0, 2);
        primeiroGrafo.adicionarAresta(0, 3);
        primeiroGrafo.adicionarAresta(1, 2);
        primeiroGrafo.adicionarAresta(1, 3);
        primeiroGrafo.adicionarAresta(2, 3);

        // Criando Grafo 1.12
        Grafo segundoGrafo = new Grafo(4);
        segundoGrafo.adicionarAresta(0, 1);
        segundoGrafo.adicionarAresta(1, 2);
        segundoGrafo.adicionarAresta(2, 3);
        segundoGrafo.adicionarAresta(3, 0);

        // Verificando isomorfismo
        if (saoIsomorfos(primeiroGrafo, segundoGrafo)) {
            System.out.println("Os grafos 1.11 e 1.12 são isomorfos.");
        } else {
            System.out.println("Os grafos 1.11 e 1.12 NÃO são isomorfos.");
        }
    }
}
