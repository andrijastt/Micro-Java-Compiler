// generated with ast extension for cup
// version 0.8
// 27/0/2023 14:29:3


package rs.ac.bg.etf.pp1.ast;

public class NoDesignatorTemp extends DesignatorTemp {

    public NoDesignatorTemp () {
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
        buffer.append("NoDesignatorTemp(\n");

        buffer.append(tab);
        buffer.append(") [NoDesignatorTemp]");
        return buffer.toString();
    }
}