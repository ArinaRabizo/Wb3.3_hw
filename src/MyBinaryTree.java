public class MyBinaryTree<E> {
    private int size = 0;
    private Node root = null;

    private class Node {
        public E element;
        public Node left = null;
        public Node right = null;

        public Node (E element) {
            this.element = element;
        }
    }

    public E put(E element) {
        if (root == null) {
            root = new Node(element);
            size++;
            return null;
        }
        return putHelper(root,element);
    }

    private Node findNode(Object target) {
        Comparable<? super E> e = (Comparable<? super E>) target;
        Node node = root;
        while (node != null) {
            int cmp = e.compareTo(node.element);
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp == 0) return node;
        }
        return null;
    }
    private Node findParent(Object target) {
        Comparable<? super E> t = (Comparable<? super E>) target;
        Node node = root;
        Node parent = root;
        while (node != null) {
            int cmp = t.compareTo(node.element);
            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            if (cmp == 0)
            {
                return parent;
            }
        }
        return root;
    }



    private E putHelper(Node node,E element) {
        Comparable<? super E> k = (Comparable<? super E>) element;
        int cmp = k.compareTo(node.element);
        if (cmp <= 0) {
            if (node.left == null) {
                node.left = new Node(element);
                size++;
                return null;
            }
            return putHelper(node.left, element);
        } else {
            if (node.right == null) {
                node.right = new Node(element);
                size++;
                return null;
            }
            return putHelper(node.right, element);
        }
    }

    public E remove(Object element) {

        if (element == root.element) root = delRecursive(element);
        else delRecursive(element);
        return (E) element;
    }

    private Node delRecursive(Object element) {
        Node node = findNode(element);
        Node parent = findParent(element);
        if (node == null) throw new NullPointerException();
        if (node.left == null && node.right == null) {
            if (parent.left == node) parent.left = null;
            if (parent.right == node) parent.right = null;
            size--;
            return parent;
        }
        if (node.right == null) {
            if (parent.left == node) parent.left = node.left;
            if (parent.right == node) parent.right = node.left;
            size--;
            return parent;
        }
        if (node.left == null) {
            if (parent.left == node) parent.left = node.right;
            if (parent.right == node) parent.right = node.right;
            size--;
            return parent;
        }
        Node tempNode = findSmallestValue(node.right);
        delRecursive(tempNode.element);
        node.element = tempNode.element;
        return parent;
    }

    private Node findSmallestValue(Node root) {
        if (root.left == null) {
            return root;
        } else {
            return findSmallestValue(root.left);
        }
    }

    public void printTree() {
        Node node = root;
        recPrintTree(node);
        System.out.println("____________");
    }
    private void recPrintTree(Node node) {
        if (node.left != null) recPrintTree(node.left);
        System.out.println(node.element);
        if (node.right != null) recPrintTree(node.right);
    }
}

