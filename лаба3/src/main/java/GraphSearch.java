import java.util.*;

public class GraphSearch {


    private List<Edge> edgeList = new ArrayList<>();

    private Node targetNode;

    private List<Node> defaultNodes = new ArrayList<>();

    private int flagY = 1;

    private int flagN = 1;

    private Stack<Node> openNodes = new Stack<>();

    private List<Node> closedNodes = new ArrayList<>();

    private List<Node> forbiddenNodes = new ArrayList<>();

    private List<Node> allNodes = new ArrayList<>();

    private Stack<Edge> openEdges = new Stack<>();

    private List<Edge> closedEdges = new ArrayList<>();

    private List<Edge> forbiddenEdges = new ArrayList<>();

    public GraphSearch(List<Edge> edgeList, Node targetNode, List<Node> defaultNodes) {
        this.edgeList.addAll(edgeList);
        this.targetNode = targetNode;
        this.defaultNodes.addAll(defaultNodes);
        this.openNodes.add(targetNode);
        this.closedNodes.addAll(defaultNodes);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        Node node15 = new Node(15);
        Node node16 = new Node(16);
        Node node17 = new Node(17);
        Node node18 = new Node(18);
        Node node19 = new Node(19);
        Node node20 = new Node(20);
        Node node31 = new Node(31);
        Node node32 = new Node(32);
        Node node33 = new Node(33);

        List<Node> allNodes = Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9,
                node10, node11, node12, node13, node14, node15, node16, node17, node18, node19, node20,
                node31, node32, node33);

        Edge edge1 = new Edge(104, node3, Arrays.asList(node8, node31));
        Edge edge2 = new Edge(101, node3, Arrays.asList(node1, node2));
        Edge edge3 = new Edge(102, node7, Arrays.asList(node3, node2, node4));
        Edge edge4 = new Edge(103, node4, Arrays.asList(node5, node6));
        Edge edge5 = new Edge(107, node11, Arrays.asList(node12, node13));
        Edge edge6 = new Edge(106, node9, Arrays.asList(node4, node10, node11));
        Edge edge7 = new Edge(105, node14, Arrays.asList(node7, node9));
        Edge edge8 = new Edge(111, node9, Arrays.asList(node18, node32));
//        Edge edge8 = new Edge(110, node14, Arrays.asList(node9, node18));
        Edge edge10 = new Edge(112, node18, Arrays.asList(node19, node20));
        Edge edge11 = new Edge(109, node15, Arrays.asList(node16, node17));
        Edge edge12 = new Edge(108, node33, Arrays.asList(node15, node18));


        List<Edge> edges = Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge10, edge11, edge12);

        List<Node> defNodes = Arrays.asList(node8, node31, node2, node5, node6, node18, node32);
//        List<Node> defNodes = Arrays.asList(node19, node20, node6);


        GraphSearch graphSearch = new GraphSearch(edges, node14, defNodes);

        graphSearch.search();
    }


    public void search() {
        int j;
        while (this.flagY == 1 && this.flagN == 1) {
            j = patternSearch();
            if (this.flagY == 0) {
                System.out.println("Решение найдено");
                System.out.println("Список закрытых правил");
                this.closedEdges.forEach(x-> System.out.print(x.getValue() + " "));
                System.out.println("\nСписок закрытых вершин");
                this.closedNodes.forEach(x-> System.out.print(x.getValue() + " "));
            } else if (j == 0 && this.openNodes.size() == 1 && this.openNodes.contains(this.targetNode)) {
                System.out.println("Решения нет");
                this.flagN = 0;
            } else if (j == 0 && this.openNodes.size() != 0) {
                returnMethod();
            }
        }
    }

    public int patternSearch() {
        int flagEdgeSearch = 0;
        for (Edge edge : this.edgeList) {
            if (edge.getFinalNode().equals(openNodes.peek()) && edge.getMetka() == 0) {
                edge.setMetka(1);
                flagEdgeSearch = 1;
                for (Node node : edge.getInputNodes()) {
                    if (this.closedNodes.contains(node)) {
                        node.setFlag(1);
                    }
                }
                this.openEdges.add(edge);

                int l = 0;
                List<Node> nodes = edge.getInputNodes();
                Collections.reverse(nodes);
                for (Node node : nodes) {
                    if (node.getFlag() == 0) {
                        l++;
                        this.openNodes.add(node);
                    }
                }
                if (l == 0) {
                    markup();
                }
                break;
            }
        }
        return flagEdgeSearch;
    }

    public void returnMethod() {
        int f = 0;
        for (Node node : this.openEdges.peek().getInputNodes()) {
            this.openNodes.remove(node);
            if (node.getFlag() == 0 && f == 0) {
                this.forbiddenNodes.add(node);
                f++;
            }
        }
        this.openEdges.peek().setMetka(-1);
        this.forbiddenEdges.add(this.openEdges.pop());
    }

    public void markup() {
        while (this.flagY == 1) {
            this.openEdges.peek().getFinalNode().setFlag(1);
            Edge edge = this.openEdges.pop();
            if (edge.getFinalNode().equals(this.targetNode)) {
                this.flagY = 0;
            }
            this.closedEdges.add(edge);
            this.closedNodes.add(this.openNodes.pop());
//            this.openNodes.remove(edge.getFinalNode());
//            int k = 0;
//            for (Node node : edge.getInputNodes()) {
//                if (node.getFlag() == 1) {
//                    k++;
//                }
//            }
//            if (k != edge.getInputNodes().size()) {
//                break;
//            }

        }
    }
}
