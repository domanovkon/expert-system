package com.company;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphSearch {


    private List<Edge> edgeList = new ArrayList<>();

    private Node targetNode;

    private List<Node> defaultNodes = new ArrayList<>();
    private int flagY = 1;
    private int flagN = 1;

    private List<Node> openNodes = new ArrayList<>();

    private List<Node> closedNodes = new ArrayList<>();

    private List<Node> allNodes = new ArrayList<>();

    private List<Edge> openEdges = new ArrayList<>();

    private List<Edge> closedEdges = new ArrayList<>();

    public GraphSearch(List<Edge> edgeList, Node targetNode, List<Node> defaultNodes, List<Node> allNodes) {
        this.edgeList = edgeList;
        this.targetNode = targetNode;
        this.defaultNodes.addAll(defaultNodes);
        this.closedNodes.addAll(defaultNodes);
        this.allNodes.addAll(allNodes);
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
        Node node33 = new Node(33);

        List<Node> allNodes = Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9,
                node10, node11, node12, node13, node14, node15, node16, node17, node18, node19, node20,
                node31, node33);

        Edge edge1 = new Edge(104, node3, Arrays.asList(node8, node31));
        Edge edge2 = new Edge(101, node3, Arrays.asList(node1, node2));
        Edge edge3 = new Edge(107, node7, Arrays.asList(node3, node2, node4));
        Edge edge4 = new Edge(103, node4, Arrays.asList(node5, node6));
        Edge edge5 = new Edge(107, node11, Arrays.asList(node12, node13));
        Edge edge6 = new Edge(106, node9, Arrays.asList(node4, node10, node11));
        Edge edge7 = new Edge(105, node14, Arrays.asList(node7, node9));
        Edge edge8 = new Edge(110, node14, Arrays.asList(node9, node18));
        Edge edge10 = new Edge(112, node18, Arrays.asList(node19, node20));
        Edge edge11 = new Edge(109, node15, Arrays.asList(node16, node17));
        Edge edge12 = new Edge(108, node33, Arrays.asList(node15, node18));


        List<Edge> edges = Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge10, edge11, edge12);
        List<Node> start = Arrays.asList(node19, node20, node12, node13, node10, node5, node6);

        GraphSearch graphSearch = new GraphSearch(edges, node14, start, allNodes);

        graphSearch.printGraph(true);
        graphSearch.BFS();
        graphSearch.printGraph(false);
    }

    public void printGraph(boolean initialDraw) {
        DirectedSparseGraph<String, String> gr = new DirectedSparseGraph<>();
        String title = "Исходный граф";
        if (initialDraw) {
            for (Node node : this.allNodes) {
                gr.addVertex(String.valueOf(node));
            }
            for (Edge edge : this.edgeList) {
                for (Node node : edge.getInputNodes()) {
                    gr.addEdge(String.valueOf(gr), String.valueOf(node), String.valueOf(edge.getFinalNode()));
                }
            }
        } else {
            for (Node node : this.closedNodes) {
                gr.addVertex(String.valueOf(node));
            }
            for (Edge edge : this.openEdges) {
                for (Node node : edge.getInputNodes()) {
                    gr.addEdge(String.valueOf(gr), String.valueOf(node), String.valueOf(edge.getFinalNode()));
                }
            }
            title = "Полученное дерево";
            if (this.flagN == 0) {
                title += ": решения нет";
            } else {
                title += ": цель " + this.targetNode.toString() + " достигнута";
            }
        }

        VisualizationViewer<String, String> vs =
                new VisualizationViewer<>(new ISOMLayout<>(gr), new Dimension(800, 800));
        JFrame frame = new JFrame(title);
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void BFS() {
        System.out.println("Список начальных вершин:");
        defaultNodes.forEach(x-> System.out.print(x.toString() + " "));
        System.out.println("\nЦелевая вершина: " + this.targetNode);
        int j;
        while (this.flagY == 1 && this.flagN == 1) {
            j = patternSearch();
            if (this.flagY == 0) {
                System.out.println("Решение найдено");
                break;
            } else if (j == 0) {
                this.flagN = 0;
                System.out.println("Нет решения");
            }
        }
    }

    public int patternSearch() {
        int count = 0;
        for (Edge edge : this.edgeList) {
            if (edge.getMetka() == 0) {
                int temp = 0;
                for (Node inputNode : edge.getInputNodes()) {
                    if (this.closedNodes.contains(inputNode)) {
                        inputNode.setFlag(1);
                        temp++;
                    }
                }
                if (edge.getInputNodes().size() == temp) {
                    count++;
                    edge.setMetka(1);
                    edge.getFinalNode().setFlag(1);
                    this.closedNodes.add(edge.getFinalNode());
                    this.openEdges.add(edge);
                    if (edge.getFinalNode().equals(this.targetNode)) {
                        this.flagY = 0;
                        break;
                    }
                }
            }
        }
        return count;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(Node targetNode) {
        this.targetNode = targetNode;
    }

    public List<Node> getDefaultNodes() {
        return defaultNodes;
    }

    public void setDefaultNodes(List<Node> defaultNodes) {
        this.defaultNodes = defaultNodes;
    }

    public int getFlagY() {
        return flagY;
    }

    public void setFlagY(int flagY) {
        this.flagY = flagY;
    }

    public int getFlagN() {
        return flagN;
    }

    public void setFlagN(int flagN) {
        this.flagN = flagN;
    }
}
