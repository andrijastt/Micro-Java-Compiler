// generated with ast extension for cup
// version 0.8
// 25/0/2023 18:35:29


package rs.ac.bg.etf.pp1.ast;

public class TrueConst extends ConstVal {

    public TrueConst () {
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

        buffer.append(tab);
        buffer.append(") [TrueConst]");
        return buffer.toString();
    }
}
