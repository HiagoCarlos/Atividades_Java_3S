import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Atividade {

    static class TreeNode {
        int key;
        TreeNode left, right;

        TreeNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public static TreeNode rightRotate(TreeNode node) {
        if (node == null || node.left == null) return node; // Verifica se o nó ou seu filho à esquerda são nulos

        TreeNode left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    public static TreeNode dswToList(TreeNode node) {
        if (node == null) return null;

        TreeNode root = node;
        while (root.left != null) {
            root = rightRotate(root);
        }
        if (root.right != null) {
            root.right = dswToList(root.right);
        }
        return root;
    }

    public static TreeNode listToBalancedTree(TreeNode node, int numNodes) {
        if (numNodes == 0 || node == null) return null;

        TreeNode root = node;
        // Realizar a rotação à direita conforme necessário
        for (int i = 0; i < numNodes / 2; i++) {
            if (root == null) break; // Verifica se o nó é nulo
            root = rightRotate(root);
        }
        if (root != null) {
            root.left = listToBalancedTree(root.left, numNodes / 2);
            root.right = listToBalancedTree(root.right, numNodes - numNodes / 2 - 1);
        }
        return root;
    }

    public static TreeNode dswBalanceTree(TreeNode root) {
        root = dswToList(root);
        int numNodes = countNodes(root);
        return listToBalancedTree(root, numNodes);
    }

    public static int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static TreeNode insertIntoTree(TreeNode root, int key) {
        if (root == null) return new TreeNode(key);
        if (key < root.key) root.left = insertIntoTree(root.left, key);
        else root.right = insertIntoTree(root.right, key);
        return root;
    }

    public static void printInOrder(TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }

    // Método para criar uma árvore com números aleatórios
    public static TreeNode createRandomTree(int numNodes) {
        TreeNode root = null;
        Random rand = new Random();
        for (int i = 0; i < numNodes; i++) {
            int key = rand.nextInt(101); // Gera números aleatórios entre 0 e 100
            root = insertIntoTree(root, key);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root;

        // 1. Crie uma árvore com 100 números aleatórios entre 0 e 100
        root = createRandomTree(100);

        System.out.println("Árvore antes do balanceamento:");
        printInOrder(root);
        System.out.println();

        // 2. Adicione 20 números à árvore
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int key = rand.nextInt(101); // Gera números aleatórios entre 0 e 100
            root = insertIntoTree(root, key);
        }

        System.out.println("Árvore após a adição de 20 números:");
        printInOrder(root);
        System.out.println();

        // 3. Balanceie a árvore usando o algoritmo DSW
        root = dswBalanceTree(root);

        System.out.println("Árvore após o balanceamento:");
        printInOrder(root);
    }
}
