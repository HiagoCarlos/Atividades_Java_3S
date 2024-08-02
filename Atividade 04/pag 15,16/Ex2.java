import java.util.*;

public class Ex2 {
    public static void main(String[] args) {
        // Grafo representado como uma matriz de adjacência
        int[][] grafo = {
            {0, 70, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0},   // A
            {70, 0, 0, 110, 0, 0, 0, 0, 0, 0, 0, 0}, // B
            {31, 0, 0, 0, 39, 0, 0, 0, 0, 0, 0, 0},  // C
            {0, 110, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0}, // D
            {0, 0, 39, 0, 0, 26, 12, 0, 0, 0, 0, 0}, // E
            {0, 0, 0, 30, 26, 0, 0, 0, 0, 0, 0, 0},  // F
            {0, 0, 0, 0, 12, 0, 0, 19, 61, 0, 0, 0}, // G
            {0, 0, 0, 0, 0, 0, 19, 0, 0, 100, 67, 0},// H
            {0, 0, 0, 0, 0, 0, 61, 0, 0, 0, 0, 140}, // I
            {0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 85}, // J
            {0, 0, 0, 0, 0, 0, 0, 67, 0, 0, 0, 74},  // L
            {0, 0, 0, 0, 0, 0, 0, 0, 140, 85, 74, 0} // M
        };

        int startVertex = 0; // Vértice A (index 0)
        int nVertices = grafo[0].length;

        // Algoritmo de Dijkstra
        int[] shortestDistances = dijkstra(grafo, startVertex, nVertices);

        // Imprimir os menores caminhos
        System.out.println("Os menores caminhos do vértice A para todos os outros vértices:");
        for (int i = 0; i < shortestDistances.length; i++) {
            System.out.println("A -> " + (char) (i + 'A') + ": " + shortestDistances[i]);
        }
    }

    public static int[] dijkstra(int[][] graph, int startVertex, int nVertices) {
        int[] shortestDistances = new int[nVertices];
        boolean[] added = new boolean[nVertices];

        // Inicializar todas as distâncias com infinito e nenhuma adicionada
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        Arrays.fill(added, false);

        // A distância do vértice inicial para si mesmo é 0
        shortestDistances[startVertex] = 0;

        // Calcular as menores distâncias
        for (int i = 0; i < nVertices - 1; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            added[nearestVertex] = true;

            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = graph[nearestVertex][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        return shortestDistances;
    }
}
