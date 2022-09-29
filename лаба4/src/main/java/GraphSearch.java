import java.util.*;

public class GraphSearch {


    private List<Edge> edgeList = new ArrayList<>();

    private Node targetNode;

    private List<Node> defaultNodes = new ArrayList<>();

    private int flagY = 1;

    private int flagN = 1;

    private ArrayDeque<Node> openNodes = new ArrayDeque<>();

    private List<Node> closedNodes = new ArrayList<>();

    private List<Edge> openEdges = new ArrayList<>();

    private List<Edge> closedEdges = new ArrayList<>();

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

        List<Node> allNodes = Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9,
                node10, node11, node12, node13, node14, node15, node16, node17, node18, node19, node20);

        Edge edge1 = new Edge(110, node1, Arrays.asList(node3, node2, node12));
        Edge edge2 = new Edge(104, node3, Arrays.asList(node11, node5));
        Edge edge3 = new Edge(103, node3, Arrays.asList(node11, node10));
        Edge edge4 = new Edge(112, node10, Arrays.asList(node14, node16));
        Edge edge5 = new Edge(101, node4, Arrays.asList(node5, node6));
        Edge edge6 = new Edge(106, node2, Arrays.asList(node4, node7));
        Edge edge7 = new Edge(107, node2, Arrays.asList(node9, node8));
        Edge edge8 = new Edge(114, node8, Arrays.asList(node15, node17));

        List<Edge> edges = Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8);

        List<Node> defNodes = Arrays.asList(node11, node14, node16, node9, node17, node15, node12);

        GraphSearch graphSearch = new GraphSearch(edges, node1, defNodes);

        System.out.println("Исходные данные");
        for (Edge edge : edges) {
            System.out.println("\nНомер правила: " + edge.getValue() + " Выходная вершина: " + edge.getFinalNode().toString());
            System.out.println("Входные вершины: ");
            edge.getInputNodes().forEach(x-> System.out.print(x.toString() + " "));
        }
        graphSearch.search();


    }

    public void search() {
        int j;
        while (this.flagY == 1 && this.flagN == 1) {
            j = patternSearch();
            openNodes.removeFirst();
            if (this.flagY == 0) {
                System.out.println("\n\nРешение найдено");
                System.out.println("\nСписок закрытых правил");
                this.closedEdges.forEach(x -> System.out.print(x.getValue() + " "));
                System.out.println("\nСписок закрытых вершин");
                this.closedNodes.forEach(x -> System.out.print(x.getValue() + " "));
            } else if (this.openNodes.isEmpty()) {
                System.out.println("\n\nРешения нет");
                System.out.println("\nСписок открытых правил");
                this.openEdges.forEach(x -> System.out.print(x.getValue() + " "));
                System.out.println("\nСписок закрытых правил");
                this.closedEdges.forEach(x -> System.out.print(x.getValue() + " "));
                System.out.println("\nСписок закрытых вершин");
                this.closedNodes.forEach(x -> System.out.print(x.getValue() + " "));
                this.flagN = 0;
            }
        }
    }

    public int patternSearch() {
        int j = 0;
        for (Edge edge : this.edgeList) {
            if (edge.getFinalNode().equals(openNodes.getFirst()) && edge.getMetka() == 0) {
                edge.setMetka(1);
                j++;

                int l = 0;
                List<Node> nodes = edge.getInputNodes();
//                Collections.reverse(nodes);
                for (Node node : nodes) {
                    if (this.closedNodes.contains(node)) {
                        node.setFlag(1);
                        l++;
                    } else {
                        this.openNodes.addLast(node);
                    }
                }

                if (l < edge.getInputNodes().size()) {
                    openEdges.add(edge);
                } else if (l == edge.getInputNodes().size() && edge.getFinalNode().equals(this.targetNode)) {
                    this.flagY = 0;
                } else if (l == edge.getInputNodes().size()) {
                    edge.setMetka(2);
                    edge.getFinalNode().setFlag(1);
                    this.closedNodes.add(edge.getFinalNode());
                    this.closedEdges.add(edge);
                    markup();
                    break;
                }
            }
        }
        return j;
    }

    public void markup() {
    }
}
