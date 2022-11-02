import java.util.ArrayList;
import java.util.List;

public class CP {

    private List<Atom> cp = new ArrayList<>();

    private Integer[][] usedPairs;

    public CP(Integer[][] usedPairs) {
        this.usedPairs = usedPairs;
    }

    public List<Atom> getCp() {
        return cp;
    }

    public void setCp(List<Atom> cp) {
        this.cp = cp;
    }

    public Integer[][] getUsedPairs() {
        return usedPairs;
    }

    public void setUsedPairs(Integer[][] usedPairs) {
        this.usedPairs = usedPairs;
    }
}
