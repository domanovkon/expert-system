package com.company;


import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;


public class Main {

    private Stack<Node> openNodesList = new Stack<>();
    private List<Node> closedNodesList = new ArrayList<>();
    private int flagYes = 1;
    private int flagNo = 1;
    private Node end;
    private Node start;

    private ArrayDeque<Node> openNodesListBFS = new ArrayDeque<>();
    private List<Node> closedNodesListBFS = new ArrayList<>();


    static <E, V> DefaultWeightedEdge addEdge(Graph<V, E> graph, V var1, V var2, int weight) {
        DefaultWeightedEdge e1 = (DefaultWeightedEdge) graph.addEdge(var1, var2);
        graph.setEdgeWeight((E) e1, weight);
        return e1;
    }

    public static void main(String[] args) {
        DefaultDirectedWeightedGraph<Node, MyEdge> g = new DefaultDirectedWeightedGraph<>(MyEdge.class);

        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        g.addVertex(node0);
        g.addVertex(node1);
        g.addVertex(node2);
        g.addVertex(node3);
        g.addVertex(node4);
        g.addVertex(node5);
        g.addVertex(node6);
        g.addVertex(node7);
        g.addVertex(node8);

        addEdge(g, node0, node4, 104);
        addEdge(g, node0, node1, 101);
        addEdge(g, node2, node6, 103);
        addEdge(g, node2, node3, 105);
        addEdge(g, node4, node5, 106);
        addEdge(g, node4, node3, 107);
        addEdge(g, node1, node2, 102);
        addEdge(g, node6, node8, 116);
        addEdge(g, node5, node7, 111);
        addEdge(g, node7, node8, 112);

        System.out.println("Начальный граф:");
        g.edgeSet().forEach(x -> System.out.print(x.toString()));

//        DijkstraShortestPath<Node, MyEdge> dijkstraAlg = new DijkstraShortestPath<>(g);
//        System.out.println("Shortest path:");
//        ShortestPathAlgorithm000.SingleSourcePaths<Node, MyEdge> cPaths = dijkstraAlg.getPaths(node0);
//        System.out.println(cPaths.getPath(node6));

        System.out.println();
        new Main().DFS(g, node0, node7);
        new Main().BFS(g, node0, node7);
    }

//    zrusakova@bmstu.ru

    /*
    поиск в глубину
    */
    public void DFS(DefaultDirectedWeightedGraph<Node, MyEdge> g, Node start, Node end) {
        System.out.println("Метод поиска в глубину");
        System.out.println("Путь между " + start.toString() + " и " + end.toString() + ":");
        this.start = start;
        this.end = end;
        openNodesList.push(this.start);
        int j;
        while (this.flagYes == 1 && this.flagNo == 1) {
            j = patternSearch(g);
            if (this.flagYes == 0) {
                for (Node node : openNodesList) {
                    System.out.print(node.toString() + " ");
                }
                break;
            } else if (this.flagYes == 1 && j == 0 && !openNodesList.isEmpty()) {
                closedNodesList.add(openNodesList.pop());
            } else if (j == 0 && (openNodesList.isEmpty() || openNodesList.contains(this.start))) {
                this.flagNo = 0;
                System.out.println("Решения нет");
                break;
            }
        }
    }

    /*
    метод поиска по образцу
    */
    public int patternSearch(DefaultDirectedWeightedGraph<Node, MyEdge> g) {
        for (MyEdge edge : g.edgeSet()) {
            if (openNodesList.size() > 0 && (g.getEdgeSource(edge).equals(openNodesList.peek()) && edge.getMetka() == 0) && g.getEdgeTarget(edge).equals(end)) {
                this.flagYes = 0;
                openNodesList.push(end);
                return 1;
            } else if (openNodesList.size() > 0 && (g.getEdgeSource(edge).equals(openNodesList.peek()) && edge.getMetka() == 0) && !g.getEdgeTarget(edge).equals(end)) {
                openNodesList.push(g.getEdgeTarget(edge));
                edge.setMetka(1);
                return 1;
            }
        }
        return 0;
    }


    /*
    метод потомки для поиска в ширину
    */
    public int potomki(DefaultDirectedWeightedGraph<Node, MyEdge> g) {
        int count = 0;
        for (MyEdge edge : g.edgeSet()) {
            if (openNodesListBFS.size() > 0 && (g.getEdgeSource(edge).equals(openNodesListBFS.peekFirst())) && g.getEdgeTarget(edge).equals(end)) {
                this.flagYes = 0;
                count++;
                break;
            } else if (openNodesListBFS.size() > 0 && edge.getMetka() == 0 && (g.getEdgeSource(edge).equals(openNodesListBFS.peekFirst()))) {
//                if (!closedNodesListBFS.contains(g.getEdgeTarget(edge))) {
                    Node node = g.getEdgeTarget(edge);
                    node.setPrev(openNodesListBFS.peekFirst());

                    openNodesListBFS.addLast(node);
                    edge.setMetka(1);
                    count++;
//                }
            }
        }
        return count;
    }


    /*
   поиск в ширину
   */
    public void BFS(DefaultDirectedWeightedGraph<Node, MyEdge> g, Node start, Node end) {
        for (MyEdge edge : g.edgeSet()) {
            edge.setMetka(0);
        }
        this.flagYes = 1;
        this.flagNo = 1;
        System.out.println("\nМетод поиска в ширину");
        System.out.println("Путь между " + start.toString() + " и " + end.toString() + ":");
        this.start = start;
        this.end = end;
        openNodesListBFS.push(start);
        int j;
        while (!openNodesListBFS.isEmpty() && this.flagYes == 1 && this.flagNo == 1) {
            j = potomki(g);
//            Node node = openNodesListBFS.removeFirst();
//            node.setPrev(openNodesListBFS.getLast());
            closedNodesListBFS.add(openNodesListBFS.removeFirst());
            if (this.flagYes == 0) {
                closedNodesListBFS.add(end);
                closedNodesListBFS.get(closedNodesListBFS.size()-1).setPrev(closedNodesListBFS.get(closedNodesListBFS.size()-2));
//                closedNodesListBFS.get(closedNodesListBFS.size()-1).setPrev(clo);
                this.flagYes = 1;
                List<Node> nodeList = getWay();
                for (Node node1 : nodeList) {
//                    if (isChildClosed(node1, g)) {
                        System.out.print(node1.toString() + " ");
//                    }
                }
                break;
            } else if (j == 0 && (openNodesListBFS.size() == 0 || (openNodesListBFS.size() == 1 && openNodesListBFS.peek().equals(start)))) {
                this.flagNo = 0;
                System.out.println("Решений нет");
                break;
            }
        }
    }

//    public boolean isChildClosed(Node node, DefaultDirectedWeightedGraph<Node, MyEdge> g) {
//        for (MyEdge edge : g.edgeSet()) {
//            if (g.getEdgeSource(edge).equals(node) && closedNodesListBFS.contains(g.getEdgeTarget(edge))) {
//                return true;
//            }
//        }
//        return false;
//    }



    public List<Node> getWay() {
        List<Node> nodes = new ArrayList<>();
        Node temp = closedNodesListBFS.get(closedNodesListBFS.size()-1);
        nodes.add(temp);
        while (temp.getPrev() != null) {
            temp = temp.getPrev();
            nodes.add(temp);
        }
        Collections.reverse(nodes);
        return nodes;
    }

}
