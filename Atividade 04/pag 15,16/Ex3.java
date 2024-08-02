import java.util.*;

public class Ex3 {
    public static void main(String[] args) {
        // Grafo representado como uma matriz de adjacência
        int[][] grafo = {
            {0, 11, 5, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},   // Mercearia
            {11, 0, Integer.MAX_VALUE, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE},  // B
            {5, Integer.MAX_VALUE, 0, 2, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},   // C
            {8, 3, 2, 0, 4, Integer.MAX_VALUE, 12, 11},  // D
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 8, 4, 0, 15, Integer.MAX_VALUE, 4},  // E
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 15, 0, 3, 7},  // F
            {Integer.MAX_VALUE, 8, Integer.MAX_VALUE, 12, Integer.MAX_VALUE, 3, 0, 2},  // G
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 11, 4, 7, 2, 0}   // H
        };

        int startVertex = 0; // Vértice Mercearia (index 0)
        int nVertices = grafo[0].length;

        // Algoritmo de Dijkstra
        int[] shortestDistances = dijkstra(grafo, startVertex, nVertices);

        // Imprimir os menores caminhos
        System.out.println("Os menores caminhos da Mercearia para todas as outras localidades:");
        for (int i = 0; i < shortestDistances.length; i++) {
            System.out.println("Mercearia -> " + (char) (i + 'A') + ": " + (shortestDistances[i] == Integer.MAX_VALUE ? "Infinito" : shortestDistances[i]));
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
