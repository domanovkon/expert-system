public class ParamType {

    public boolean isConstant() {
        return this instanceof Constant;
    }

    public boolean isVariable() {
        return this instanceof Variable;
    }
}
