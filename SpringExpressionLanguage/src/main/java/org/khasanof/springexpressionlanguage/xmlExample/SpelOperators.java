package org.khasanof.springexpressionlanguage.xmlExample;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 9:11 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.xml
 */
public class SpelOperators {
    private boolean equal;
    private boolean notEqual;
    private boolean greaterThanOrEqual;
    private boolean and;
    private boolean or;
    private String addString;

    public SpelOperators() {
    }

    public SpelOperators(boolean equal, boolean notEqual, boolean greaterThanOrEqual, boolean and, boolean or, String addString) {
        this.equal = equal;
        this.notEqual = notEqual;
        this.greaterThanOrEqual = greaterThanOrEqual;
        this.and = and;
        this.or = or;
        this.addString = addString;
    }

    public boolean isEqual() {
        return equal;
    }

    public void setEqual(boolean equal) {
        this.equal = equal;
    }

    public boolean isNotEqual() {
        return notEqual;
    }

    public void setNotEqual(boolean notEqual) {
        this.notEqual = notEqual;
    }

    public boolean isGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public void setGreaterThanOrEqual(boolean greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
    }

    public boolean isAnd() {
        return and;
    }

    public void setAnd(boolean and) {
        this.and = and;
    }

    public boolean isOr() {
        return or;
    }

    public void setOr(boolean or) {
        this.or = or;
    }

    public String getAddString() {
        return addString;
    }

    public void setAddString(String addString) {
        this.addString = addString;
    }

    @Override
    public String toString() {
        return "SpelOperators{" +
                "equal=" + equal +
                ", notEqual=" + notEqual +
                ", greaterThanOrEqual=" + greaterThanOrEqual +
                ", and=" + and +
                ", or=" + or +
                ", addString='" + addString + '\'' +
                '}';
    }
}
