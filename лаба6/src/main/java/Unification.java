import java.util.*;

public class Unification {

    List<ReplacedParams> replacedParams = new ArrayList<>();

    public boolean unification(Atom firstAtom, Atom secondAtom) {

        if ((!firstAtom.getName().equals(secondAtom.getName())) || (firstAtom.getArgs().size() != secondAtom.getArgs().size())) {
            return false;
        }

        for (int i = 0; i < firstAtom.getArgs().size(); i++) {
            System.out.println(i);
            ParamType firstAtomParam = firstAtom.getArgs().get(i);
            ParamType secondAtomParam = secondAtom.getArgs().get(i);

            if (firstAtomParam.isConstant() && secondAtomParam.isConstant()) {
                if (!((Constant) firstAtomParam).getValue().equals(((Constant) secondAtomParam).getValue())) {
                    return false;
                }
                this.addReplacement("constantToConstant", firstAtomParam, secondAtomParam);
                continue;
            }

            if (firstAtomParam.isVariable() && secondAtomParam.isVariable()) {
                if (((Variable) firstAtomParam).getConstant() != null && ((Variable) secondAtomParam).getConstant() != null) {
                    if (!((Variable) firstAtomParam).getConstant().equals(((Variable) secondAtomParam).getConstant())) {
                        return false;
                    }
                }
                this.addReplacement("variableToVariable", firstAtomParam, secondAtomParam);
                continue;
            }
            if (firstAtomParam.isConstant() && secondAtomParam.isVariable()) {
                if (((Variable) secondAtomParam).getConstant() != null && !((Variable) secondAtomParam).getConstant().getValue().equals(((Constant)firstAtomParam).getValue())) {
                    return false;
                }
                this.addReplacement("constantToVariable", firstAtomParam, secondAtomParam);
            } else {
                if (((Variable) firstAtomParam).getConstant() != null && !((Variable) firstAtomParam).getConstant().getValue().equals(((Constant) secondAtomParam).getValue())) {
                    return false;
                }
                this.addReplacement("variableToConstant", firstAtomParam, secondAtomParam);
            }
        }

        for (ReplacedParams rp : this.replacedParams) {
            switch (rp.getReplacementType()) {
                case "variableToVariable":
                    this.addVariableLink((Variable) rp.getParam().get(0), (Variable) rp.getParam().get(1));
                    if (((Variable) rp.getParam().get(0)).getConstant() != null && ((Variable) rp.getParam().get(1)).getConstant() == null) {
                        this.addConstantLink(((Variable) rp.getParam().get(1)), ((Variable) rp.getParam().get(0)).getConstant());
                    } else if (((Variable) rp.getParam().get(1)).getConstant() != null && ((Variable) rp.getParam().get(0)).getConstant() == null) {
                        this.addConstantLink(((Variable) rp.getParam().get(0)), ((Variable) rp.getParam().get(1)).getConstant());
                    }
                    break;
                case "constantToVariable":
                    this.addConstantLink((Variable) rp.getParam().get(1), (Constant) rp.getParam().get(0));
                    break;
                case "variableToConstant":
                    this.addConstantLink((Variable) rp.getParam().get(0), (Constant) rp.getParam().get(1));
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void addVariableLink(Variable variable1, Variable variable2) {
        Set<Variable> allLinks = new LinkedHashSet<>();
        allLinks.addAll(variable1.getLinks());
        allLinks.addAll(variable2.getLinks());
        List<Variable> v1List = new ArrayList<>(allLinks);
        v1List.remove(variable1);
        if (!v1List.contains(variable2)) {
            v1List.add(variable2);
        }
        variable1.setLinks(v1List);
        List<Variable> v2List = new ArrayList<>(allLinks);
        v2List.remove(variable2);
        if (!v2List.contains(variable1)) {
            v2List.add(variable1);
        }
        variable2.setLinks(v2List);
    }

    public boolean isDifferentSings(Atom atom1, Atom atom2) {
        return atom1.isNegative() != atom2.isNegative();
    }

    public void addReplacement(String type, ParamType param1, ParamType param2) {
        List<ParamType> params = new ArrayList<>();
        params.add(param1);
        params.add(param2);
        ReplacedParams rp = new ReplacedParams(type, params);
        this.replacedParams.add(rp);
    }

    public void addConstantLink(Variable variable, Constant constant) {
        variable.setConstant(constant);
        for (Variable v : variable.getLinks()) {
            v.setConstant(constant);
        }
    }

    public void printAtoms(Atom atom1, Atom atom2) {
        String atom1Sign = "";
        if (atom1.isNegative()) {
            atom1Sign = "-";
        }
        String atom2Sign = "";
        if (atom2.isNegative()) {
            atom2Sign = "-";
        }
        System.out.println("Атом 1:");
        System.out.println(atom1Sign + " " + atom1.getName());
        for (ParamType pt : atom1.getArgs()) {
            if (pt instanceof Variable) {
                System.out.println("Переменная: " + ((Variable) pt).getName());
                if (((Variable) pt).getConstant() != null) {
                    System.out.println("\tЗначение: " + ((Variable) pt).getConstant().getValue());
                } else {
                    System.out.println("\tЗначение: пусто");
                }
                if (((Variable) pt).getLinks().size() == 0) {
                    System.out.println("\tСвязанные переменные: пусто");
                } else {
                    System.out.print("\tСвязанные переменные: ");
                    for (Variable var : ((Variable) pt).getLinks()) {
                        System.out.print(" " + var.getName() + "\n");
                    }
                }
            } else {
                System.out.println("Константа: " + ((Constant) pt).getValue());
            }
        }
        System.out.println("Атом 2:");
        System.out.println(atom2Sign + " " + atom2.getName());
        for (ParamType pt : atom2.getArgs()) {
            if (pt instanceof Variable) {
                System.out.println("Переменная: " + ((Variable) pt).getName());
                if (((Variable) pt).getConstant() != null) {
                    System.out.println("\tЗначение: " + ((Variable) pt).getConstant().getValue());
                } else {
                    System.out.println("\tЗначение: пусто");
                }
                if (((Variable) pt).getLinks().size() == 0) {
                    System.out.println("\tСвязанные переменные: пусто");
                } else {
                    System.out.print("\tСвязанные переменные: ");
                    for (Variable var : ((Variable) pt).getLinks()) {
                        System.out.print(" " + var.getName() + "\n");
                    }
                }
            } else {
                System.out.println("Константа: " + ((Constant) pt).getValue());
            }
        }
    }

}
