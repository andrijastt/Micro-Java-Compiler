package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	private boolean traverseDesignatorBracketsStatement = false;
	private ArrayList<Integer> printNumbers = new ArrayList<Integer>();
	
	public int getMainPc() {
		return mainPc;
	}
	
//	public void visit(Designator Designator) { }
//    public void visit(MethodDecl MethodDecl) { }
//    public void visit(Factor Factor) { }
//    public void visit(DesignatorStatement DesignatorStatement) { }
//    public void visit(MethodVarDecl MethodVarDecl) { }
//    public void visit(Var Var) { }
//    public void visit(ConstDecl ConstDecl) { }
//    public void visit(VarSemi VarSemi) { }
//    public void visit(ParamList ParamList) { }
//    public void visit(ParamItem ParamItem) { }
//    public void visit(Expr Expr) { }
//    public void visit(DesignatorList DesignatorList) { }
//    public void visit(VarDeclList VarDeclList) { }
//    public void visit(DesignatorTemp DesignatorTemp) { }
//    public void visit(VarDecl VarDecl) { }
//    public void visit(ConstDeclList ConstDeclList) { }
//    public void visit(MethodDeclList MethodDeclList) { }
//    public void visit(Statement Statement) { }
//    public void visit(ConstComma ConstComma) { }
//    public void visit(ConstVal ConstVal) { }
//    public void visit(NumConstList NumConstList) { }
//    public void visit(Term Term) { }
//    public void visit(ConstSemi ConstSemi) { }
//    public void visit(VarComma VarComma) { }
//    public void visit(StatementList StatementList) { }
//    public void visit(Modop Modop) { visit(); }
//    public void visit(Divop Divop) { visit(); }
//    public void visit(Mulop Mulop) { visit(); }
//    public void visit(Minusop Minusop) { visit(); }
//    public void visit(Addop Addop) { visit(); }
//    public void visit(Assignop Assignop) { visit(); }
	
	public void visit(DesignatorBracketsName DesignatorBracketsName) {	
		
		DesignatorBrackets temp0 = (DesignatorBrackets) DesignatorBracketsName.getParent();
		
		if(temp0.getParent().getClass() == DesignatorListItem.class) {
			traverseDesignatorBracketsStatement = true;
		}
		
		if(!traverseDesignatorBracketsStatement) {
			Code.load(DesignatorBracketsName.obj);
		}	
	}
	
//    public void visit(DesignatorBrackets DesignatorBrackets) { visit(); }
//  public void visit(DesignatorNoBrackets DesignatorNoBrackets) { visit(); }
//    public void visit(Expression Expression) { visit(); }
	
    public void visit(NewFuncExpr NewFuncExpr) {
    	
    	Code.put(Code.newarray);
    	Obj temp = Tab.find(NewFuncExpr.getType().getTypeName());    	
    	if(temp.getType() == Tab.intType) {
    		Code.put(1);	// b == 1
    	} 
    	else if(temp.getType() == Tab.charType) {    		
    		Code.put(0);	// b == 0
    	}
    	else {    		
    		Code.put(1);	// b == 1, za bool
    	}
    		  	 
    }
    
	public void visit(FalseFactorConst FalseFactorConst) { 
		Code.loadConst(0);		// 0 - false
    }
    
    public void visit(TrueFactorConst TrueFactorConst) { 
    	Code.loadConst(1);		// 1 - true
    }
    
    public void visit(CharFactorConst CharFactorConst) { 
    	Code.loadConst(CharFactorConst.getCharacter());
    }
	
    public void visit(NumFactorConst NumFactorConst) {  
    	if(!traverseDesignatorBracketsStatement) 
	Code.loadConst(NumFactorConst.getN1()); 
    }
    
    public void visit(DesignatorNoPars DisgnatorNoPars) { 
    	if(!traverseDesignatorBracketsStatement) 
    		Code.load(DisgnatorNoPars.getDesignator().obj);
    }
    
