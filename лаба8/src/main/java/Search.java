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

    public Search(List<Node> closedNodes, List<Edge> closedEdges, Stack<Edge> openEdges, List<Edge> edgeList, Node target) {
        this.closedNodes.addAll(closedNodes);
        this.closedEdges.addAll(closedEdges);
        this.openEdges.addAll(openEdges);
        this.edgeList.addAll(edgeList);
        this.target = target;
        this.openNodes.add(target);
    }

    public void search() {
        for (Edge edge : this.edgeList) {
            if (edge.getMetka() == 0) {
                if (edge.getFinalNode().getName().equals(this.openNodes.peek().getName()) &&
                        edge.getFinalNode().getArgs().size() == this.openNodes.peek().getArgs().size()) {
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
                            this.openNodes.pop();
                        }
//                    for (Node node : edge.getInputNodes()) {
//                        if (this.isContainsInFacts(node)) {
//                            node.setFlag(1);
//                        } else {
//                            this.openNodes.add(node);
//                        }
//                    }
                    }
                }
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

    public void patternSearch() {

    }
}
