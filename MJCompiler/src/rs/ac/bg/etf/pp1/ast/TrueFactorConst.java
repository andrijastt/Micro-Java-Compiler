// generated with ast extension for cup
// version 0.8
// 2/1/2023 1:25:52


package rs.ac.bg.etf.pp1.ast;

public class TrueFactorConst extends Factor {

    private String T1;

    public TrueFactorConst (String T1) {
        this.T1=T1;
    }

    public String getT1() {
        return T1;
    }

    public void setT1(String T1) {
        this.T1=T1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TrueFactorConst(\n");

        buffer.append(" "+tab+T1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TrueFactorConst]");
        return buffer.toString();
    }
}
