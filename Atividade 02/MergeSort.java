import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        // Ler os números do arquivo
        List<Integer> numbers = readNumbersFromFile("dados100_mil.txt");

        // Exibir os números antes da ordenação
        System.out.println("Números antes da ordenação:");
        System.out.println(numbers);

        // Iniciar contagem de tempo
        long startTime = System.currentTimeMillis();

        // Ordenar os números com o Merge Sort
        mergeSort(numbers);

        // Finalizar contagem de tempo
        long endTime = System.currentTimeMillis();

        // Exibir os números ordenados
        System.out.println("Números ordenados com Merge Sort:");
        System.out.println(numbers);

        // Calcular o tempo de execução
        long executionTime = endTime - startTime;

        // Imprimir o tempo de execução
        System.out.println("Tempo de execução do Merge Sort: " + executionTime + " milissegundos");
    }

    public static List<Integer> readNumbersFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Ignorar linhas em branco
                String[] tokens = line.split(",\\s+");
                for (String token : tokens) {
                    try {
                        int num = Integer.parseInt(token.trim());
                        numbers.add(num);
                    } catch (NumberFormatException e) {
                        System.err.println("Ignorando token inválido: " + token);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    
        // Saída de depuração - imprimir os números lidos do arquivo
        System.out.println("Números lidos do arquivo:");
        System.out.println(numbers);
    
        return numbers;
    }
    

    public static void mergeSort(List<Integer> arr) {
        if (arr == null || arr.size() < 2) {
            return;
        }
        sort(arr, 0, arr.size() - 1);
    }

    private static void sort(List<Integer> arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            sort(arr, low, mid);
            sort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(List<Integer> arr, int low, int mid, int high) {
        List<Integer> temp = new ArrayList<>(arr);
        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high) {
            if (temp.get(i) <= temp.get(j)) {
                arr.set(k++, temp.get(i++));
            } else {
                arr.set(k++, temp.get(j++));
            }
        }

        while (i <= mid) {
            arr.set(k++, temp.get(i++));
        }

        while (j <= high) {
            arr.set(k++, temp.get(j++));
        }
    }
}
