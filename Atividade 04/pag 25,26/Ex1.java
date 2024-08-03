import java.util.Arrays;

public class Ex1 {

    public static void main(String[] args) {
        // Matriz de adjacência para o grafo completo K3
        int[][] k3 = {
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0}
        };
        
        // Matriz de adjacência para o grafo completo K7
        int[][] k7 = {
            {0, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0}
        };
        
        // Calcula e exibe o complemento das matrizes
        int[][] complementoK3 = calcularComplemento(k3);
        int[][] complementoK7 = calcularComplemento(k7);
        
        System.out.println("Complemento de K3:");
        exibirMatriz(complementoK3);
        
        System.out.println("Complemento de K7:");
        exibirMatriz(complementoK7);
    }

    // Método para calcular o complemento de uma matriz de adjacência
    private static int[][] calcularComplemento(int[][] matriz) {
        int tamanho = matriz.length;
        int[][] matrizComplementar = new int[tamanho][tamanho];
        
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i != j) {
                    matrizComplementar[i][j] = 1 - matriz[i][j];
                }
            }
        }
        return matrizComplementar;
    }

    // Método para exibir a matriz no console
    private static void exibirMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            System.out.println(Arrays.toString(linha));
        }
    }
}
