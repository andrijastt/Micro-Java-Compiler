// generated with ast extension for cup
// version 0.8
// 2/1/2023 1:25:52


package rs.ac.bg.etf.pp1.ast;

public class DesignatorBrackets extends Designator {

    private DesignatorBracketsName DesignatorBracketsName;
    private Expr Expr;

    public DesignatorBrackets (DesignatorBracketsName DesignatorBracketsName, Expr Expr) {
        this.DesignatorBracketsName=DesignatorBracketsName;
        if(DesignatorBracketsName!=null) DesignatorBracketsName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorBracketsName getDesignatorBracketsName() {
        return DesignatorBracketsName;
    }

    public void setDesignatorBracketsName(DesignatorBracketsName DesignatorBracketsName) {
        this.DesignatorBracketsName=DesignatorBracketsName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorBracketsName!=null) DesignatorBracketsName.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorBracketsName!=null) DesignatorBracketsName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorBracketsName!=null) DesignatorBracketsName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorBrackets(\n");

        if(DesignatorBracketsName!=null)
            buffer.append(DesignatorBracketsName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorBrackets]");
        return buffer.toString();
    }
}
