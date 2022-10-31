import java.util.ArrayList;
import java.util.List;

public class Atom {

    private String name;

    private boolean isNegative;

    private List<ParamType> args = new ArrayList<>();

    public Atom(String name, boolean isNegative, List<ParamType> args) {
        this.name = name;
        this.isNegative = isNegative;
        this.args.addAll(args);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }

    public List<ParamType> getArgs() {
        return args;
    }

    public void setArgs(List<ParamType> args) {
        this.args = args;
    }
}
