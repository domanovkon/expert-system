import java.util.ArrayList;
import java.util.List;

public class Node {

    private String name;

    private int flag;

    private List<ParamType> args = new ArrayList<>();

    public Node(String name, int flag, List<ParamType> args) {
        this.name = name;
        this.flag = 0;
        this.args.addAll(args);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<ParamType> getArgs() {
        return args;
    }

    public void setArgs(List<ParamType> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", args=" + args;
    }
}
