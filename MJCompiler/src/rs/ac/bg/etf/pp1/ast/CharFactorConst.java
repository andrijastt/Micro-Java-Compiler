// generated with ast extension for cup
// version 0.8
// 2/1/2023 1:25:52


package rs.ac.bg.etf.pp1.ast;

public class CharFactorConst extends Factor {

    private Character character;

    public CharFactorConst (Character character) {
        this.character=character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character=character;
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
        buffer.append("CharFactorConst(\n");

        buffer.append(" "+tab+character);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharFactorConst]");
        return buffer.toString();
    }
}
