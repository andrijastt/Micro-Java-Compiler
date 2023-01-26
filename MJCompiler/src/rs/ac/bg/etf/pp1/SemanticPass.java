package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class SemanticPass extends VisitorAdaptor {

	int printCallCount = 0;
	
	Obj currentMethod = null;
	boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" on line ").append(line);
		}
		log.error(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" on line ").append(line);
		}
		log.info(msg.toString());
	}
	
    
    
    public void visit(ProgName progName) {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }

    public void visit(Program program) {
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
    public void visit(Designator designator) {
//    	Obj obj = Tab.find(designator.getName()); ovako se nalazi 
//    	if(obj == Tab.noObj) {
//    		report_error("Error on line " + designator.getLine() + " name: " + designator.getName() + "not declared! ", null);
//    	}
//    	designator.obj = obj;
    	
    	
    }
    
    public void visit(MethodDecl methodDecl) {
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	
    	currentMethod = null;
    }
    
    public void visit(Factor Factor) { }
    public void visit(DesignatorStatement DesignatorStatement) { }
    public void visit(MethodVarDecl MethodVarDecl) { }
    public void visit(Var Var) { }
    public void visit(ConstDecl ConstDecl) { }
    public void visit(VarSemi VarSemi) { }
    public void visit(ParamList ParamList) { }
    
    public void visit(ParamItem paramItem){
//		Obj varNode = Tab.insert(Obj.Var, paramItem.get, paramItem.getType().struct); // ovako se dodaje promenljiva
	}
    
    public void visit(Expr Expr) { }
    public void visit(DesignatorList DesignatorList) { }
    public void visit(VarDeclList VarDeclList) { }
    public void visit(DesignatorTemp DesignatorTemp) { }
    public void visit(VarDecl VarDecl) { }
    public void visit(ConstDeclList ConstDeclList) { }
    public void visit(MethodDeclList MethodDeclList) { }
    public void visit(Statement Statement) { }
    public void visit(ConstComma ConstComma) { }
    public void visit(ConstVal ConstVal) { }
    public void visit(NumConstList NumConstList) { }
    public void visit(Term Term) { }
    public void visit(ConstSemi ConstSemi) { }
    public void visit(VarComma VarComma) { }
    public void visit(StatementList StatementList) { }
    public void visit(Modop Modop) { visit(); }
    public void visit(Divop Divop) { visit(); }
    public void visit(Mulop Mulop) { visit(); }
    public void visit(Minusop Minusop) { visit(); }
    public void visit(Addop Addop) { visit(); }
    public void visit(Assignop Assignop) { visit(); }
    public void visit(DesignatorBrackets DesignatorBrackets) { visit(); }
    public void visit(DesignatorNoBrackets DesignatorNoBrackets) { visit(); }
    public void visit(Expression Expression) { visit(); }
    public void visit(NewFuncExpr NewFuncExpr) { visit(); }
    public void visit(FalseFactorConst FalseFactorConst) { visit(); }
    public void visit(TrueFactorConst TrueFactorConst) { visit(); }
    public void visit(CharFactorConst CharFactorConst) { visit(); }
    public void visit(NumFactorConst NumFactorConst) { visit(); }
    public void visit(DisgnatorNoPars DisgnatorNoPars) { visit(); }
    public void visit(SignleTerm SignleTerm) { visit(); }
    public void visit(TermExpr TermExpr) { visit(); }
    public void visit(SingleNegativeExpr SingleNegativeExpr) { visit(); }
    public void visit(SingleExpr SingleExpr) { visit(); }
    public void visit(PositiveExpr PositiveExpr) { visit(); }
    public void visit(NegativeExpr NegativeExpr) { visit(); }
    public void visit(SingleDesignatorList SingleDesignatorList) { visit(); }
    public void visit(DesignatorLists DesignatorLists) { visit(); }
    public void visit(NoDesignatroTemp NoDesignatroTemp) { visit(); }
    public void visit(DesignatroTemp DesignatroTemp) { visit(); }
    public void visit(DesignatorStatementError DesignatorStatementError) { visit(); }
    public void visit(DesignatorStatementBrackets DesignatorStatementBrackets) { visit(); }
    public void visit(DesignatorDEC DesignatorDEC) { visit(); }
    public void visit(DesignatorINC DesignatorINC) { visit(); }
    public void visit(DesignatorAssign DesignatorAssign) { visit(); }
    public void visit(NoNumConsts NoNumConsts) { visit(); }
    public void visit(NumConsts NumConsts) { visit(); }
    
    public void visit(PrintStmt print) {
		printCallCount++;
	}
    
    public void visit(ReadStmt ReadStmt) { visit(); }
    public void visit(DesignatorStmt DesignatorStmt) { visit(); }
    public void visit(NoStmt NoStmt) { visit(); }
    public void visit(Statements Statements) { visit(); }
    
    public void visit(Type type) {
    	Obj typeNode = Tab.find(type.getTypeName());
    	
    	if(typeNode == Tab.noObj) {
    		report_error("Not found type " + type.getTypeName() + " in symbol table!", null);
    		type.struct = Tab.noType;
    	}
    	else {
    		if(Obj.Type == typeNode.getKind()) {
    			type.struct = typeNode.getType();
    		} else {
    			report_error("Error: Name " + type.getTypeName() + " isn't a type!", type);
				type.struct = Tab.noType;
    		}
    	}
    	
    }
    
    public void visit(MethodType methodType) {
    	currentMethod = Tab.insert(Obj.Meth, methodType.getMethName(), null); // null == void?
    	methodType.obj = currentMethod;
    	Tab.openScope();
    	report_info("Function: " + methodType.getMethName(), methodType);
    }
    
    public void visit(VoidMethodDecl VoidMethodDecl) { visit(); }
    public void visit(NoMethodDecl NoMethodDecl) { visit(); }
    public void visit(MethodDeclarations MethodDeclarations) { visit(); }
    public void visit(VarBrackets VarBrackets) { visit(); }
    public void visit(VarNoBrackets VarNoBrackets) { visit(); }
    public void visit(VarSemiError VarSemiError) { visit(); }
    public void visit(SemiVar SemiVar) { visit(); }
    public void visit(VarCommaError VarCommaError) { visit(); }
    public void visit(CommaVar CommaVar) { visit(); }
    public void visit(SingleVar SingleVar) { visit(); }
    public void visit(VarList VarList) { visit(); }
    public void visit(VarDeclaration VarDeclaration) { visit(); }
    public void visit(NoMethodVarDecls NoMethodVarDecls) { visit(); }
    public void visit(MethodVarDecls MethodVarDecls) { visit(); }
    public void visit(FalseConst FalseConst) { visit(); }
    public void visit(TrueConst TrueConst) { visit(); }
    public void visit(CharConst CharConst) { visit(); }
    public void visit(NumConst NumConst) { visit(); }
    public void visit(Const Const) { visit(); }
    public void visit(ConstCommaError ConstCommaError) { visit(); }
    public void visit(CommaConst CommaConst) { visit(); }
    public void visit(ConstSemiError ConstSemiError) { visit(); }
    public void visit(SemiConst SemiConst) { visit(); }
    public void visit(SingleConst SingleConst) { visit(); }
    public void visit(ConstList ConstList) { visit(); }
    public void visit(ConstDeclaration ConstDeclaration) { visit(); }
    public void visit(ParamVarList ParamVarList) { visit(); }
    public void visit(ParamConstList ParamConstList) { visit(); }
    public void visit(NoParamItem NoParamItem) { visit(); }
    public void visit(ParamItemList ParamItemList) { visit(); }
    
}
