// generated with ast extension for cup
// version 0.8
// 30/0/2023 15:23:53


package rs.ac.bg.etf.pp1.ast;

public class TrueConst extends ConstVal {

    private String T1;

    public TrueConst (String T1) {
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
        buffer.append("TrueConst(\n");

        buffer.append(" "+tab+T1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TrueConst]");
        return buffer.toString();
    }
}
