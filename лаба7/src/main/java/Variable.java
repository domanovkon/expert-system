import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        String result = this.links.stream()
                .map(n -> String.valueOf(n.getName()))
                .collect(Collectors.joining());
        return name;
    }
}
