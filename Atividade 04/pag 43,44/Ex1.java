import java.util.Arrays;

public class Ex1 {

    public static void main(String[] args) {
        int[][] matrizAdjacencia = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        Ex1 algoritmo = new Ex1();
        algoritmo.exibirDistanciasDijkstra(matrizAdjacencia, 0);
    }

    /**
     * Calcula e exibe as distâncias mínimas do vértice de origem para todos os outros vértices usando o algoritmo de Dijkstra.
     *
     * @param matriz A matriz de adjacência do grafo.
     * @param origem O vértice de origem para o qual calcular as distâncias.
     */
    public void exibirDistanciasDijkstra(int[][] matriz, int origem) {
        int tamanho = matriz.length;
        int[] distancias = new int[tamanho];
        boolean[] verticesVisitados = new boolean[tamanho];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origem] = 0;

        for (int i = 0; i < tamanho - 1; i++) {
            int verticeAtual = encontrarVerticeMinDistancia(distancias, verticesVisitados);
            verticesVisitados[verticeAtual] = true;

            for (int adjacente = 0; adjacente < tamanho; adjacente++) {
                if (!verticesVisitados[adjacente] && matriz[verticeAtual][adjacente] != 0 
                        && distancias[verticeAtual] != Integer.MAX_VALUE
                        && distancias[verticeAtual] + matriz[verticeAtual][adjacente] < distancias[adjacente]) {
                    distancias[adjacente] = distancias[verticeAtual] + matriz[verticeAtual][adjacente];
                }
            }
        }

        exibirResultados(distancias);
    }

    /**
     * Encontra o vértice com a menor distância não visitado.
     *
     * @param distancias O array das distâncias mínimas.
     * @param visitados O array de vértices visitados.
     * @return O índice do vértice com a menor distância.
     */
    public int encontrarVerticeMinDistancia(int[] distancias, boolean[] visitados) {
        int distanciaMinima = Integer.MAX_VALUE;
        int indiceMinimo = -1;

        for (int vertice = 0; vertice < distancias.length; vertice++) {
            if (!visitados[vertice] && distancias[vertice] < distanciaMinima) {
                distanciaMinima = distancias[vertice];
                indiceMinimo = vertice;
            }
        }

        return indiceMinimo;
    }

    /**
     * Exibe as distâncias mínimas de cada vértice a partir da origem.
     *
     * @param distancias O array das distâncias mínimas.
     */
    public void exibirResultados(int[] distancias) {
        System.out.println("Vértice \t Distância da Origem");
        for (int i = 0; i < distancias.length; i++) {
            System.out.println(i + " \t\t " + distancias[i]);
        }
    }
}
