// generated with ast extension for cup
// version 0.8
// 31/0/2023 15:34:29


package rs.ac.bg.etf.pp1.ast;

public class ConstList extends ConstDecl {

    private ConstComma ConstComma;
    private ConstDecl ConstDecl;

    public ConstList (ConstComma ConstComma, ConstDecl ConstDecl) {
        this.ConstComma=ConstComma;
        if(ConstComma!=null) ConstComma.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ConstComma getConstComma() {
        return ConstComma;
    }

    public void setConstComma(ConstComma ConstComma) {
        this.ConstComma=ConstComma;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstComma!=null) ConstComma.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstComma!=null) ConstComma.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstComma!=null) ConstComma.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstList(\n");

        if(ConstComma!=null)
            buffer.append(ConstComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstList]");
        return buffer.toString();
    }
}
