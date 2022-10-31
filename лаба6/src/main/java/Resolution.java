import java.util.*;

public class Resolution {

    Unification unification = new Unification();

    public void resolution(List<Disjunct> disjuncts, Disjunct target) {
        int i = 0;
        int j = 0;
        do {

        } while (j <= disjuncts.size());
    }

    public Disjunct findNewDisjunct(Disjunct dis1, Disjunct dis2) {
        int maxCount = Math.max(dis1.getAtoms().size(), dis2.getAtoms().size());
        HashMap<Integer, Integer> usedPairs = new HashMap<>();
        for (int i = 0; i < maxCount; i++) {
            ContraryPairs contraryPairs = findContraryPairs(dis1.getAtoms(), dis2.getAtoms(), usedPairs);
            if (contraryPairs == null) {
                return null;
            }
            usedPairs.putAll(contraryPairs.usedAtoms);
            if (!this.unification.unification(contraryPairs.contraryAtoms.get(0), contraryPairs.contraryAtoms.get(1))) {
                continue;
            }

            dis1.getAtoms().remove(contraryPairs.contraryAtoms.get(0));
            dis2.getAtoms().remove(contraryPairs.contraryAtoms.get(1));

            List<Atom> atomList = new ArrayList<>();
            atomList.addAll(dis1.getAtoms());

            for (Atom dis2Atom : dis2.getAtoms()) {
                if (!atomList.contains(dis2Atom)) {
                    atomList.add(dis2Atom);
                }
            }
            return new Disjunct(atomList);
        }
        return null;
    }

    public ContraryPairs findContraryPairs(List<Atom> atoms1, List<Atom> atoms2, HashMap<Integer, Integer> usedPairs) {
        ContraryPairs contraryPairs = new ContraryPairs();
        List<Atom> contraryPair = new ArrayList<>();
        int i = 0;
        int j = 0;
        for (Atom a1 : atoms1) {
            i++;
            for (Atom a2 : atoms2) {
                j++;
                if (a1.getName().equals(a2.getName()) && a1.isNegative() != a2.isNegative()
                        && a1.getArgs().size() == a2.getArgs().size() && !usedPairs.containsKey(i)) {
                    contraryPair.add(a1);
                    contraryPair.add(a2);
                    usedPairs.put(i, j);
                    contraryPairs.setUsedAtoms(usedPairs);
                    contraryPairs.setContraryAtoms(contraryPair);
                    break;
                }
            }
        }
        if (contraryPair.size() == 0) {
            return null;
        }
        return contraryPairs;
    }
}
