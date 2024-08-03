import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ex1 {

    private static final Map<Integer, Set<Integer>> GRAFO1 = new HashMap<>();
    private static final Map<Integer, Set<Integer>> GRAFO2 = new HashMap<>();
    private static final Map<Integer, Integer> CORRESPONDENCIA = new HashMap<>();

    public static void main(String[] args) {
        inicializarGrafos();
        inicializarCorrespondencia();

        if (verificarIsomorfismo()) {
            System.out.println("A correspondência proposta preserva o isomorfismo.");
        } else {
            System.out.println("A correspondência proposta NÃO preserva o isomorfismo.");
        }
    }

    private static void inicializarGrafos() {
        // Inicializa o grafo 1
        GRAFO1.put(1, Set.of(2, 3));
        GRAFO1.put(2, Set.of(1, 4));
        GRAFO1.put(3, Set.of(1, 4));
        GRAFO1.put(4, Set.of(2, 3));

        // Inicializa o grafo 2
        GRAFO2.put(1, Set.of(2, 3));
        GRAFO2.put(2, Set.of(1, 4));
        GRAFO2.put(3, Set.of(1, 4));
        GRAFO2.put(4, Set.of(2, 3));
    }

    private static void inicializarCorrespondencia() {
        // Define a correspondência entre os vértices dos dois grafos
        CORRESPONDENCIA.put(1, 1);
        CORRESPONDENCIA.put(2, 2);
        CORRESPONDENCIA.put(3, 3);
        CORRESPONDENCIA.put(4, 4);
    }

    private static boolean verificarIsomorfismo() {
        for (Integer verticeGrafo1 : GRAFO1.keySet()) {
            Integer verticeCorrespondente = CORRESPONDENCIA.get(verticeGrafo1);
            if (verticeCorrespondente == null) {
                return false;
            }
            
            Set<Integer> vizinhosGrafo1 = GRAFO1.get(verticeGrafo1);
            Set<Integer> vizinhosGrafo2 = GRAFO2.get(verticeCorrespondente);
            
            Set<Integer> vizinhosMapeados = new HashSet<>();
            for (Integer vizinho : vizinhosGrafo1) {
                Integer vizinhoMapeado = CORRESPONDENCIA.get(vizinho);
                if (vizinhoMapeado != null) {
                    vizinhosMapeados.add(vizinhoMapeado);
                }
            }

            if (!vizinhosMapeados.equals(vizinhosGrafo2)) {
                return false;
            }
        }
        return true;
    }
}
