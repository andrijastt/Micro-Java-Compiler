// generated with ast extension for cup
// version 0.8
// 1/1/2023 18:32:49


package rs.ac.bg.etf.pp1.ast;

public class MinusOper extends Addop {

    public MinusOper () {
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
        buffer.append("MinusOper(\n");

        buffer.append(tab);
        buffer.append(") [MinusOper]");
        return buffer.toString();
    }
}
