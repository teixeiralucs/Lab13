package tree;

import estrut.Tree;

public class BinarySearchTree implements Tree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElemento(root, valor);
    }

    private boolean buscaElemento(Node node, int valor) {
        if (node == null)
            return false;
        if (valor == node.valor)
            return true;
        if (valor < node.valor)
            return buscaElemento(node.left, valor);
        else
            return buscaElemento(node.right, valor);
    }

    @Override
    public int minimo() {
        if (root == null)
            throw new IllegalStateException("Árvore vazia");
        return minimo(root);
    }

    private int minimo(Node node) {
        if (node.left == null)
            return node.valor;
        return minimo(node.left);
    }

    @Override
    public int maximo() {
        if (root == null)
            throw new IllegalStateException("Árvore vazia");
        return maximo(root);
    }

    private int maximo(Node node) {
        if (node.right == null)
            return node.valor;
        return maximo(node.right);
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElemento(root, valor);
    }

    private Node insereElemento(Node node, int valor) {
        if (node == null)
            return new Node(valor);
        if (valor < node.valor)
            node.left = insereElemento(node.left, valor);
        else if (valor > node.valor)
            node.right = insereElemento(node.right, valor);
        return node;
    }

    @Override
    public void remove(int valor) {
        root = remove(root, valor);
    }

    private Node remove(Node node, int valor) {
        if (node == null)
            return null;
        if (valor < node.valor)
            node.left = remove(node.left, valor);
        else if (valor > node.valor)
            node.right = remove(node.right, valor);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            node.valor = minimo(node.right);
            node.right = remove(node.right, node.valor);
        }
        return node;
    }

    @Override
    public int[] preOrdem() {
        return preOrdem(root);
    }

    private int[] preOrdem(Node node) {
        if (node == null)
            return new int[]{};
        int[] left = preOrdem(node.left);
        int[] right = preOrdem(node.right);
        int[] result = new int[left.length + right.length + 1];
        result[0] = node.valor;
        System.arraycopy(left, 0, result, 1, left.length);
        System.arraycopy(right, 0, result, left.length + 1, right.length);
        return result;
    }

    @Override
    public int[] emOrdem() {
        return emOrdem(root);
    }

    private int[] emOrdem(Node node) {
        if (node == null)
            return new int[]{};
        int[] left = emOrdem(node.left);
        int[] right = emOrdem(node.right);
        int[] result = new int[left.length + right.length + 1];
        System.arraycopy(left, 0, result, 0, left.length);
        result[left.length] = node.valor;
        System.arraycopy(right, 0, result, left.length + 1, right.length);
        return result;
    }

    @Override
    public int[] posOrdem() {
        return posOrdem(root);
    }

    private int[] posOrdem(Node node) {
        if (node == null)
            return new int[]{};
        int[] left = posOrdem(node.left);
        int[] right = posOrdem(node.right);
        int[] result = new int[left.length + right.length + 1];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(right, 0, result, left.length, right.length);
        result[left.length + right.length] = node.valor;
        return result;
    }

    private static class Node {
        int valor;
        Node left;
        Node right;

        Node(int valor) {
            this.valor = valor;
            left = null;
            right = null;
        }
    }
}