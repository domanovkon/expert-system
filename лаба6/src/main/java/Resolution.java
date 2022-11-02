import java.util.*;

public class Resolution {

    Unification unification = new Unification();


    private List<Disjunct> allDisjunkts = new ArrayList<>();

    public boolean resolution(List<Disjunct> disjuncts, Disjunct target) {
        allDisjunkts.addAll(disjuncts);
        allDisjunkts.add(target);
        allDisjunkts.sort(Comparator.comparingInt(x -> x.getAtoms().size()));
//        allDisjunkts.sort((x,y)  -> y.getAtoms().size() - x.getAtoms().size());

        int i = 1;
        int j = 0;

        Disjunct currentDisjunct = allDisjunkts.get(0);
        do {
            if (currentDisjunct == allDisjunkts.get(i)) {
                i++;
                if (i == allDisjunkts.size()) {
                    i = 0;
                    j++;

                    if (j > disjuncts.size()) {
                        break;
                    }

                    currentDisjunct = allDisjunkts.get(j);
                }
                continue;
            }
            this.printDisjunct(currentDisjunct);
            this.printDisjunct(allDisjunkts.get(i));
            Disjunct newDisjunct = this.findNewDisjunct(currentDisjunct, allDisjunkts.get(i));
            System.out.println("\nНовый дизъюнкт");
            this.printDisjunct(newDisjunct);

            if (newDisjunct != null && !this.isContains(newDisjunct)) {
                if (newDisjunct.getAtoms().size() == 0) {
                    return true;
                }
                i = 0;
                this.allDisjunkts.add(newDisjunct);
                currentDisjunct = newDisjunct;
            } else {
                i++;
                if (i == this.allDisjunkts.size()) {
                    i = 0;
                    j++;
                    if (j > disjuncts.size()) {
                        break;
                    }
                    currentDisjunct = this.allDisjunkts.get(j);
                }
            }

        } while (j <= disjuncts.size());
        return false;
    }

    public Disjunct findNewDisjunct(Disjunct dis1, Disjunct dis2) {
        List<Atom> dis1Atoms = new ArrayList<>(dis1.getAtoms());
        List<Atom> dis2Atoms = new ArrayList<>(dis2.getAtoms());
        int maxCount = Math.max(dis1Atoms.size(), dis2Atoms.size());
        Integer[][] usedPairs = new Integer[maxCount][maxCount];
        for (int i = 0; i < maxCount; i++) {
            for (int j = 0; j < maxCount; j++) {
                usedPairs[i][j] = 0;
            }
        }
        CP cp228 = new CP(usedPairs);
        for (int i = 0; i < maxCount; i++) {
            CP cp = findCP(cp228, dis1Atoms, dis2Atoms);
            if (cp == null) {
                return null;
            }

            if (!this.unification.unification(cp.getCp().get(0), cp.getCp().get(1))) {
                continue;
            }
            dis1Atoms.remove(cp.getCp().get(0));
            dis2Atoms.remove(cp.getCp().get(1));

            List<Atom> newAtom = new ArrayList<>();
            newAtom.addAll(dis1Atoms);
            newAtom.addAll(dis2Atoms);
            Set<Atom> atomSet = new HashSet<>(newAtom);

            Disjunct disjunct228 = this.findNewDisjunct(new Disjunct(dis1Atoms), new Disjunct(dis2Atoms));
            if (disjunct228 != null) {
                return disjunct228;
            }
            return new Disjunct(new ArrayList<>(atomSet));
        }
        return null;
    }


    public CP findCP(CP cp, List<Atom> atoms1, List<Atom> atoms2) {
        List<Atom> contPairs = new ArrayList<>();
        int i = 0;
        int j = 0;
        boolean flag = false;
        for (Atom a1 : atoms1) {
            j = 0;
            for (Atom a2 : atoms2) {
                if (a1.getName().equals(a2.getName()) && a1.isNegative() != a2.isNegative()) {
                    if (cp.getUsedPairs()[i][j] == 1) {
                        j++;
                        continue;
                    }
                    contPairs.add(a1);
                    contPairs.add(a2);
                    cp.setCp(contPairs);
                    flag = true;
                    break;
                }
                j++;
            }
            if (flag) {
                break;
            }
            i++;
        }
        if (contPairs.size() == 0) {
            cp.setCp(null);
            return null;
        }
        cp.getUsedPairs()[i][j] = 1;
        return cp;
    }

