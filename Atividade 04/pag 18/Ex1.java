import java.util.Arrays;

public class Ex1 {

    public static void main(String[] args) {
        // Definição do grafo utilizando matriz de adjacência
        int[][] graph = {
                {0, 74, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {74, 0, 0, 0, 0, 0, 0, 140, 0, 0, 0, 0},
                {31, 0, 0, 67, 0, 0, 0, 105, 0, 0, 0, 0},
                {0, 0, 67, 0, 70, 30, 0, 0, 126, 0, 0, 0},
                {0, 0, 0, 70, 0, 39, 19, 0, 0, 61, 0, 0},
                {0, 0, 0, 30, 39, 0, 12, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 19, 12, 0, 0, 0, 0, 85, 0},
                {0, 140, 105, 0, 0, 0, 0, 0, 0, 0, 0, 100},
                {0, 0, 0, 126, 0, 0, 0, 0, 0, 30, 0, 26},
                {0, 0, 0, 0, 61, 0, 0, 0, 30, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 85, 0, 0, 0, 0, 110},
                {0, 0, 0, 0, 0, 0, 0, 100, 26, 0, 110, 0}
        };

        int startVertex = 0; // Vértice A (índice 0)
        int nVertices = graph.length;

        int[] shortestDistances = dijkstra(graph, startVertex, nVertices);

        // Imprimir os menores caminhos
        System.out.println("Os menores caminhos do vértice A para todos os outros vértices:");
        for (int i = 0; i < shortestDistances.length; i++) {
            System.out.println("A -> " + (char) (i + 'A') + ": " + (shortestDistances[i] == Integer.MAX_VALUE ? "Infinito" : shortestDistances[i]));
        }
    }

    public static int[] dijkstra(int[][] graph, int startVertex, int nVertices) {
        int[] shortestDistances = new int[nVertices];
        boolean[] added = new boolean[nVertices];

        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        Arrays.fill(added, false);

        shortestDistances[startVertex] = 0;

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
