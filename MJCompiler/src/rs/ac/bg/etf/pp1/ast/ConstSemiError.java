// generated with ast extension for cup
// version 0.8
// 26/0/2023 17:24:39


package rs.ac.bg.etf.pp1.ast;

public class ConstSemiError extends ConstSemi {

    public ConstSemiError () {
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
        buffer.append("ConstSemiError(\n");

        buffer.append(tab);
        buffer.append(") [ConstSemiError]");
        return buffer.toString();
    }
}