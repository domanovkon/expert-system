public class Node {

    private int value;


    /*
    0 - вершина нераскрыта / не доказана
    1 - вершина закрыта / доказана
     */
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
