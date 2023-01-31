// generated with ast extension for cup
// version 0.8
// 31/0/2023 15:34:29


package rs.ac.bg.etf.pp1.ast;

public class SingleConst extends ConstDecl {

    private ConstSemi ConstSemi;

    public SingleConst (ConstSemi ConstSemi) {
        this.ConstSemi=ConstSemi;
        if(ConstSemi!=null) ConstSemi.setParent(this);
    }

    public ConstSemi getConstSemi() {
        return ConstSemi;
    }

    public void setConstSemi(ConstSemi ConstSemi) {
        this.ConstSemi=ConstSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstSemi!=null) ConstSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstSemi!=null) ConstSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstSemi!=null) ConstSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConst(\n");

        if(ConstSemi!=null)
            buffer.append(ConstSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConst]");
        return buffer.toString();
    }
}