//    public void visit(SignleTerm SignleTerm) { visit(); }
    
    public void visit(TermExpr TermExpr) { 
    	
    	if(TermExpr.getMulop().getClass() == MulOper.class) {
    		Code.put(Code.mul);
    	}
    	
    	if(TermExpr.getMulop().getClass() == DivOper.class) {
    		Code.put(Code.div);
    	}

		if(TermExpr.getMulop().getClass() == ModOper.class) {
			Code.put(Code.rem);
		}
    	
    }
    
    public void visit(SingleNegativeExpr SingleNegativeExpr) { 
    	Code.put(Code.neg);
    }
    
//    public void visit(SingleExpr SingleExpr) { visit(); }
    
    public void visit(PositiveExpr PositiveExpr) { 
    	
    	if(PositiveExpr.getAddop().getClass() == AddOper.class) {
    		Code.put(Code.add);
    	}
    	
    	if(PositiveExpr.getAddop().getClass() == MinusOper.class) {
    		Code.put(Code.sub);
    	}
    	
    }
    
//    public void visit(SingleDesignatorList SingleDesignatorList) { visit(); }
//    public void visit(DesignatorLists DesignatorLists) { visit(); }
//    public void visit(NoDesignatorListItem NoDesignatorListItem) { visit(); }
//    public void visit(DesignatorListItem DesignatorListItem) { visit(); }
//    public void visit(DesignatorStatementError DesignatorStatementError) { visit(); }
    
    private class ListVisitor extends VisitorAdaptor{
    	
    	Obj rightSideItem;
    	int index;
    	int counter = 0;
    	
    	public ListVisitor(Obj rightSideItem){
    		this.rightSideItem = rightSideItem;
    		this.index = 0;
    	}
    	
    	public void visit(NoDesignatorListItem item) {
    		index++;
    	}
    	
    	public void visit(DesignatorBracketsName item) {
    		Code.load(item.obj);
    		counter++;
    	}
    	
    	public void visit(DesignatorNoPars item) {   		
    		if(counter > 1) {
    			counter--;
    			Code.load(item.getDesignator().obj);
    			Code.put(Code.aload);
    		}
    	}
    	
    	public void visit(NumFactorConst num) {
    		Code.loadConst(num.getN1());
    	}
    	
    	public void visit(DesignatorListItem item) {
    		// trap part
//    		Code.load(rightSideItem);
//    		Code.put(Code.arraylength);
//    		Code.loadConst(index);
//    		Code.put(Code.jcc + Code.gt);
//    		Code.put2(6);
//    		Code.put(Code.trap);
//    		Code.put2(index);
    		// not trap part
    		Code.load(rightSideItem);
    		Code.loadConst(index);
    		Code.put(Code.aload);
    		Code.store(item.getDesignator().obj);  
    		counter = 0;
    		index++;
    	}
    }
    
    public void visit(DesignatorStatementBrackets DesignatorStatementBrackets) {
    	Obj rightSideItem = DesignatorStatementBrackets.getDesignator().obj;
    	ListVisitor visitor = new ListVisitor(rightSideItem);
    	DesignatorStatementBrackets.traverseBottomUp(visitor);
    	traverseDesignatorBracketsStatement = false;
    }
    
    public void visit(DesignatorDEC DesignatorDEC) { 
    	
    	Designator temp = DesignatorDEC.getDesignator();
    	
    	if(temp.getClass() == DesignatorBrackets.class) {
    		Code.put(Code.dup2);
    	}
    	
    	Code.load(temp.obj);
    	Code.loadConst(1);
    	Code.put(Code.sub);
    	Code.store(temp.obj); 
    }
    
    public void visit(DesignatorINC DesignatorINC) { 
    	
    	Designator temp = DesignatorINC.getDesignator();
    	
    	if(temp.getClass() == DesignatorBrackets.class) {
    		Code.put(Code.dup2);
    	}
    	
    	Code.load(temp.obj);
    	Code.loadConst(1);
    	Code.put(Code.add);
    	Code.store(temp.obj);
    	
    }
    
    public void visit(DesignatorAssign DesignatorAssign) {	
    	Code.store(DesignatorAssign.getDesignator().obj);   	
    }
    
