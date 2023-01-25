// generated with ast extension for cup
// version 0.8
// 25/0/2023 21:54:29


package rs.ac.bg.etf.pp1.ast;

public class ConstList extends ConstDeclList {

    private ConstComma ConstComma;
    private ConstDeclList ConstDeclList;

    public ConstList (ConstComma ConstComma, ConstDeclList ConstDeclList) {
        this.ConstComma=ConstComma;
        if(ConstComma!=null) ConstComma.setParent(this);
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
    }

    public ConstComma getConstComma() {
        return ConstComma;
    }

    public void setConstComma(ConstComma ConstComma) {
        this.ConstComma=ConstComma;
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstComma!=null) ConstComma.accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstComma!=null) ConstComma.traverseTopDown(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstComma!=null) ConstComma.traverseBottomUp(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
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

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstList]");
        return buffer.toString();
    }
}
