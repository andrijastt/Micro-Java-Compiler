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
    
    public void visit(Factor factor) { report_info("Factor: ", factor); }
    public void visit(DesignatorStatement designatorStatement) { report_info("DesignatorStatement: ", designatorStatement); }
    public void visit(MethodVarDecl methodVarDecl) { report_info("MethodVarDecl: ", methodVarDecl); }
    public void visit(Var var) { report_info("Var: ", var); }
    public void visit(ConstDecl constDecl) { report_info("ConstDecl", constDecl); }
    public void visit(VarSemi varSemi) { report_info("VarSemi", varSemi); }
    public void visit(ParamList paramList) { report_info("ParamList", paramList); }
    
    public void visit(ParamItem paramItem){
//		Obj varNode = Tab.insert(Obj.Var, paramItem.get, paramItem.getType().struct); // ovako se dodaje promenljiva
    	report_info("ParamItem", paramItem); 
	}
    
    public void visit(Expr expr) { report_info("Expr", expr); }
    public void visit(DesignatorList DesignatorList) { report_info("DesignatorList", DesignatorList); }
    public void visit(VarDeclList VarDeclList) { report_info("VarDeclList", VarDeclList); }
    public void visit(DesignatorTemp DesignatorTemp) { report_info("DesignatorTemp", DesignatorTemp); }
    public void visit(VarDecl VarDecl) { report_info("VarDecl", VarDecl); }
    public void visit(ConstDeclList ConstDeclList) { report_info("ConstDeclList", ConstDeclList); }
    public void visit(MethodDeclList MethodDeclList) { report_info("MethodDeclList", MethodDeclList); }
    public void visit(Statement Statement) { report_info("Statement", Statement); }
    public void visit(ConstComma ConstComma) { report_info("ConstComma", ConstComma); }
    public void visit(ConstVal ConstVal) { report_info("ConstVal", ConstVal); }
    public void visit(NumConstList NumConstList) { report_info("NumConstList", NumConstList); }
    public void visit(Term Term) { report_info("Term", Term); }
    public void visit(ConstSemi ConstSemi) { report_info("ConstSemi", ConstSemi); }
    public void visit(VarComma VarComma) { report_info("VarComma", VarComma); }
    public void visit(StatementList StatementList) { report_info("StatementList", StatementList); }
    public void visit(Modop Modop) { report_info("Modop", Modop); }
    public void visit(Divop Divop) { report_info("Divop", Divop); }
    public void visit(Mulop Mulop) { report_info("Mulop", Mulop); }
    public void visit(Minusop Minusop) { report_info("Minusop", Minusop); }
    public void visit(Addop Addop) { report_info("Addop", Addop); }
    public void visit(Assignop Assignop) { report_info("Assignop", Assignop); }
    public void visit(DesignatorBrackets DesignatorBrackets) { report_info("DesignatorBrackets", DesignatorBrackets); }
    public void visit(DesignatorNoBrackets DesignatorNoBrackets) { report_info("DesignatorNoBrackets", DesignatorNoBrackets); }
    public void visit(Expression Expression) { report_info("Expression", Expression); }
    public void visit(NewFuncExpr NewFuncExpr) { report_info("NewFuncExpr", NewFuncExpr); }
    public void visit(FalseFactorConst FalseFactorConst) { report_info("FalseFactorConst", FalseFactorConst); }
    public void visit(TrueFactorConst TrueFactorConst) { report_info("TrueFactorConst", TrueFactorConst); }
    public void visit(CharFactorConst CharFactorConst) { report_info("CharFactorConst", CharFactorConst); }
    public void visit(NumFactorConst NumFactorConst) { report_info("NumFactorConst", NumFactorConst); }
    public void visit(DisgnatorNoPars DisgnatorNoPars) { report_info("DisgnatorNoPars", DisgnatorNoPars); }
    public void visit(SignleTerm SignleTerm) { report_info("SignleTerm", SignleTerm); }
    public void visit(TermExpr TermExpr) { report_info("TermExpr", TermExpr); }
    public void visit(SingleNegativeExpr SingleNegativeExpr) { report_info("SingleNegativeExpr", SingleNegativeExpr); }
    public void visit(SingleExpr SingleExpr) { report_info("SingleExpr", SingleExpr); }
    public void visit(PositiveExpr PositiveExpr) { report_info("PositiveExpr", PositiveExpr); }
    public void visit(NegativeExpr NegativeExpr) { report_info("NegativeExpr", NegativeExpr); }
    public void visit(SingleDesignatorList SingleDesignatorList) { report_info("SingleDesignatorList", SingleDesignatorList); }
    public void visit(DesignatorLists DesignatorLists) { report_info("DesignatorLists", DesignatorLists); }
    public void visit(NoDesignatroTemp NoDesignatroTemp) { report_info("NoDesignatroTemp", NoDesignatroTemp); }
    public void visit(DesignatroTemp DesignatroTemp) { report_info("DesignatroTemp", DesignatroTemp); }
    public void visit(DesignatorStatementError DesignatorStatementError) { report_info("DesignatorStatementError", DesignatorStatementError); }
    public void visit(DesignatorStatementBrackets DesignatorStatementBrackets) { report_info("DesignatorStatementBrackets", DesignatorStatementBrackets); }
    public void visit(DesignatorDEC DesignatorDEC) { report_info("DesignatorDEC", DesignatorDEC); }
    public void visit(DesignatorINC DesignatorINC) { report_info("DesignatorINC", DesignatorINC); }
    public void visit(DesignatorAssign DesignatorAssign) { report_info("DesignatorAssign", DesignatorAssign); }
    public void visit(NoNumConsts NoNumConsts) { report_info("NoNumConsts", NoNumConsts); }
    public void visit(NumConsts NumConsts) { report_info("NumConsts", NumConsts); }
    
    public void visit(PrintStmt print) {
		printCallCount++;
		{ report_info("PrintStmt", print); }
	}
    
    public void visit(ReadStmt ReadStmt) { report_info("ReadStmt", ReadStmt); }
    public void visit(DesignatorStmt DesignatorStmt) { report_info("DesignatorStmt", DesignatorStmt); }
    public void visit(NoStmt NoStmt) { report_info("NoStmt", NoStmt); }
    public void visit(Statements Statements) { report_info("Statements", Statements); }
    
    public void visit(Type type) {
//    	Obj typeNode = Tab.find(type.getTypeName());
//    	
//    	if(typeNode == Tab.noObj) {
//    		report_error("Not found type " + type.getTypeName() + " in symbol table!", null);
//    		type.struct = Tab.noType;
//    	}
//    	else {
//    		if(Obj.Type == typeNode.getKind()) {
//    			type.struct = typeNode.getType();
//    		} else {
//    			report_error("Error: Name " + type.getTypeName() + " isn't a type!", type);
//				type.struct = Tab.noType;
//    		}
//    	}
    	
    	{ report_info("Type", type); }
    	
    }
    
    public void visit(MethodType methodType) {
//    	currentMethod = Tab.insert(Obj.Meth, methodType.getMethName(), null); // null == void?
//    	methodType.obj = currentMethod;
//    	Tab.openScope();
//    	report_info("Function: " + methodType.getMethName(), methodType);
    	
    	{ report_info("MethodType", methodType); }
    }
    
    public void visit(VoidMethodDecl VoidMethodDecl) { report_info("VoidMethodDecl", VoidMethodDecl); }
    public void visit(NoMethodDecl NoMethodDecl) { report_info("NoMethodDecl", NoMethodDecl); }
    public void visit(MethodDeclarations MethodDeclarations) { report_info("MethodDeclarations", MethodDeclarations); }
    public void visit(VarBrackets VarBrackets) { report_info("VarBrackets", VarBrackets); }
    public void visit(VarNoBrackets VarNoBrackets) { report_info("VarNoBrackets", VarNoBrackets); }
    public void visit(VarSemiError VarSemiError) { report_info("VarSemiError", VarSemiError); }
    public void visit(SemiVar SemiVar) { report_info("SemiVar", SemiVar); }
    public void visit(VarCommaError VarCommaError) { report_info("VarCommaError", VarCommaError); }
    public void visit(CommaVar CommaVar) { report_info("CommaVar", CommaVar); }
    public void visit(SingleVar SingleVar) { report_info("SingleVar", SingleVar); }
    public void visit(VarList VarList) { report_info("VarList", VarList); }
    public void visit(VarDeclaration VarDeclaration) { report_info("VarDeclaration", VarDeclaration); }
    public void visit(NoMethodVarDecls NoMethodVarDecls) { report_info("NoMethodVarDecls", NoMethodVarDecls); }
    public void visit(MethodVarDecls MethodVarDecls) { report_info("MethodVarDecls", MethodVarDecls); }
    public void visit(FalseConst FalseConst) { report_info("FalseConst", FalseConst); }
    public void visit(TrueConst TrueConst) { report_info("TrueConst", TrueConst); }
    public void visit(CharConst CharConst) { report_info("CharConst", CharConst); }
    public void visit(NumConst NumConst) { report_info("NumConst", NumConst); }
    public void visit(Const Const) { report_info("Const", Const); }
    public void visit(ConstCommaError ConstCommaError) { report_info("ConstCommaError", ConstCommaError); }
    public void visit(CommaConst CommaConst) { report_info("CommaConst", CommaConst); }
    public void visit(ConstSemiError ConstSemiError) { report_info("ConstSemiError", ConstSemiError); }
    public void visit(SemiConst SemiConst) { report_info("SemiConst", SemiConst); }
    public void visit(SingleConst SingleConst) { report_info("SingleConst", SingleConst); }
    public void visit(ConstList ConstList) { report_info("ConstList", ConstList); }
    public void visit(ConstDeclaration ConstDeclaration) { report_info("ConstDeclaration", ConstDeclaration); }
    public void visit(ParamVarList ParamVarList) { report_info("ParamVarList", ParamVarList); }
    public void visit(ParamConstList ParamConstList) { report_info("ParamConstList", ParamConstList); }
    public void visit(NoParamItem NoParamItem) { report_info("NoParamItem", NoParamItem); }
    public void visit(ParamItemList ParamItemList) { report_info("ParamItemList", ParamItemList); }
    
}
