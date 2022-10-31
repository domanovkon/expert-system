import java.util.ArrayList;
import java.util.List;

public class Disjunct {

    List<Atom> atoms = new ArrayList<>();

    public Disjunct(List<Atom> atoms) {
        this.atoms.addAll(atoms);
    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    public void setAtoms(List<Atom> atoms) {
        this.atoms = atoms;
    }
}
