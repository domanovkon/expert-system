import java.util.ArrayList;
import java.util.List;

public class Edge {

    private int value;

    private Node finalNode;

    private List<Node> inputNodes = new ArrayList<>();


    // 2 - доказано
    private int metka;

    public Edge(int value, Node finalNode, List<Node> inputNodes) {
        this.value = value;
        this.finalNode = finalNode;
        this.inputNodes = inputNodes;
        this.metka = 0;
    }

    public int getMetka() {
        return metka;
    }

    public void setMetka(int metka) {
        this.metka = metka;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public List<Node> getInputNodes() {
        return inputNodes;
    }

    public void setInputNodes(List<Node> inputNodes) {
        this.inputNodes = inputNodes;
    }
}