//    public void visit(NoNumConsts NoNumConsts) { visit(); }
    
    public void visit(NumConsts NumConsts) { 
    	printNumbers.add(NumConsts.getN2());
    }
	
    public void visit(PrintStmt PrintStmt) {
    	
    	if(PrintStmt.getExpr().struct == Tab.intType || PrintStmt.getExpr().struct.getKind() == Struct.Bool) {
    		Code.loadConst(5);
    		Code.put(Code.print);
    	}
    	else {
    		Code.loadConst(1);
    		Code.put(Code.bprint);
    	}
    	
    	while(printNumbers.size() > 0) {
    		Code.loadConst(printNumbers.get(0));
    		Code.loadConst(5);
    		Code.put(Code.print);
    		printNumbers.remove(0);
    	}
    }
    
    public void visit(ReadStmt ReadStmt) { 
    	if(ReadStmt.getDesignator().obj.getType() == Tab.charType) {
    		Code.put(Code.bread);
    	} else {
    		Code.put(Code.read);
    	}
    	Code.load(ReadStmt.getDesignator().obj);
    }
    
//    public void visit(DesignatorStmt DesignatorStmt) { visit(); }
//    public void visit(NoStmt NoStmt) { visit(); }
//    public void visit(Statements Statements) { visit(); }
//    public void visit(Type Type) { visit(); }
    
    public void visit(MethodType MethodType) {
    	
    	if("main".equals(MethodType.getMethName())) {
    		mainPc = Code.pc;
    	}
    	MethodType.obj.setAdr(Code.pc);
 
    	Code.put(Code.enter);
    	Code.put(MethodType.obj.getLevel());		
    	Code.put(MethodType.obj.getLocalSymbols().size());
    }
    
    public void visit(VoidMethodDecl VoidMethodDecl) { 
    	Code.put(Code.exit);
    	Code.put(Code.return_);
    }
//    public void visit(NoMethodDecl NoMethodDecl) { visit(); }
//    public void visit(MethodDeclarations MethodDeclarations) { visit(); }
//    public void visit(VarBrackets VarBrackets) { visit(); }
//    public void visit(VarNoBrackets VarNoBrackets) { visit(); }
//    public void visit(VarSemiError VarSemiError) { visit(); }
//    public void visit(SemiVar SemiVar) { visit(); }
//    public void visit(VarCommaError VarCommaError) { visit(); }
//    public void visit(CommaVar CommaVar) { visit(); }
//    public void visit(SingleVar SingleVar) { visit(); }
//    public void visit(VarList VarList) { visit(); }
//    public void visit(VarDeclaration VarDeclaration) { visit(); }
//    public void visit(SingleMethodVarDecl SingleMethodVarDecl) { visit(); }
//    public void visit(MethodVarDecls MethodVarDecls) { visit(); }
//    public void visit(FalseConst FalseConst) { visit(); }
//    public void visit(TrueConst TrueConst) { visit(); }
//    public void visit(CharConst CharConst) { visit(); }
//    public void visit(NumConst NumConst) { visit(); }
//    public void visit(Const Const) { visit(); }
//    public void visit(ConstCommaError ConstCommaError) { visit(); }
//    public void visit(CommaConst CommaConst) { visit(); }
//    public void visit(ConstSemiError ConstSemiError) { visit(); }
//    public void visit(SemiConst SemiConst) { visit(); }
//    public void visit(SingleConst SingleConst) { visit(); }
//    public void visit(ConstList ConstList) { visit(); }
//    public void visit(ConstDeclaration ConstDeclaration) { visit(); }
//    public void visit(ParamVarList ParamVarList) { visit(); }
//    public void visit(ParamConstList ParamConstList) { visit(); }
//    public void visit(NoParamItem NoParamItem) { visit(); }
//    public void visit(ParamItemList ParamItemList) { visit(); }
//    public void visit(ProgName ProgName) { visit(); }
//    public void visit(Program Program) { visit(); }
	
}
