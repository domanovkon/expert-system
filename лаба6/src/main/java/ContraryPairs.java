import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContraryPairs {

    List<Atom> contraryAtoms = new ArrayList<>();

    List<Integer[]> usedAtoms = new ArrayList<>();

    public List<Atom> getContraryAtoms() {
        return contraryAtoms;
    }

    public void setContraryAtoms(List<Atom> contraryAtoms) {
        this.contraryAtoms = contraryAtoms;
    }

    public List<Integer[]> getUsedAtoms() {
        return usedAtoms;
    }

    public void setUsedAtoms(List<Integer[]> usedAtoms) {
        this.usedAtoms = usedAtoms;
    }
}
