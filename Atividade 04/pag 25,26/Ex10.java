import java.util.ArrayList;
import java.util.List;

public class Ex10 {

    public static void main(String[] args) {
        int[][] matrizAdj = {
            {0, 1, 0, 1, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 1, 0}
        };

        // Converte a matriz de adjacência para lista de adjacência
        List<List<Integer>> listaAdj = converterParaListaAdjacencia(matrizAdj);

        // Exibe a lista de adjacência
        System.out.println("Representação do grafo (Lista de Adjacência):");
        exibirListaAdjacencia(listaAdj);
    }

    /**
     * Converte uma matriz de adjacência em uma lista de adjacência.
     *
     * @param matriz A matriz de adjacência do grafo.
     * @return Uma lista de listas representando a lista de adjacência.
     */
    private static List<List<Integer>> converterParaListaAdjacencia(int[][] matriz) {
        List<List<Integer>> listaAdjacencia = new ArrayList<>();
        
        for (int i = 0; i < matriz.length; i++) {
            List<Integer> adjacentes = new ArrayList<>();
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    adjacentes.add(j);
                }
            }
            listaAdjacencia.add(adjacentes);
        }
        return listaAdjacencia;
    }

    /**
     * Exibe a lista de adjacência no console.
     *
     * @param listaAdj A lista de adjacência a ser exibida.
     */
    private static void exibirListaAdjacencia(List<List<Integer>> listaAdj) {
        for (int i = 0; i < listaAdj.size(); i++) {
            System.out.println("Vértice " + i + " -> " + listaAdj.get(i));
        }
    }
}
