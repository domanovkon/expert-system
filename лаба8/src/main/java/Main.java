import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Constant N = new Constant("N");
        Constant M1 = new Constant("M1");
        Constant W = new Constant("W");
        Constant A1 = new Constant("A1");

        Node node1 = new Node("O", 1, Arrays.asList(N, M1));
        Node node2 = new Node("M", 1, Arrays.asList(M1));
        Node node3 = new Node("A", 1, Arrays.asList(W));
        Node node4 = new Node("E", 1, Arrays.asList(N, A1));


        Variable x = new Variable();
        x.setName("x");
        Variable y = new Variable();
        y.setName("y");
        Variable z = new Variable();
        z.setName("z");
        Variable x2 = new Variable();
        x2.setName("x2");
        Variable x3 = new Variable();
        x3.setName("x3");
        Variable x4 = new Variable();
        x4.setName("x4");

        Node node5 = new Node("A", 0, Arrays.asList(x));
        Node node6 = new Node("W", 0, Arrays.asList(y));
        Node node7 = new Node("S", 0, Arrays.asList(x, y, z));
        Node node8 = new Node("H", 0, Arrays.asList(z));
        Node node9 = new Node("C", 0, Arrays.asList(x));
        Node node10 = new Node("M", 0, Arrays.asList(x2));
        Node node11 = new Node("O", 0, Arrays.asList(N, x2));
        Node node12 = new Node("S", 0, Arrays.asList(W, x2, N));
        Node node13 = new Node("M", 0, Arrays.asList(x3));
        Node node14 = new Node("W", 0, Arrays.asList(x3));
        Node node15 = new Node("E", 0, Arrays.asList(x4, A1));
        Node node16 = new Node("H", 0, Arrays.asList(x4));

        Edge m1 = new Edge("m1", node9, new ArrayList<>(Arrays.asList(node5, node6, node7, node8)));
        Edge m2 = new Edge("m2", node12, new ArrayList<>(Arrays.asList(node10, node11)));
        Edge m3 = new Edge("m3", node14, new ArrayList<>(Arrays.asList(node13)));
        Edge m4 = new Edge("m4", node16, new ArrayList<>(Arrays.asList(node15)));

        Node target = new Node("C", 0, Arrays.asList(W));


        List<Node> closedNodes = Arrays.asList(node1, node2, node3, node4);
        List<Edge> edgeList = new ArrayList<>(Arrays.asList(m1, m2, m3, m4));
        List<Edge> closedEdges = new ArrayList<>();
        Stack<Edge> openEdges = new Stack<>();

        Search search = new Search(closedNodes, closedEdges, openEdges, edgeList, target);
        search.search();


    }
}
