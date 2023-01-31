package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
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
//    public void visit(DesignatorBrackets DesignatorBrackets) { visit(); }
    
	public void visit(DesignatorNoBrackets DesignatorNoBrackets) { 
//		 Synt
	}
	
//    public void visit(Expression Expression) { visit(); }
	
    public void visit(NewFuncExpr NewFuncExpr) {
    	
    	// provereno da su dobri tipovi
    	// samo dodajemo na heap len 
    	// razlicito za Char i Int
    	
    	 
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
    	 Code.loadConst(NumFactorConst.getN1()); 
    }
    
//    public void visit(DisgnatorNoPars DisgnatorNoPars) { visit(); }
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
//    public void visit(DesignatorStatementBrackets DesignatorStatementBrackets) { visit(); }
//    public void visit(DesignatorDEC DesignatorDEC) { visit(); }
//    public void visit(DesignatorINC DesignatorINC) { visit(); }
    
    public void visit(DesignatorAssign DesignatorAssign) { 
    	Code.store(DesignatorAssign.getDesignator().obj);
    }
    
//    public void visit(NoNumConsts NoNumConsts) { visit(); }
//    public void visit(NumConsts NumConsts) { visit(); }
	
    public void visit(PrintStmt PrintStmt) {
    	
    	if(PrintStmt.getExpr().struct == Tab.intType) {
    		Code.loadConst(5);
    		Code.put(Code.print);
    	}
    	else {
    		Code.loadConst(1);
    		Code.put(Code.bprint);
    	}
    }
    
//    public void visit(ReadStmt ReadStmt) { visit(); }
//    public void visit(DesignatorStmt DesignatorStmt) { visit(); }
//    public void visit(NoStmt NoStmt) { visit(); }
//    public void visit(Statements Statements) { visit(); }
//    public void visit(Type Type) { visit(); }
    
    public void visit(MethodType MethodType) {
    	
    	if("main".equals(MethodType.getMethName())) {
    		mainPc = Code.pc;
    	}
    	MethodType.obj.setAdr(Code.pc);
    	Code.put(MethodType.obj.getLevel());		// ako zeza stavi 0 jer nema formal params
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
    
    public void visit(Const Const) { 
    	
    	Obj con = Tab.insert(Obj.Con, Const.getConstName(), Const.struct);
    	
    	if(Const.getConstVal().getClass() == NumConst.class) {			// Tab.intType
    		NumConst temp = (NumConst)Const.getConstVal();
    		con.setAdr(temp.getN1());
    	} 
    	else if(Const.getConstVal().getClass() == CharConst.class) {	// Tab.charType
    		CharConst temp = (CharConst)Const.getConstVal();
    		con.setAdr(temp.getC1());
    	}
    	else if(Const.getConstVal().getClass() == TrueConst.class) {	// Tab.boolType	TRUE
//    		TrueConst temp = (TrueConst)Const.getConstVal();
//    		con.setAdr(temp.getT1());
    		con.setAdr(1);
    	} 
    	else if(Const.getConstVal().getClass() == FalseConst.class) {	// Tab.boolType	FALSE
//    		FalseConst temp = (FalseConst)Const.getConstVal();
//    		con.setAdr(temp.getF1());
    		con.setAdr(0);
    	}
    		
    	Code.load(con);
    	
    }
    
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
