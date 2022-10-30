import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {

    List<ReplacedParams> replacedParams = new ArrayList<>();

    public static void main(String[] args) {

        List<Disjunct> disjuncts = new ArrayList<>();

        Variable x1 = new Variable();
        x1.setName("x1");
        Atom atom1 = new Atom("S", false, Arrays.asList(x1));

        Atom atom2 = new Atom("M", false, Arrays.asList(x1));

        Variable x2 = new Variable();
        x2.setName("x2");
        Atom atom3 = new Atom("M", true, Arrays.asList(x2));

        Constant rain = new Constant("rain");
        Atom atom4 = new Atom("L", true, Arrays.asList(x2, rain));


        Variable x3 = new Variable();
        x3.setName("x3");
        Atom atom5 = new Atom("S", true, Arrays.asList(x3));


        Constant snow = new Constant("snow");
        Atom atom6 = new Atom("L", false, Arrays.asList(x3, snow));

        Variable y1 = new Variable();
        y1.setName("y1");
        Variable y2 = new Variable();
        y2.setName("y2");
        Constant lena = new Constant("lena");
        Constant petya = new Constant("petya");
        Atom atom7 = new Atom("L", true, Arrays.asList(lena, y1));
        Atom atom8 = new Atom("L", true, Arrays.asList(petya, y1));
        Atom atom9 = new Atom("L", false, Arrays.asList(lena, y2));
        Atom atom10 = new Atom("L", false, Arrays.asList(petya, y2));
        Atom atom11 = new Atom("L", false, Arrays.asList(petya, rain));
        Atom atom12 = new Atom("L", false, Arrays.asList(petya, snow));

        Disjunct disjunct1 = new Disjunct(Arrays.asList(atom1, atom2));
        Disjunct disjunct2 = new Disjunct(Arrays.asList(atom3, atom4));
        Disjunct disjunct3 = new Disjunct(Arrays.asList(atom5, atom6));
        Disjunct disjunct4 = new Disjunct(Arrays.asList(atom7, atom8));
        Disjunct disjunct5 = new Disjunct(Arrays.asList(atom9, atom10));
        Disjunct disjunct6 = new Disjunct(Arrays.asList(atom11));
        Disjunct disjunct7 = new Disjunct(Arrays.asList(atom12));


        disjuncts.add(disjunct1);
        disjuncts.add(disjunct2);
        disjuncts.add(disjunct3);
        disjuncts.add(disjunct4);
        disjuncts.add(disjunct5);
        disjuncts.add(disjunct6);
        disjuncts.add(disjunct7);

        Solution solution = new Solution();

    }

    public boolean unification(Atom firstAtom, Atom secondAtom) {

        if ((!firstAtom.getName().equals(secondAtom.getName())) || (firstAtom.getArgs().size() != secondAtom.getArgs().size())) {
            return false;
        }

        for (int i = 0; i < firstAtom.getArgs().size(); i++) {
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
                this.addReplacement("constantToVariable", firstAtomParam, secondAtomParam);
                continue;
            }

            if (firstAtomParam.isVariable() && secondAtomParam.isConstant()) {
                this.addReplacement("variableToConstant", firstAtomParam, secondAtomParam);
            }
        }

        for (ReplacedParams rp : this.replacedParams) {
            switch (rp.getReplacementType()) {
                case "variableToVariable":
                    //
                    break;
                case "constantToVariable":
                    this.addConstantLink((Variable) rp.getParam().get(1), (Constant) rp.getParam().get(0));
                    break;
                case "variableToConstant":
                    this.addConstantLink((Variable) rp.getParam().get(0), (Constant) rp.getParam().get(1));
                    break;
            }
        }
        return true;
    }


    public void addReplacement(String type, ParamType param1, ParamType param2) {
        List<ParamType> params = new ArrayList<>();
        params.add(param1);
        params.add(param2);
        ReplacedParams rp = new ReplacedParams(type, params);
        replacedParams.add(rp);
    }

    public void addConstantLink(Variable variable, Constant constant) {
        variable.setConstant(constant);
        for (Variable v : variable.getLinks()) {
            v.setConstant(constant);
        }
    }

}
