package me.sunderhill.binarytrees;

public class BTreeNode<T extends Comparable<T>> {
    private T value;
    private BTreeNode<T> left;
    private BTreeNode<T> right;

    public BTreeNode(T value) {
        this.value = value;
    }

    public void add(BTreeNode n) {

    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(BTreeNode<T> left) {
        this.left = left;
    }

    public void setRight(BTreeNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public BTreeNode<T> getLeft() {
        return left;
    }

    public BTreeNode<T> getRight() {
        return right;
    }
}
