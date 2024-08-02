import java.util.Arrays;
import java.util.PriorityQueue;

public class Ex2 {
    private static final int INF = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 11, 5, 8, INF, INF, INF, INF},
            {11, 0, INF, 3, INF, INF, 8, INF},
            {5, INF, 0, 2, 8, INF, INF, INF},
            {8, 3, 2, 0, INF, 4, 6, 7},
            {INF, INF, 8, INF, 0, INF, INF, 3},
            {INF, INF, INF, 4, INF, 0, 3, 6},
            {INF, 8, INF, 6, INF, 3, 0, 2},
            {INF, INF, INF, 7, 3, 6, 2, 0}
        };
        
        int startVertex = 0; // Mercearia
        dijkstra(graph, startVertex);
    }
    
    public static void dijkstra(int[][] graph, int startVertex) {
        int nVertices = graph.length;
        int[] distances = new int[nVertices];
        boolean[] visited = new boolean[nVertices];

        Arrays.fill(distances, INF);
        distances[startVertex] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(nVertices, (a, b) -> a.cost - b.cost);
        pq.add(new Node(startVertex, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int vertex = node.vertex;

            if (!visited[vertex]) {
                visited[vertex] = true;

                for (int i = 0; i < nVertices; i++) {
                    if (graph[vertex][i] != INF && !visited[i]) {
                        int newDist = distances[vertex] + graph[vertex][i];
                        if (newDist < distances[i]) {
                            distances[i] = newDist;
                            pq.add(new Node(i, newDist));
                        }
                    }
                }
            }
        }

        printSolution(startVertex, distances);
    }

    public static void printSolution(int startVertex, int[] distances) {
        System.out.println("Menor caminho da Mercearia para todas as outras localidades:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Para " + i + " = " + distances[i]);
        }
    }
}

class Node {
    int vertex;
    int cost;

    Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}
