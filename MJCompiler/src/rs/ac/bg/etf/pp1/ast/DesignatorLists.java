// generated with ast extension for cup
// version 0.8
// 31/0/2023 15:34:29


package rs.ac.bg.etf.pp1.ast;

public class DesignatorLists extends DesignatorList {

    private DesignatorList DesignatorList;
    private DesignatorTemp DesignatorTemp;

    public DesignatorLists (DesignatorList DesignatorList, DesignatorTemp DesignatorTemp) {
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.DesignatorTemp=DesignatorTemp;
        if(DesignatorTemp!=null) DesignatorTemp.setParent(this);
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public DesignatorTemp getDesignatorTemp() {
        return DesignatorTemp;
    }

    public void setDesignatorTemp(DesignatorTemp DesignatorTemp) {
        this.DesignatorTemp=DesignatorTemp;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
        if(DesignatorTemp!=null) DesignatorTemp.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
        if(DesignatorTemp!=null) DesignatorTemp.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        if(DesignatorTemp!=null) DesignatorTemp.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorLists(\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorTemp!=null)
            buffer.append(DesignatorTemp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorLists]");
        return buffer.toString();
    }
}
