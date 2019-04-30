package me.sunderhill.binarytrees;

public class BTree<T extends Comparable<T>> {
    // The <T extends Comparable<T>> "is a Type Bound" which restricts the type parameter T to be a
    // type that implements the interface Comparable<T>, guaranteeing that the call to compareTo()
    // is valid. ( http://courses.cs.vt.edu/~cs3114/Summer10/Notes/JavaGenerics.pdf pages 5 and 6 )
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
            addTo(root, value);
        }
    }
    // Recusive add algorithm
    public void addTo(BTreeNode<T> node, T value)
    {
        // Case 1: Value is less than the current node value
        if(value.compareTo(node.getValue()) < 0)
        {
            //If there is no left child, make this the new left node...
            if (node.getLeft() == null)
            {
                node.setLeft(new BTreeNode<T>(value));
            }
            // ...but if there is a left child then add it to the left node
            else
            {
                addTo(node.getLeft(), value);
            }
        }
        // Case 2: Value is equal to or greater than the current value
        else
        {
            // If there is no right child, add it to the right node...
            if(node.getRight() == null )
            {
                node.setRight(new BTreeNode<T>(value));
            }
            //...but if there is a right child then add it to the right node
            else
            {
                addTo(node.getRight(),value);
            }
        }
    }
}
