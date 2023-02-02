// generated with ast extension for cup
// version 0.8
// 2/1/2023 1:18:41


package rs.ac.bg.etf.pp1.ast;

public class ParamItemList extends ParamList {

    private ParamList ParamList;
    private ParamItem ParamItem;

    public ParamItemList (ParamList ParamList, ParamItem ParamItem) {
        this.ParamList=ParamList;
        if(ParamList!=null) ParamList.setParent(this);
        this.ParamItem=ParamItem;
        if(ParamItem!=null) ParamItem.setParent(this);
    }

    public ParamList getParamList() {
        return ParamList;
    }

    public void setParamList(ParamList ParamList) {
        this.ParamList=ParamList;
    }

    public ParamItem getParamItem() {
        return ParamItem;
    }

    public void setParamItem(ParamItem ParamItem) {
        this.ParamItem=ParamItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ParamList!=null) ParamList.accept(visitor);
        if(ParamItem!=null) ParamItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ParamList!=null) ParamList.traverseTopDown(visitor);
        if(ParamItem!=null) ParamItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ParamList!=null) ParamList.traverseBottomUp(visitor);
        if(ParamItem!=null) ParamItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ParamItemList(\n");

        if(ParamList!=null)
            buffer.append(ParamList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ParamItem!=null)
            buffer.append(ParamItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ParamItemList]");
        return buffer.toString();
    }
}
