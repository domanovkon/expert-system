import java.util.ArrayList;
import java.util.List;

public class Variable extends ParamType {

    private String name;

    private Constant constant;

    private List<Variable> links = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constant getConstant() {
        return constant;
    }

    public void setConstant(Constant constant) {
        this.constant = constant;
    }

    public List<Variable> getLinks() {
        return links;
    }

    public void setLinks(List<Variable> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", constant=" + constant +
                ", link size" + getLinks().size() +
                '}';
    }
}
