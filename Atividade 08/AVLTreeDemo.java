import java.io.*;
import java.util.*;

class AVLTree {
    class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            left = right = null;
            height = 1;
        }
    }

    private Node root;

    // Get the height of the node
    private int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    // Get the balance factor of each node
    private int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    // Right rotate
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotate
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else if (key > node.key) {
            node.right = insertNode(node.right, key);
        } else {
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public boolean search(int key) {
        return searchNode(root, key) != null;
    }

    private Node searchNode(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (root.key < key) {
            return searchNode(root.right, key);
        }

        return searchNode(root.left, key);
    }
}

public class AVLTreeDemo {
    public static void main(String[] args) {
        try {
            File file = new File("dados100_mil.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            AVLTree avl = new AVLTree();
            String line;
            Random rand = new Random();

            // Medir o tempo de inserção na árvore AVL
            long startTime = System.currentTimeMillis();
            while ((line = br.readLine()) != null) {
                try {
                    int num = Integer.parseInt(line.trim());
                    avl.insert(num);
                } catch (NumberFormatException e) {
                    System.err.println("Linha ignorada (não é um número válido): " + line);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Tempo de inserção na Árvore AVL: " + (endTime - startTime) + " ms");

            br.close();

            // Gerar números aleatórios e realizar operações
            int[] randomKeys = new int[50000];
            for (int i = 0; i < randomKeys.length; i++) {
                randomKeys[i] = rand.nextInt(19999) - 9999; // Números entre -9999 e 9999
            }

            startTime = System.currentTimeMillis();
            for (int key : randomKeys) {
                if (key % 3 == 0) {
                    avl.insert(key);
                } else if (key % 5 == 0) {
                    avl.delete(key);
                } else {
                    // Contar a ocorrência de um número é mais complexo em AVL, pois não mantemos contadores em cada nó
                    // Para simplicidade, assumimos que o método search pode ser usado para verificar se o número está presente
                    if (avl.search(key)) {
                        // Contar ocorrências (não implementado)
                    }
                }
            }
            endTime = System.currentTimeMillis();
            System.out.println("Tempo de operações na Árvore AVL: " + (endTime - startTime) + " ms");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
