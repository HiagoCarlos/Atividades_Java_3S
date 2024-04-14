import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RadixSort {
    public static void main(String[] args) {
        List<Integer> numbers = readNumbersFromFile("dados100_mil.txt");
        System.out.println("Números antes da ordenação:");
        System.out.println(numbers);
        long startTime = System.currentTimeMillis();
        radixSort(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Números ordenados com Radix Sort:");
        System.out.println(numbers);
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execução do Radix Sort: " + formatTime(executionTime));
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

    public static void radixSort(List<Integer> arr) {
        List<Integer> negativeNumbers = new ArrayList<>();
        List<Integer> nonNegativeNumbers = new ArrayList<>();
        for (int num : arr) {
            if (num < 0) {
                negativeNumbers.add(num);
            } else {
                nonNegativeNumbers.add(num);
            }
        }

        radixSortHelper(negativeNumbers);
        radixSortHelper(nonNegativeNumbers);

        arr.clear();
        arr.addAll(negativeNumbers);
        arr.addAll(nonNegativeNumbers);
    }

    private static void radixSortHelper(List<Integer> arr) {
        if (arr.isEmpty()) {
            return;
        }

        int max = Math.abs(arr.get(0));
        for (int num : arr) {
            max = Math.max(max, Math.abs(num));
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static void countingSort(List<Integer> arr, int exp) {
        int n = arr.size();
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(Math.abs(arr.get(i)) / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(Math.abs(arr.get(i)) / exp) % 10] - 1] = arr.get(i);
            count[(Math.abs(arr.get(i)) / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            arr.set(i, output[i]);
        }
    }

    private static String formatTime(long millis) {
        long milliseconds = millis % 1000;
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d:%d", hours, minutes, seconds, milliseconds);
    }
}