    public ContraryPairs findContraryPairs(List<Atom> atoms1, List<Atom> atoms2, List<Integer[]> usedPairs) {
        ContraryPairs contraryPairs = new ContraryPairs();
        List<Atom> contraryPair = new ArrayList<>();
        int i = 0;
        int j = 0;
        boolean flag = false;
        for (Atom a1 : atoms1) {
            i++;
            for (Atom a2 : atoms2) {
                j++;
                if (a1.getName().equals(a2.getName()) && a1.isNegative() != a2.isNegative()) {
                    for (Integer[] arr : usedPairs) {
                        if (arr[0] == i && arr[1] == j) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        continue;
                    }
                    contraryPair.add(a1);
                    contraryPair.add(a2);
                    contraryPairs.setContraryAtoms(contraryPair);
                    break;
                }
            }
        }
        if (contraryPair.size() == 0) {
            return null;
        }
        Integer[] integers = new Integer[100];
        integers[0] = i;
        integers[1] = j;
        usedPairs.add(integers);
        contraryPairs.setUsedAtoms(usedPairs);

        return contraryPairs;
    }


    public boolean isContains(Disjunct disjunct) {
        int count = 0;
        int k = 0;
        for (Disjunct dis : this.allDisjunkts) {
            if (dis.getAtoms().size() == disjunct.getAtoms().size()) {
                for (int i = 0; i < dis.getAtoms().size(); i++) {
                    if (dis.getAtoms().get(i).getName().equals(disjunct.getAtoms().get(i).getName()) &&
                            dis.getAtoms().get(i).getArgs().size() == disjunct.getAtoms().get(i).getArgs().size() &&
                            dis.getAtoms().get(i).isNegative() == disjunct.getAtoms().get(i).isNegative()) {
                        List<ParamType> dis1Types = dis.getAtoms().get(i).getArgs();
                        List<ParamType> myDisTypes = disjunct.getAtoms().get(i).getArgs();
                        for (int j = 0; j < dis1Types.size(); j++) {
                            if (dis1Types.get(j).isConstant() && myDisTypes.get(j).isConstant()) {
                                Constant c1 = (Constant) dis1Types.get(j);
                                Constant c2 = (Constant) myDisTypes.get(j);
                                if (c1.getValue().equals(c2.getValue())) {
                                    count++;
                                }
                            } else if (dis1Types.get(j).isVariable() && myDisTypes.get(j).isVariable()) {
                                Variable v1 = (Variable) dis1Types.get(j);
                                Variable v2 = (Variable) myDisTypes.get(j);
                                if (v1.getName().equals(v2.getName()) && v1.getLinks().size() == v2.getLinks().size()) {
                                    if (v1.getLinks().size() == 0) {
                                        count++;
                                    } else if (v1.getLinks().size() != 0 && v1.getLinks().containsAll(v2.getLinks())) {
                                        count++;
                                    }
                                }
                            }
                        }
                        if (count == dis1Types.size()) {
                            k++;
                        } else {
                            k = 0;
                        }
                        count = 0;
                    }
                }
                if (k == dis.getAtoms().size()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printDisjunct(Disjunct disjunct) {
        if (disjunct != null) {
            if (disjunct.getAtoms() != null && disjunct.getAtoms().size() == 0) {
                System.out.println("\nПустой дизъюнкт");
            } else {
//                System.out.println("\nДизъюнкт");
                System.out.println("\n");
                int k = 0;
                for (Atom atom : disjunct.getAtoms()) {
                    k++;
                    if (atom.isNegative()) {
                        System.out.print("-");
                    }
                    System.out.print(atom.getName());
                    System.out.print("   ");
                    System.out.print(Arrays.toString(atom.getArgs().toArray()));
                    if (k != disjunct.getAtoms().size()) {
                        System.out.print("\t||\t");
                    }
                }
            }
        } else {
            System.out.println("Не нашел дизъюнкт");
            ;
        }
    }
}
