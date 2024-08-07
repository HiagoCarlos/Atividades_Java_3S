import java.io.*;
import java.util.*;

public class Atividade {

    public static void main(String[] args) {
        try {
            // Carrega os números do arquivo
            int[] numeros = carregarNumeros("dados_500mil.txt");

            // Ordenação usando Heap Sort
            int[] numerosHeapSort = Arrays.copyOf(numeros, numeros.length);
            long inicioHeapSort = System.nanoTime();
            realizarHeapSort(numerosHeapSort);
            long fimHeapSort = System.nanoTime();
            System.out.println("Tempo de execução do Heap Sort: " + (fimHeapSort - inicioHeapSort) + " nanosegundos");

            // Inserção e travessia em ordem na Árvore Binária
            long inicioBST = System.nanoTime();
            ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
            for (int numero : numeros) {
                arvoreBinaria.adicionar(numero);
            }
            List<Integer> bstEmOrdem = arvoreBinaria.travessiaEmOrdem();
            long fimBST = System.nanoTime();
            System.out.println("Tempo de execução da Árvore Binária: " + (fimBST - inicioBST) + " nanosegundos");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega os números do arquivo e retorna um array de inteiros
    private static int[] carregarNumeros(String caminhoArquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        List<Integer> listaNumeros = new ArrayList<>();
        String linha;
        while ((linha = leitor.readLine()) != null) {
            linha = linha.trim();
            if (!linha.isEmpty()) {
                try {
                    listaNumeros.add(Integer.parseInt(linha));
                } catch (NumberFormatException ex) {
                    System.err.println("Linha inválida ignorada: " + linha);
                }
            }
        }
        leitor.close();
        return listaNumeros.stream().mapToInt(Integer::intValue).toArray();
    }

    // Realiza a ordenação usando Heap Sort
    public static void realizarHeapSort(int[] array) {
        int tamanho = array.length;

        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            heapificar(array, tamanho, i);
        }

        for (int i = tamanho - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapificar(array, i, 0);
        }
    }

    // Ajusta a estrutura do heap
    private static void heapificar(int[] array, int tamanho, int indice) {
        int maior = indice;
        int esquerda = 2 * indice + 1;
        int direita = 2 * indice + 2;

        if (esquerda < tamanho && array[esquerda] > array[maior]) {
            maior = esquerda;
        }

        if (direita < tamanho && array[direita] > array[maior]) {
            maior = direita;
        }

        if (maior != indice) {
            int troca = array[indice];
            array[indice] = array[maior];
            array[maior] = troca;

            heapificar(array, tamanho, maior);
        }
    }

    // Classe para a Árvore Binária de Busca
    static class ArvoreBinaria {
        class Nodo {
            int dado;
            Nodo esquerda, direita;

            public Nodo(int valor) {
                dado = valor;
                esquerda = direita = null;
            }
        }

        Nodo raiz;

        // Adiciona um valor na árvore
        void adicionar(int valor) {
            raiz = adicionarRecursivo(raiz, valor);
        }

        private Nodo adicionarRecursivo(Nodo nodo, int valor) {
            if (nodo == null) {
                return new Nodo(valor);
            }

            if (valor < nodo.dado) {
                nodo.esquerda = adicionarRecursivo(nodo.esquerda, valor);
            } else if (valor > nodo.dado) {
                nodo.direita = adicionarRecursivo(nodo.direita, valor);
            }

            return nodo;
        }

        // Realiza a travessia em ordem
        List<Integer> travessiaEmOrdem() {
            List<Integer> resultado = new ArrayList<>();
            travessiaEmOrdemRecursiva(raiz, resultado);
            return resultado;
        }

        private void travessiaEmOrdemRecursiva(Nodo nodo, List<Integer> resultado) {
            if (nodo != null) {
                travessiaEmOrdemRecursiva(nodo.esquerda, resultado);
                resultado.add(nodo.dado);
                travessiaEmOrdemRecursiva(nodo.direita, resultado);
            }
        }
    }
}
