public class Ex1 {
    public static void main(String[] args) {
        int n = 5; // Exemplo com 5 vértices, você pode mudar para qualquer valor de n
        int[] graus = new int[n];

        // Calcula o grau de cada vértice
        for (int i = 0; i < n; i++) {
            graus[i] = n - 1;
        }

        // Soma dos graus
        int somaGraus = 0;
        for (int grau : graus) {
            somaGraus += grau;
        }

        // Número de arestas
        int numeroArestas = (n * (n - 1)) / 2;

        // Exibe resultados
        System.out.println("Grau de cada vértice:");
        for (int i = 0; i < n; i++) {
            System.out.println("Vértice " + i + ": " + graus[i]);
        }

        System.out.println("Soma de todos os graus: " + somaGraus);
        System.out.println("Número de arestas: " + numeroArestas);
    }
}
