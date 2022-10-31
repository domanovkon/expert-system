import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContraryPairs {

    List<Atom> contraryAtoms = new ArrayList<>();

    HashMap<Integer, Integer> usedAtoms = new HashMap<>();

    public List<Atom> getContraryAtoms() {
        return contraryAtoms;
    }

    public void setContraryAtoms(List<Atom> contraryAtoms) {
        this.contraryAtoms = contraryAtoms;
    }

    public HashMap<Integer, Integer> getUsedAtoms() {
        return usedAtoms;
    }

    public void setUsedAtoms(HashMap<Integer, Integer> usedAtoms) {
        this.usedAtoms = usedAtoms;
    }
}
