import java.util.ArrayList;
import java.util.List;

public class Edge {

    private String value;

    private Node finalNode;

    private List<Node> inputNodes = new ArrayList<>();

    private int metka;

    public Edge(String value, Node finalNode, List<Node> inputNodes) {
        this.value = value;
        this.finalNode = finalNode;
        this.inputNodes.addAll(inputNodes);
        this.metka = 0;
    }

    public int getMetka() {
        return metka;
    }

    public void setMetka(int metka) {
        this.metka = metka;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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


    @Override
    public String toString() {
        return inputNodes.toString() + "\t->\t" + finalNode.toString();
    }
}
