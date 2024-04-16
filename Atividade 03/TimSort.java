import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimSort {
    private static long comparisons = 0;
    private static long movements = 0;

    public static void main(String[] args) {
        List<Integer> numbers = readNumbersFromFile("dados500_mil.txt");
        System.out.println("Números antes da ordenação:");
        System.out.println(numbers);
        long startTime = System.currentTimeMillis();
        timSort(numbers, 0, numbers.size() - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Números ordenados com TimSort:");
        System.out.println(numbers);
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execução do TimSort: " + executionTime + " milissegundos");
        System.out.println("Número de comparações: " + comparisons);
        System.out.println("Quantidade de Movimentações: " + movements);
    }

    public static List<Integer> readNumbersFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; 
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

        System.out.println("Números lidos do arquivo:");
        System.out.println(numbers);

        return numbers;
    }

    public static void timSort(List<Integer> arr, int left, int right) {
        if (right - left + 1 < 64) {
            insertionSort(arr, left, right);
            return;
        }
        int mid = (left + right) >>> 1;
        timSort(arr, left, mid);
        timSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(List<Integer> arr, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;
        List<Integer> leftArr = new ArrayList<>();
        List<Integer> rightArr = new ArrayList<>();
        for (int i = 0; i < len1; i++) {
            leftArr.add(arr.get(left + i));
        }
        for (int i = 0; i < len2; i++) {
            rightArr.add(arr.get(mid + 1 + i));
        }
        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            comparisons++;
            if (leftArr.get(i) <= rightArr.get(j)) {
                arr.set(k++, leftArr.get(i++));
            } else {
                arr.set(k++, rightArr.get(j++));
            }
            movements++;
        }
        while (i < len1) {
            arr.set(k++, leftArr.get(i++));
            movements++;
        }
        while (j < len2) {
            arr.set(k++, rightArr.get(j++));
            movements++;
        }
    }

    private static void insertionSort(List<Integer> arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= left && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
                movements++;
                comparisons++;
            }
            arr.set(j + 1, key);
            movements++;
        }
    }
}
