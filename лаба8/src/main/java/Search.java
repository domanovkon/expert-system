import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Search {

    private List<Node> closedNodes = new ArrayList<>();

    private List<Edge> closedEdges = new ArrayList<>();

    private Stack<Node> openNodes = new Stack<>();

    private Stack<Edge> openEdges = new Stack<>();

    private List<Edge> edgeList = new ArrayList<>();

    private Unification unification = new Unification();

    private Node target;

    private int flagY = 1;

    private int flagN = 1;

    public Search(List<Node> closedNodes, List<Edge> closedEdges, Stack<Edge> openEdges, List<Edge> edgeList, Node target) {
        this.closedNodes.addAll(closedNodes);
        this.closedEdges.addAll(closedEdges);
        this.openEdges.addAll(openEdges);
        this.edgeList.addAll(edgeList);
        this.target = target;
        this.openNodes.add(target);
    }

    public void search() {
        while (this.flagY == 1 && this.flagN == 1) {
            int k = 0;
            for (Edge edge : this.edgeList) {
                if (edge.getMetka() == 0) {
                    if (edge.getFinalNode().getName().equals(this.openNodes.peek().getName()) &&
                            edge.getFinalNode().getArgs().size() == this.openNodes.peek().getArgs().size()) {
                        this.edgePrint(edge);
                        if (this.unification.unification(edge.getFinalNode(), this.openNodes.peek())) {
                            int temp = 0;
                            for (int i = edge.getInputNodes().size() - 1; i >= 0; i--) {
                                if (this.isContainsInFacts(edge.getInputNodes().get(i))) {
                                    edge.getInputNodes().get(i).setFlag(1);
                                    temp++;
                                } else {
                                    this.openNodes.add(edge.getInputNodes().get(i));
                                }
                            }
                            if (temp == edge.getInputNodes().size()) {
                                edge.setMetka(1);
                                this.closedNodes.add(edge.getFinalNode());
                                this.closedEdges.add(edge);
                                if (this.openNodes.peek().equals(target)) {
                                    this.flagY = 0;
                                }
                                this.openNodes.pop();
                                k++;
                            }
                            this.edgePrint(edge);
                            System.out.println("\n============================================================");
                        }
                    }
                }
            }
            if (k == 0) {
                this.flagN = 0;
                System.out.println("Решения нет");
            }
            if (this.flagY == 0) {
                System.out.println("Решение найдено");
            }
        }
    }

    public boolean isContainsInFacts(Node checkNode) {
        for (Node node : this.closedNodes) {
            if (checkNode.getName().equals(node.getName()) && checkNode.getArgs().size() == node.getArgs().size()) {
                if (unification.unification(node, checkNode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void edgePrint(Edge edge) {
        System.out.println("\n   metka = " + edge.getMetka());
        for (Node node : edge.getInputNodes()) {
            System.out.print("   " + node.getName() + " (");
            for (ParamType pt : node.getArgs()) {
                if (pt.isConstant()) {
                    Constant c = (Constant) pt;
                    System.out.print(c.getValue());
                } else {
                    Variable v = (Variable) pt;
                    if (v.getConstant() != null) {
                        System.out.print(" " + v.getName() + " value = " + v.getConstant().getValue() + " ");
                    } else {
                        System.out.print(" " + v.getName() + " value = null " );
                    }
                }
            }
            System.out.print(")");
        }
        System.out.print("  --->  " + edge.getFinalNode().getName() + " (");
        for (ParamType pt : edge.getFinalNode().getArgs()) {
            if (pt.isConstant()) {
                Constant c = (Constant) pt;
                System.out.print(c.getValue());
            } else {
                Variable v = (Variable) pt;
                if (v.getConstant() != null) {
                    System.out.print(" " + v.getName() + " value = " + v.getConstant().getValue() + " ");
                } else {
                    System.out.print(" " + v.getName() + " value = null " );
                }
            }
        }
        System.out.print(")");
    }

    public void printClosedNodes() {
        System.out.println("Список закрытых вершин:");
        for (Node n : this.closedNodes) {
            System.out.print("\n" + n.getName() + " ( ");
            for (ParamType pt : n.getArgs()) {
                if (pt.isConstant()) {
                    Constant c = (Constant) pt;
                    System.out.print(c.getValue());
                } else {
                    Variable v = (Variable) pt;
                    System.out.print(v.getConstant().getValue());
                }
                System.out.print(" ");
            }
            System.out.print(")");
        }
    }

//    public boolean isContainsInFacts(Node checkNode) {
//        for (Node node : this.closedNodes) {
//            if (checkNode.getName().equals(node.getName())) {
//                int similarity = 0;
//                for (int i = 0; i < checkNode.getArgs().size(); i++) {
//                    Constant nodeArg = (Constant) node.getArgs().get(i);
//                    if (checkNode.getArgs().get(i) != null) {
//                        if (checkNode.getArgs().get(i).isVariable()) {
//                            Variable var = (Variable) checkNode.getArgs().get(i);
//                            if (var.getConstant() != null && var.getConstant().getValue().equals(nodeArg.getValue())) {
//                                similarity++;
//                            }
//                            continue;
//                        }
//                        Constant const1 = (Constant) checkNode.getArgs().get(i);
//                        if (nodeArg.getValue().equals(const1.getValue())) {
//                            similarity++;
//                        }
//                    }
//                }
//                if (similarity == checkNode.getArgs().size()) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
