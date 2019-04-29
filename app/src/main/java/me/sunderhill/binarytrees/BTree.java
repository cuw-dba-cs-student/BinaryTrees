package me.sunderhill.binarytrees;

public class BTree<T extends Comparable<T>> {
    // The <T extends Comparable<T>> "is a Type Bound" which
    // restricts the type parameter T to be a type that
    // implements the interface Comparable<T>, guaranteeing that
    // the call to compareTo() is valid.
    // see http://courses.cs.vt.edu/~cs3114/Summer10/Notes/JavaGenerics.pdf pages 5 and 6
    private BTreeNode<T> root;

    public BTree() {
        this.root = null;
    }

    public void add(T value) {
        BTreeNode n = new BTreeNode<T>(value);
        if(this.root == null) {
            // The tree is empty
            this.root = n;
        }
        else {
            // The tree has nodes in it

        }
    }
}
