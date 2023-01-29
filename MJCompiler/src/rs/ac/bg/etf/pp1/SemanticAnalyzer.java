package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {

	int printCallCount = 0;
	int localVariablesCount = 0;
	int globalVaribalesCount = 0;
	int temp = 0;
	
	final private int NOOP = -1;
	final private int ASSIGN = 0;
	final private int ADD = 1;
	final private int MINUS = 2;
	final private int DIV = 3;
	final private int MOD = 4;
	final private int MUL = 5;
	
	private int operation = NOOP;
	
	boolean brackets = false;
	
	Obj currentMethod = null;
	Obj currentDesignator = null;
	boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());
	
	// We remember the last type for variables
	Struct typeStruct = null;
	
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
    	report_info("Designator", designator); 
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
    /**
     * Set what opertaion is currently
     */
    public void visit(Modop Modop) { 
    	report_info("Modop", Modop);
    	operation = MOD;
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(Divop Divop) { 
    	report_info("Divop", Divop);
    	operation = DIV;
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(Mulop Mulop) { 
    	report_info("Found Mulop:", Mulop);
    	operation = MUL;
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(Minusop Minusop) { 
    	report_info("Found Minusop:", Minusop);
    	operation = MINUS;
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(Addop Addop) { 
    	report_info("Found Addop:", Addop); 
    	operation = ADD; 
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(Assignop Assignop) { 
    	report_info("Found Assignop:", Assignop); 
    	operation = ASSIGN;
    }
    
    public void visit(DesignatorBrackets designatorBrackets) { 
    	
    	Obj obj = Tab.find(designatorBrackets.getName());  
    	brackets = true;
    	if(obj == Tab.noObj) {
    		report_error("Error on line " + designatorBrackets.getLine() + " name: " + designatorBrackets.getName() + "not declared! ", null);
    	}
    	designatorBrackets.obj = obj;
    			
		currentDesignator = obj;
	
		if(obj.getType().getKind() == currentDesignator.getType().getKind()) {
			report_info("Same kind!", designatorBrackets);
		} else {
			if(!brackets)
				report_error("Error name: " + designatorBrackets.getName() + " not same type as: " + currentDesignator.getName(), null);
		}
    	
    	
    	report_info("DesignatorBrackets found: " + designatorBrackets.getName(), designatorBrackets);
    }
    
    public void visit(DesignatorNoBrackets designatorNoBrackets) {
    	
    	Obj obj = Tab.find(designatorNoBrackets.getName());  
    	if(obj == Tab.noObj) {
    		report_error("Error on line " + designatorNoBrackets.getLine() + " name: " + designatorNoBrackets.getName() + "not declared! ", null);
    	}
    	designatorNoBrackets.obj = obj;
    	
    	if(currentDesignator == null) {
    		currentDesignator = obj;
    	} else {
    		if(obj.getType().getKind() == currentDesignator.getType().getKind()) {
    			report_info("Same kind!", designatorNoBrackets);
    		} else {
    			if(!brackets)
    				report_error("Error name: " + designatorNoBrackets.getName() + " not same type as: " + currentDesignator.getName(), null);
    		}
    	}
    	
    	report_info("DesignatorNoBrackets found: " + designatorNoBrackets.getName(), designatorNoBrackets);
    }
    
    public void visit(Expression Expression) { report_info("Expression", Expression); }
    public void visit(NewFuncExpr NewFuncExpr) { report_info("NewFuncExpr", NewFuncExpr); }
    public void visit(FalseFactorConst FalseFactorConst) { report_info("FalseFactorConst", FalseFactorConst); }
    
    public void visit(TrueFactorConst TrueFactorConst) {
    	
    	report_info("TrueFactorConst", TrueFactorConst); 
    }
    
    public void visit(CharFactorConst CharFactorConst) { 
    	
    	if(currentDesignator.getKind() == Struct.Char && currentDesignator != null) {
    		report_info("CharFactorConst: " + CharFactorConst.getCharacter(), CharFactorConst);
    	}
    	else {
    		report_error("Error CharFactorConst assignment is bad: " + currentDesignator.getName() + " isn't char", CharFactorConst);
    	}
    	
    	
    }
    
    public void visit(NumFactorConst NumFactorConst) { 
    	
    	if(currentDesignator != null)
	    	if(currentDesignator.getType().getKind() == Struct.Int) {
	    		report_info("NumFactorConst:" + NumFactorConst.getN1(), NumFactorConst);
	    	}
	    	else {
	    		report_error("Error NumFactorConst assignment is bad: " + currentDesignator.getName() + " isn't int", NumFactorConst);
	    	}
    	
    }
    
    public void visit(DisgnatorNoPars DisgnatorNoPars) { report_info("DisgnatorNoPars", DisgnatorNoPars); }
    public void visit(SignleTerm SignleTerm) { report_info("SignleTerm", SignleTerm); }
    public void visit(TermExpr TermExpr) { report_info("TermExpr", TermExpr); }
    public void visit(SingleNegativeExpr SingleNegativeExpr) { report_info("SingleNegativeExpr", SingleNegativeExpr); }
    public void visit(SingleExpr SingleExpr) { report_info("SingleExpr", SingleExpr); }
    public void visit(PositiveExpr PositiveExpr) { report_info("PositiveExpr", PositiveExpr); }
    public void visit(SingleDesignatorList SingleDesignatorList) { report_info("SingleDesignatorList", SingleDesignatorList); }
    public void visit(DesignatorLists DesignatorLists) { report_info("DesignatorLists", DesignatorLists); }
    public void visit(NoDesignatorTemp NoDesignatorTemp) { report_info("NoDesignatorTemp", NoDesignatorTemp); }
    public void visit(DesignatroTemp DesignatroTemp) { report_info("DesignatroTemp", DesignatroTemp); }
    public void visit(DesignatorStatementError DesignatorStatementError) { report_info("DesignatorStatementError", DesignatorStatementError); }
    public void visit(DesignatorStatementBrackets DesignatorStatementBrackets) { report_info("DesignatorStatementBrackets", DesignatorStatementBrackets); }
    
    public void visit(DesignatorDEC DesignatorDEC) { 
    	
    	Designator des = DesignatorDEC.getDesignator();
    	if(des.obj.getType().getKind() == Struct.Int) {
    		report_info("Found DesignatorDEC that can DEC: " + des.obj.getName(), DesignatorDEC);
    	} else {
    		report_error("Found DesignatorDEC that can't be DEC: " + des.obj.getName(), DesignatorDEC);
    	} 
    }
    
    public void visit(DesignatorINC DesignatorINC) { 
    	 
    	Designator des = DesignatorINC.getDesignator();
    	if(des.obj.getType().getKind() == Struct.Int) {
    		report_info("Found DesignatorINC that can INC: " + des.obj.getName(), DesignatorINC);
    	} else {
    		report_error("Found DesignatorINC that can't be INC: " + des.obj.getName(), DesignatorINC);
    	}
    	
    }
    
    /**
     * We reset assignments
     */
    public void visit(DesignatorAssign DesignatorAssign) { 
    	 
    	if(currentDesignator != null)
    		report_info("Found DesignatorAssign: " + currentDesignator.getName(), DesignatorAssign);
    	currentDesignator = null;
    	operation = NOOP;
    	brackets = false;
    	
    }
    
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
    
    /**
     * Checks if there is an type in table, if there isn't returns error.
     */
    public void visit(Type type) {
    
    	Obj typeNode = Tab.find(type.getTypeName());
    	
//    	if(type.getTypeName().equals("bool")) {
//    		typeNode = Tab.find("int");
//    	}
    	
    	if(typeNode == Tab.noObj) {
    		report_error("Not found type " + type.getTypeName() + " in symbol table!", null);
    		typeStruct = type.struct = Tab.noType;
    	}
    	else {
    		if(Obj.Type == typeNode.getKind()) {
    			typeStruct = type.struct = typeNode.getType();
    		} else {
    			report_error("Error: Name " + type.getTypeName() + " isn't a type!", type);
    			typeStruct = type.struct = Tab.noType;
    		}
    	}
    	
    }
    
    public void visit(MethodType methodType) {
    	
    	currentMethod = Tab.insert(Obj.Meth, methodType.getMethName(), new Struct(0)); 
    	methodType.obj = currentMethod;
    	Tab.openScope();
    	report_info("Function: " + methodType.getMethName(), methodType);
 
    }
    
    public void visit(VoidMethodDecl VoidMethodDecl) { report_info("VoidMethodDecl", VoidMethodDecl); }
    public void visit(NoMethodDecl NoMethodDecl) { report_info("NoMethodDecl", NoMethodDecl); }
    public void visit(MethodDeclarations MethodDeclarations) { report_info("MethodDeclarations", MethodDeclarations); }
    
    public void visit(VarBrackets varBrackets) { 
    	report_info("Declared variable VarBrackets: " + varBrackets.getVarName(), varBrackets); 
    	Obj varNode = Tab.insert(Obj.Var, varBrackets.getVarName(), typeStruct);
    }
    
    public void visit(VarNoBrackets varNoBrackets) { 
    	report_info("Declared variable VarNoBrackets: " + varNoBrackets.getVarName(), varNoBrackets); 
    	Obj varNode = Tab.insert(Obj.Var, varNoBrackets.getVarName(), typeStruct);
    }
    
    public void visit(VarSemiError VarSemiError) { report_info("VarSemiError", VarSemiError); }
    public void visit(SemiVar SemiVar) { report_info("SemiVar", SemiVar); }
    public void visit(VarCommaError VarCommaError) { report_info("VarCommaError", VarCommaError); }
    public void visit(CommaVar CommaVar) { report_info("CommaVar", CommaVar); }
    public void visit(SingleVar SingleVar) { report_info("SingleVar", SingleVar); }
    public void visit(VarList VarList) { report_info("VarList", VarList); }
    
    public void visit(VarDeclaration varDeclaration) { 
    	report_info("Type in VarDeclaration " + varDeclaration.getType().getTypeName(), varDeclaration); 
    }
    
    public void visit(SingleMethodVarDecl SingleMethodVarDecl) { report_info("SingleMethodVarDecl", SingleMethodVarDecl); }
    public void visit(MethodVarDecls MethodVarDecls) { 
    	report_info("MethodVarDecls", MethodVarDecls); 
    }
    public void visit(FalseConst FalseConst) { report_info("FalseConst", FalseConst); }
    public void visit(TrueConst TrueConst) { report_info("TrueConst", TrueConst); }
    public void visit(CharConst CharConst) { report_info("CharConst", CharConst); }
    public void visit(NumConst NumConst) { report_info("NumConst", NumConst); }
    
    public void visit(Const Const) { 
    	report_info("Declared constant Const: " + Const.getConstName(), Const);
    	Obj varNode = Tab.insert(Obj.Con, Const.getConstName(), typeStruct);
    }
    
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
