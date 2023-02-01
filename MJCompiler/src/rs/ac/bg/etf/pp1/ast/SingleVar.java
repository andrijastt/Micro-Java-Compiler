// generated with ast extension for cup
// version 0.8
// 1/1/2023 18:32:49


package rs.ac.bg.etf.pp1.ast;

public class SingleVar extends VarDecl {

    private VarSemi VarSemi;

    public SingleVar (VarSemi VarSemi) {
        this.VarSemi=VarSemi;
        if(VarSemi!=null) VarSemi.setParent(this);
    }

    public VarSemi getVarSemi() {
        return VarSemi;
    }

    public void setVarSemi(VarSemi VarSemi) {
        this.VarSemi=VarSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarSemi!=null) VarSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarSemi!=null) VarSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarSemi!=null) VarSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleVar(\n");

        if(VarSemi!=null)
            buffer.append(VarSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleVar]");
        return buffer.toString();
    }
}
