package com.company;

public class Node {

    private int value;

    private int flag;

    private Node prev = null;

    public Node(int value) {
        this.value = value;
        this.flag = 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
