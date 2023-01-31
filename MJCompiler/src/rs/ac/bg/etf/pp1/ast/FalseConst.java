// generated with ast extension for cup
// version 0.8
// 31/0/2023 15:34:29


package rs.ac.bg.etf.pp1.ast;

public class FalseConst extends ConstVal {

    private String F1;

    public FalseConst (String F1) {
        this.F1=F1;
    }

    public String getF1() {
        return F1;
    }

    public void setF1(String F1) {
        this.F1=F1;
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
        buffer.append("FalseConst(\n");

        buffer.append(" "+tab+F1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FalseConst]");
        return buffer.toString();
    }
}
