import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void main(String args[]) {
        int arr[] = new int[500000];
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("dados_500mil.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    arr[i++] = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Linha inválida ignorada: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Redimensiona o array para o número real de elementos lidos
        int[] validArr = Arrays.copyOf(arr, i);

        HeapSort ob = new HeapSort();
        long startTime = System.currentTimeMillis();
        ob.sort(validArr);
        long endTime = System.currentTimeMillis();
        System.out.println("Heap Sort completou em " + (endTime - startTime) + " milliseconds");
    }
}
