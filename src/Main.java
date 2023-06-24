public class Main {
    public static void main(String[] args) {
        MyBinaryTree<Integer> ints = new MyBinaryTree<>();
        ints.put(8);
        ints.put(4);
        ints.put(17);
        ints.put(32);
        ints.put(5);
        ints.remove(32);
        ints.remove(8);
        ints.printTree();

    }
}