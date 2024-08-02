import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int item) {
        val = item;
        left = right = null;
    }
}

class BinarySearchTree {
    TreeNode root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int val) {
        root = insertRec(root, val);
    }

    TreeNode insertRec(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        if (val < root.val)
            root.left = insertRec(root.left, val);
        else if (val > root.val)
            root.right = insertRec(root.right, val);

        return root;
    }

    void inOrderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inOrderTraversal(root.left, result);
            result.add(root.val);
            inOrderTraversal(root.right, result);
        }
    }

    List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }
}

public class ArvoreBinaria
 {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        try (BufferedReader br = new BufferedReader(new FileReader("dados_500mil.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    bst.insert(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Linha inválida ignorada: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        List<Integer> sortedList = bst.inOrder();
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Tree Sort (in-order traversal) completed in " + (endTime - startTime) + " milliseconds");
        // Para verificar a saída, você pode descomentar a linha abaixo para imprimir os primeiros 100 elementos
        // System.out.println(sortedList.subList(0, 100));
    }
}
