import java.util.ArrayList;
import java.util.List;

public class BFS {

    private int flagY = 1;

    private int flagN = 1;

    private List<Node> closedNodes = new ArrayList<>();

    private List<Edge> edgeList = new ArrayList<>();

    private Unification unification = new Unification();

    private Node target;

    public BFS(List<Node> closedNodes, List<Edge> edgeList, Node target) {
        this.closedNodes.addAll(closedNodes);
        this.edgeList.addAll(edgeList);
        this.target = target;
    }

    public void search() {
        this.initialPrint();
        while (this.flagY == 1 && this.flagN == 1) {
            int numberOfSubstitutions = 0;
            for (Edge edge : this.edgeList) {
                if (isTargetAchieved()) {
                    break;
                }
                if (edge.getMetka() == 0) {
                    this.edgePrint(edge);
                    int k = 0;
                    for (Node node : edge.getInputNodes()) {
                        for (Node clNode : this.closedNodes) {
                            if (node.getName().equals(clNode.getName()) && node.getArgs().size() == clNode.getArgs().size()) {
                                if (this.unification.unification(node, clNode)) {
                                    k++;
                                    break;
                                }
                            }
                        }
                    }
                    if (k == edge.getInputNodes().size()) {
                        numberOfSubstitutions++;
                        edge.setMetka(1);
                        this.closedNodes.add(edge.getFinalNode());
                        System.out.println("\n   Добавим выходную вершину " + edge.getFinalNode().getName() + " в список закрытых вершин");
                    }
                    this.edgePrint(edge);
                    System.out.println("\n============================================================");
                }
            }
            if (numberOfSubstitutions == 0) {
                System.out.println("\nЦель не достигнута");
                System.out.println("Не можем применить ни один факт");
                this.printClosedNodes();
                this.flagN = 0;
            }
        }
    }

    public void initialPrint() {
        this.printClosedNodes();
        System.out.println("\n\nЦель: " + this.target);
        System.out.println("\nСписок правил");
        for (Edge edge : this.edgeList) {
            System.out.println(edge);
        }
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

    public boolean isTargetAchieved() {
        for (Node node : this.closedNodes) {
            if (this.target.getName().equals(node.getName())) {
                int similarity = 0;
                for (int i = 0; i < target.getArgs().size(); i++) {
                    Constant targetArg = (Constant) target.getArgs().get(i);
                    if (node.getArgs().get(i) != null) {
                        if (node.getArgs().get(i).isVariable()) {
                            Variable var = (Variable) node.getArgs().get(i);
                            if (var.getConstant().getValue().equals(targetArg.getValue())) {
                                similarity++;
                            }
                            continue;
                        }
                        Constant nodeArg = (Constant) node.getArgs().get(i);
                        if (nodeArg.getValue().equals(targetArg.getValue())) {
                            similarity++;
                        }
                    }
                }
                if (similarity == target.getArgs().size()) {
                    this.flagY = 0;
                    System.out.println("\nЦель достигнута");
                    this.printClosedNodes();
                    return true;
                }
            }
        }
        return false;
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
}
