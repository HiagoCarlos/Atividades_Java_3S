public class Ex1 {

    public static int calcularArestas(int vertices) {
        // Calcula o número de arestas em um grafo completo com 'vertices' vértices
        return vertices * (vertices - 1) / 2;
    }

    public static void main(String[] args) {
        // Calcula e exibe o número de arestas para K7 e K12
        int arestasK7 = calcularArestas(7);
        int arestasK12 = calcularArestas(12);

        System.out.println("Número de arestas em K7: " + arestasK7);
        System.out.println("Número de arestas em K12: " + arestasK12);

        // Calcula e exibe o número de arestas para K5
        int numVertices = 5; 
        int arestasKn = calcularArestas(numVertices);
        System.out.println("Número de arestas em K" + numVertices + ": " + arestasKn);
    }
}
