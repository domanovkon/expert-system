import java.util.List;

public class ReplacedParams {

    private String replacementType;

    private List<ParamType> param;

    public ReplacedParams(String replacementType, List<ParamType> param) {
        this.replacementType = replacementType;
        this.param = param;
    }

    public String getReplacementType() {
        return replacementType;
    }

    public void setReplacementType(String replacementType) {
        this.replacementType = replacementType;
    }

    public List<ParamType> getParam() {
        return param;
    }

    public void setParam(List<ParamType> param) {
        this.param = param;
    }
}
