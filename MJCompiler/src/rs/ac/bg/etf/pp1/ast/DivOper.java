// generated with ast extension for cup
// version 0.8
// 2/1/2023 1:18:41


package rs.ac.bg.etf.pp1.ast;

public class DivOper extends Mulop {

    public DivOper () {
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
        buffer.append("DivOper(\n");

        buffer.append(tab);
        buffer.append(") [DivOper]");
        return buffer.toString();
    }
}
