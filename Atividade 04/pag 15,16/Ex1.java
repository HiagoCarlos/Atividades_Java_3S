import java.util.HashMap;

public class Ex1 {
    public static void main(String[] args) {
        // Grafo 1
        char[] verticesGrafo1 = {'a', 'b', 'c', 'd'};
        int[][] arestasGrafo1 = {
            {0, 1, 0, 1},  // a
            {1, 0, 1, 0},  // b
            {0, 1, 0, 1},  // c
            {1, 0, 1, 0}   // d
        };

        // Grafo 2
        char[] verticesGrafo2 = {'w', 'x', 'y', 'z'};
        int[][] arestasGrafo2 = {
            {0, 0, 1, 1},  // w
            {0, 0, 1, 1},  // x
            {1, 1, 0, 0},  // y
            {1, 1, 0, 0}   // z
        };

        // Correspondência proposta
        HashMap<Character, Character> correspondencia = new HashMap<>();
        correspondencia.put('a', 'w');
        correspondencia.put('b', 'x');
        correspondencia.put('c', 'y');
        correspondencia.put('d', 'z');

        // Verificação do isomorfismo
        boolean isIsomorph = checkIsomorphism(verticesGrafo1, arestasGrafo1, verticesGrafo2, arestasGrafo2, correspondencia);
        if (isIsomorph) {
            System.out.println("Os grafos são isomorfos.");
        } else {
            System.out.println("Os grafos não são isomorfos.");
        }
    }

    public static boolean checkIsomorphism(char[] vertices1, int[][] edges1, char[] vertices2, int[][] edges2, HashMap<Character, Character> correspondence) {
        for (int i = 0; i < vertices1.length; i++) {
            for (int j = 0; j < vertices1.length; j++) {
                char v1 = vertices1[i];
                char u1 = vertices1[j];
                char v2 = correspondence.get(v1);
                char u2 = correspondence.get(u1);
                int indexV2 = new String(vertices2).indexOf(v2);
                int indexU2 = new String(vertices2).indexOf(u2);
                if (edges1[i][j] != edges2[indexV2][indexU2]) {
                    return false;
                }
            }
        }
        return true;
    }
}
