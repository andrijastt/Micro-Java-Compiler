// generated with ast extension for cup
// version 0.8
// 29/0/2023 13:30:50


package rs.ac.bg.etf.pp1.ast;

public class NoDesignatorListItem extends DesignatorTemp {

    public NoDesignatorListItem () {
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
        buffer.append("NoDesignatorListItem(\n");

        buffer.append(tab);
        buffer.append(") [NoDesignatorListItem]");
        return buffer.toString();
    }
}