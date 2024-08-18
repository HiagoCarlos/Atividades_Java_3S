import java.util.Random;

class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

class AVLTree {

    Node root;

    // Função para obter a altura do nó
    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // Função para obter o fator de balanceamento do nó
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    // Rotação à direita
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza as alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Retorna a nova raiz
        return x;
    }

    // Rotação à esquerda
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza as alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Retorna a nova raiz
        return y;
    }

    // Insere um novo valor na árvore AVL
    Node insert(Node node, int key) {

        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Rotação simples à direita
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Rotação simples à esquerda
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Rotação dupla à direita
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Rotação dupla à esquerda
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Encontra o nó com o menor valor na árvore
    Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    // Remove um nó da árvore AVL
    Node deleteNode(Node root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);

        else if (key > root.key)
            root.right = deleteNode(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {

                Node temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Função para imprimir o valor do nó e o fator de balanceamento
    void preOrder(Node node) {
        if (node != null) {
            System.out.print("Nó: " + node.key + ", Fator de Balanceamento: " + getBalance(node) + "\n");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}

public class Atividade {
    public static void main(String[] args) {
        Random random = new Random();
        AVLTree tree = new AVLTree();

        // Inserção de 100 números aleatórios
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(1001) - 500; // Gera números de -500 a 500
            tree.root = tree.insert(tree.root, num);
        }

        // Verificação e impressão da árvore AVL
        System.out.println("Árvore AVL após inserção:");
        tree.preOrder(tree.root);

        // Remoção de 20 números aleatórios
        for (int i = 0; i < 20; i++) {
            int num = random.nextInt(1001) - 500;
            tree.root = tree.deleteNode(tree.root, num);
        }

        // Verificação e impressão da árvore AVL após remoção
        System.out.println("\nÁrvore AVL após remoção:");
        tree.preOrder(tree.root);
    }
}
