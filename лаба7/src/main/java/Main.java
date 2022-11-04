import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Constant N = new Constant("N");
        Constant M1 = new Constant("M1");
        Constant W = new Constant("W");
        Constant A1 = new Constant("A1");

        Node node1 = new Node("O", 1, Arrays.asList(N, M1));
        Node node2 = new Node("M", 1, Arrays.asList(M1));
        Node node3 = new Node("A", 1, Arrays.asList(W));
        Node node4 = new Node("E", 1, Arrays.asList(M1, A1));

        List<Node> closedNodes = new ArrayList<>(Arrays.asList(node1, node2, node3, node4));

        Variable x1 = new Variable();
        x1.setName("x1");
        Variable y1 = new Variable();
        y1.setName("y1");
        Variable z1 = new Variable();
        z1.setName("z1");

        Node node11 = new Node("A", 0, Arrays.asList(x1));
        Node node12 = new Node("W", 0, Arrays.asList(y1));
        Node node13 = new Node("S", 0, Arrays.asList(x1, y1, z1));
        Node node14 = new Node("H", 0, Arrays.asList(z1));
        Node node15 = new Node("C", 0, Arrays.asList(x1));

        Edge edge1 = new Edge(1, node15, new ArrayList<>(Arrays.asList(node11, node12, node13, node14)));

        Variable x2 = new Variable();
        x2.setName("x2");

        Node node21 = new Node("M", 0, Arrays.asList(x2));
        Node node22 = new Node("O", 0, Arrays.asList(N, x2));
        Node node23 = new Node("S", 0, Arrays.asList(W, x2, M1));

        Edge edge2 = new Edge(2, node23, new ArrayList<>(Arrays.asList(node21, node22)));

        Variable x3 = new Variable();
        x3.setName("x3");

        Node node31 = new Node("M", 0, Arrays.asList(x3));
        Node node32 = new Node("W", 0, Arrays.asList(x3));

        Edge edge3 = new Edge(3, node32, new ArrayList<>(Arrays.asList(node31)));

        Variable x4 = new Variable();
        x4.setName("x4");

        Node node41 = new Node("E", 0, Arrays.asList(x4, A1));
        Node node42 = new Node("H", 0, Arrays.asList(x4));

        Edge edge4 = new Edge(4, node42, new ArrayList<>(Arrays.asList(node41)));


        List<Edge> edgeList = new ArrayList<>(Arrays.asList(edge1, edge2, edge3, edge4));

        Node targetNode = new Node("C",0, Arrays.asList(W));

        BFS bfs = new BFS(closedNodes, edgeList, targetNode);
        bfs.search();
    }
}
