package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {

	int localVariablesCount = 0;
	int globalVaribalesCount = 0;
	int temp = 0;
	
	boolean foundMain = false;
	boolean leftSide = true;
	
	Obj currentMethod = null;
	boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());
	
	// We use this for when we declare new vars or constants in table 
	Struct typeStruct = null;
	
	// We use this to check if expression is good
	Struct leftType = null, rightType = null;
	
	Obj currentDesignator = null;
	Obj prevDesignator = null;
	
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
	
	private void setCurrentAndPrevMethod(Obj obj, SyntaxNode node) {
		
		if(currentDesignator == null) currentDesignator = obj;
    	else {
    		prevDesignator = currentDesignator;
    		currentDesignator = obj;
    		
    		int temp0, temp1;
    		
    		if(prevDesignator.getType().getKind() == Struct.Array) {
    			temp0 = prevDesignator.getType().getElemType().getKind();
    		}
    		else {
    			temp0 = prevDesignator.getType().getKind();
    		}
    		
    		if(currentDesignator.getType().getKind() == Struct.Array) {
    			temp1 = currentDesignator.getType().getElemType().getKind();
    		}
    		else {
    			temp1 = currentDesignator.getType().getKind();
    		}
    		
    		if(temp0 != temp1) {
    			report_error("Different type in expression!", node);
    		}
    		
    	}
		
	}
    
    public void visit(ProgName progName) {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	report_info("Program name: " + progName.getProgName(), progName);
    	Tab.openScope();
    }

    public void visit(Program program) {
    	localVariablesCount = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    	globalVaribalesCount = Tab.currentScope.getnVars();
    	
    	if(!foundMain) {
    		report_error("No main function!", program);
    	}
    	else {
    		report_info("Main is declared!", program);
    	}
    	
    	if(localVariablesCount > 256) {
    		report_error("Local varibale count > 256: " + localVariablesCount, program);
    	}
    	else {
    		report_info("Local varibale count: " + localVariablesCount, program);
    	}
    	
    	if(globalVaribalesCount > 65536) {
    		report_error("Global varibale count > 65536: " + globalVaribalesCount, program);
    	}
    	else {
    		report_info("Global varibale count: " + globalVaribalesCount, program);
    	}
    	
    }
    
    public void visit(Designator designator) {
    	report_info("Designator", designator); 
    }
    
    public void visit(MethodDecl methodDecl) {
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	
    	currentMethod = null;
    }
    
//    public void visit(Factor factor) { report_info("Factor: ", factor); }
//    public void visit(DesignatorStatement designatorStatement) { report_info("DesignatorStatement finished!", designatorStatement); }
//    public void visit(MethodVarDecl methodVarDecl) { report_info("MethodVarDecl: ", methodVarDecl); }
//    public void visit(Var var) { report_info("Var: ", var); }
//    public void visit(ConstDecl constDecl) { report_info("ConstDecl", constDecl); }
//    public void visit(VarSemi varSemi) { report_info("VarSemi", varSemi); }
//    public void visit(ParamList paramList) { report_info("ParamList", paramList); }
//    public void visit(ParamItem paramItem){ report_info("ParamItem", paramItem); }
//    public void visit(Expr expr) { report_info("Expr", expr); }
//    public void visit(DesignatorList DesignatorList) { report_info("DesignatorList", DesignatorList); }
//    public void visit(VarDeclList VarDeclList) { report_info("VarDeclList", VarDeclList); }
//    public void visit(DesignatorTemp DesignatorTemp) { report_info("DesignatorTemp", DesignatorTemp); }
//    public void visit(VarDecl VarDecl) { report_info("VarDecl", VarDecl); }
//    public void visit(ConstDeclList ConstDeclList) { report_info("ConstDeclList", ConstDeclList); }
//    public void visit(MethodDeclList MethodDeclList) { report_info("MethodDeclList", MethodDeclList); }
//    public void visit(Statement Statement) { report_info("Statement", Statement); }
//    public void visit(ConstComma ConstComma) { report_info("ConstComma", ConstComma); }
//    public void visit(ConstVal ConstVal) { report_info("ConstVal", ConstVal); }
//    public void visit(NumConstList NumConstList) { report_info("NumConstList", NumConstList); }
//    public void visit(Term Term) { report_info("Term", Term); }
//    public void visit(ConstSemi ConstSemi) { report_info("ConstSemi", ConstSemi); }
//    public void visit(VarComma VarComma) { report_info("VarComma", VarComma); }
//    public void visit(StatementList StatementList) { report_info("StatementList", StatementList); }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(ModOper Modop) { 
    	report_info("Modop", Modop);
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(DivOper Divop) { 
    	report_info("Divop", Divop);
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(MulOper Mulop) { 
    	report_info("Found Mulop:", Mulop);
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(MinusOper Minusop) { 
    	report_info("Found Minusop:", Minusop);
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(AddOper Addop) { 
    	report_info("Found Addop:", Addop); 
    }
    
    /**
     * Set what opertaion is currently
     */
    public void visit(AssignOper Assignop) { 
    	report_info("Found Assignop:", Assignop); 
    	leftSide = false;
    	currentDesignator = null;
    	prevDesignator = null;
    	rightType = null;
    }
    
    public void visit(DesignatorBrackets designatorBrackets) { 
    	
    	Obj obj = Tab.find(designatorBrackets.getName());
    	
    	if(obj == Tab.noObj) {
    		report_error("Error on line " + designatorBrackets.getLine() + " name: " + designatorBrackets.getName() + "not declared! ", null);
    	}
    	
    	if(obj.getType().getKind() != Struct.Array) {
    		report_error("Deisgnator: " + designatorBrackets.getName() + " isn't an Array", designatorBrackets);
    	}
    	
    	Obj temp = new Obj(Obj.Elem, designatorBrackets.getName(), obj.getType().getElemType());
    	
//    	report_info("TEMP je Obj.Elem " + (temp.getKind() == Obj.Elem) + " TEMP nije array " + (temp.getType().getKind() != Struct.Array), designatorBrackets);
    	designatorBrackets.obj = temp;
    	
    	if(currentDesignator.getType().getKind() == Struct.Array) {
    		if(currentDesignator.getType().getElemType().getKind() != Struct.Int) {
    			report_error("Type in brackets isn't int: " + currentDesignator.getName(), designatorBrackets);
    		}
    	}
    	else if(currentDesignator.getType().getKind() != Struct.Int) {
    		report_error("Type in brackets isn't int: " + currentDesignator.getName(), designatorBrackets);
    	}
    	
		currentDesignator = obj;
		
		if(leftSide) {
			leftType = currentDesignator.getType();
		} else {
			rightType = currentDesignator.getType();
		}
		
    	report_info("DesignatorBrackets found: " + designatorBrackets.getName(), designatorBrackets);
    }
    
    public void visit(DesignatorNoBrackets designatorNoBrackets) {
    	
    	Obj obj = Tab.find(designatorNoBrackets.getName());  
    	if(obj == Tab.noObj) {
    		report_error("Error on line " + designatorNoBrackets.getLine() + " name: " + designatorNoBrackets.getName() + "not declared! ", null);
    	}
    	designatorNoBrackets.obj = obj;
    		
    	setCurrentAndPrevMethod(obj, designatorNoBrackets);
    	
		if(leftSide) {
			if(leftType == null) leftType = currentDesignator.getType();
		}
		else {
			if(rightType == null) {
				rightType = currentDesignator.getType();
			} 
		}
			
    	report_info("DesignatorNoBrackets found: " + designatorNoBrackets.getName(), designatorNoBrackets);
    }
    
//    public void visit(Expression Expression) { report_info("Expression", Expression); }
    
    public void visit(NewFuncExpr NewFuncExpr) { 
    	
    	Obj typeNode = Tab.find(NewFuncExpr.getType().getTypeName());
    	
    	if(typeNode == Tab.noObj) {
    		report_error("Not found type " + NewFuncExpr.getType().getTypeName() + " in symbol table!", NewFuncExpr);
    	}
    	else {
    		if(Obj.Type == typeNode.getKind()) {
    			
    			if(leftType.getKind() == Struct.Array) {
    				
	    			if(leftType.getElemType().getKind() == typeNode.getType().getKind()) {
	    				report_info("Found NewFuncExpr: ", NewFuncExpr);
	    				rightType = new Struct(leftType.getElemType().getKind());
	    			}
	    			else {
	    				report_error("Wrong NewFuncExpr can't create diffenrt types", NewFuncExpr);
	    			}
    			} else {
    				report_error("Left side isn't Array type!", NewFuncExpr);
    			}
	    			
    		} else {
    			report_error("Error: Name " + NewFuncExpr.getType().getTypeName() + " isn't a type!", NewFuncExpr);
    			
    		}
    	}
    	
    }
    
    public void visit(FalseFactorConst FalseFactorConst) { 
    	
    	Obj temp = new Obj(Obj.Var, FalseFactorConst.getF1(), new Struct(Struct.Bool));
    	setCurrentAndPrevMethod(temp, FalseFactorConst);
    	
    	if(leftSide) {
			if(leftType == null) {
				leftType = new Struct(Struct.Bool);
			} 
		}
		else {
			if(rightType == null) {
				rightType = new Struct(Struct.Bool);
			} 
		}
    	
		report_info("CharFactorConst: " + FalseFactorConst.getF1(), FalseFactorConst);
    }
    
    public void visit(TrueFactorConst TrueFactorConst) {
    	
    	Obj temp = new Obj(Obj.Var, TrueFactorConst.getT1(), new Struct(Struct.Bool));
    	setCurrentAndPrevMethod(temp, TrueFactorConst);
    	
    	if(leftSide) {
			if(leftType == null) {
				leftType = new Struct(Struct.Bool);
			} 
		}
		else {
			if(rightType == null) {
				rightType = new Struct(Struct.Bool);
			} 
		}
    	
		report_info("CharFactorConst: " + TrueFactorConst.getT1(), TrueFactorConst);
    	
    }
    
    public void visit(CharFactorConst CharFactorConst) { 
    	
    	Obj temp = new Obj(Obj.Var, CharFactorConst.getCharacter().toString(), new Struct(Struct.Char));
    	setCurrentAndPrevMethod(temp, CharFactorConst);
    	
    	if(leftSide) {
			if(leftType == null) {
				leftType = new Struct(Struct.Char);
			} 
		}
		else {
			if(rightType == null) {
				rightType = new Struct(Struct.Char);
			} 
		}
    	
		report_info("CharFactorConst: " + CharFactorConst.getCharacter(), CharFactorConst);
    	
    }
    
    public void visit(NumFactorConst NumFactorConst) { 
    	
    	Obj temp = new Obj(Obj.Var, NumFactorConst.getN1().toString(), new Struct(Struct.Int));
    	setCurrentAndPrevMethod(temp, NumFactorConst);
    	
    	if(leftSide) {
			if(leftType == null) {
				leftType = new Struct(Struct.Int);
			} 
		}
		else {
			if(rightType == null) {
				rightType = new Struct(Struct.Int);
			} 
		}
    	
		report_info("NumFactorConst: " + NumFactorConst.getN1(), NumFactorConst);
    	
    }
    
//    public void visit(DisgnatorNoPars DisgnatorNoPars) { report_info("DisgnatorNoPars", DisgnatorNoPars); }
//    public void visit(SignleTerm SignleTerm) { report_info("SignleTerm", SignleTerm); }
    
//    public void visit(TermExpr TermExpr) { 
//    	report_info("TermExpr", TermExpr);
//    }
    
//    public void visit(SingleNegativeExpr SingleNegativeExpr) { 	report_info("SingleNegativeExpr", SingleNegativeExpr); }
//    public void visit(SingleExpr SingleExpr) { report_info("SingleExpr", SingleExpr); }
//    public void visit(PositiveExpr PositiveExpr) { report_info("PositiveExpr", PositiveExpr); }
//    public void visit(SingleDesignatorList SingleDesignatorList) { report_info("SingleDesignatorList", SingleDesignatorList); }
//    public void visit(DesignatorLists DesignatorLists) { report_info("DesignatorLists", DesignatorLists); }
//    public void visit(NoDesignatorListItem NoDesignatorListItem) { report_info("NoDesignatorListItem", NoDesignatorListItem); }
//    public void visit(DesignatorListItem DesignatorListItem) { report_info("DesignatorListItem", DesignatorListItem); }
//    public void visit(DesignatorStatementError DesignatorStatementError) { report_info("DesignatorStatementError", DesignatorStatementError); }
//    public void visit(DesignatorStatementBrackets DesignatorStatementBrackets) { report_info("DesignatorStatementBrackets", DesignatorStatementBrackets); }
    
    public void visit(DesignatorDEC DesignatorDEC) { 
    	
    	Designator des = DesignatorDEC.getDesignator();
    	
    	if(des.obj.getType().getKind() == Struct.Array) {
    		if(des.obj.getType().getElemType().getKind() == Struct.Int) {
    			report_info("Found DesignatorDEC that can DEC: " + des.obj.getName(), DesignatorDEC);
    		}
    		else {
    			report_error("Found DesignatorDEC that can't be DEC: " + des.obj.getName(), DesignatorDEC);
    		}
    	}
    	else if(des.obj.getType().getKind() == Struct.Int) {
    		report_info("Found DesignatorDEC that can DEC: " + des.obj.getName(), DesignatorDEC);
    	} else {
    		report_error("Found DesignatorDEC that can't be DEC: " + des.obj.getName(), DesignatorDEC);
    	}
    	
    	leftType = rightType = new Struct(Struct.Int);
    }
    
    public void visit(DesignatorINC DesignatorINC) { 
    	 
    	Designator des = DesignatorINC.getDesignator();
    	
    	if(des.obj.getType().getKind() == Struct.Array) {
    		if(des.obj.getType().getElemType().getKind() == Struct.Int) {
    			report_info("Found DesignatorINC that can INC: " + des.obj.getName(), DesignatorINC);
    		}
    		else {
    			report_error("Found DesignatorINC that can't be INC: " + des.obj.getName(), DesignatorINC);
    		}
    	}
    	else if(des.obj.getType().getKind() == Struct.Int) {
    		report_info("Found DesignatorINC that can INC: " + des.obj.getName(), DesignatorINC);
    	} else {
    		report_error("Found DesignatorINC that can't be INC: " + des.obj.getName(), DesignatorINC);
    	}
    	
    	leftType = rightType = new Struct(Struct.Int);
    }
    
    /**
     * We reset assignments
     */
    public void visit(DesignatorAssign DesignatorAssign) { 
    	 	
		report_info("Found DesignatorAssign: " + currentDesignator.getName(), DesignatorAssign);
		
		int temp0, temp1;
		
		if(leftType.getKind() == Struct.Array) {
			temp0 = leftType.getElemType().getKind();
		} else {
			temp0 = leftType.getKind();
		}
		
		if(rightType.getKind() == Struct.Array) {
			temp1 = rightType.getElemType().getKind();
		} else {
			temp1 = rightType.getKind();
		}
		
		if(temp1 == temp0) {
			report_info("Good Assignment!", DesignatorAssign);
		} else {
			report_error("Bad Assignment!", DesignatorAssign);
		}
		
    }
    
//    public void visit(NoNumConsts NoNumConsts) { report_info("NoNumConsts", NoNumConsts); }
//    public void visit(NumConsts NumConsts) { report_info("NumConsts", NumConsts); }
    
    public void visit(PrintStmt print) {
    	
    	if(leftType.getKind() == Struct.Array) {
    		if(leftType.getElemType().getKind() != Struct.Int && leftType.getElemType().getKind() != Struct.Char) {
    			report_error("Wrong type in PrintStmt, only int and char can be printed!", print);
    		}
    	} else {
    		if(leftType.getKind() != Struct.Int && leftType.getKind() != Struct.Char) {
    			report_error("Wrong type in PrintStmt, only int and char can be printed!", print);
    		}
    	}
    	
    	
    	currentDesignator = null;
    	prevDesignator = null;
    	leftSide = true;
    	rightType = null;
    	leftType = null;
		{ report_info("PrintStmt!", print); }
	}
    
    public void visit(ReadStmt ReadStmt) { 
    	currentDesignator = null;
    	prevDesignator = null;
    	leftSide = true;
    	rightType = null;
    	leftType = null;
    	report_info("ReadStmt!", ReadStmt); 
    }
    
    public void visit(DesignatorStmt DesignatorStmt) { 
    	report_info("DesignatorStmt finished!", DesignatorStmt);
    	currentDesignator = null;
    	prevDesignator = null;
    	leftSide = true;
    	rightType = null;
    	leftType = null;
    }
    
//    public void visit(NoStmt NoStmt) { report_info("NoStmt", NoStmt); }
//    public void visit(Statements Statements) { report_info("Statements", Statements); }
    
    /**
     * Checks if there is an type in table, if there isn't returns error.
     */
    public void visit(Type type) {
    
    	Obj typeNode = Tab.find(type.getTypeName());
    	
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
    	
    	if(methodType.getMethName().equals("main")) {
    		foundMain = true;
    	}
    	
    	Tab.openScope();
    	report_info("Function: " + methodType.getMethName(), methodType);
 
    }
    
//    public void visit(VoidMethodDecl VoidMethodDecl) { report_info("VoidMethodDecl", VoidMethodDecl); }
//    public void visit(NoMethodDecl NoMethodDecl) { report_info("NoMethodDecl", NoMethodDecl); }
//    public void visit(MethodDeclarations MethodDeclarations) { report_info("MethodDeclarations", MethodDeclarations); }
    
    public void visit(VarBrackets varBrackets) { 
    	report_info("Declared variable VarBrackets: " + varBrackets.getVarName(), varBrackets);
    	
    	Struct temp = new Struct(Struct.Array);
    	temp.setElementType(typeStruct);
    	
    	if(Tab.find(varBrackets.getVarName()) != Tab.noObj) {
    		report_error("VarBrackets already declared!" + varBrackets.getVarName(), varBrackets);
    	}
    	Obj varNode = Tab.insert(Obj.Var, varBrackets.getVarName(), temp);
    	varBrackets.obj = varNode;
    	Tab.currentScope.addToLocals(varNode);
    	
    	if(varNode.getLevel() > 0) {
    		if(Tab.currentScope.getnVars() > 256) {
    			report_error("Local varibale count > 256: " + Tab.currentScope.getnVars(), varBrackets);
    		}
    		else {
    			report_info("Current scope: " + varNode.getLevel() + ", variable count: " + Tab.currentScope.getnVars(), varBrackets);
    		}
    		
    	} else {
    		if(Tab.currentScope.getnVars() > 65536) {
    			report_error("Global varibale count > 65536: " + Tab.currentScope.getnVars(), varBrackets);
    		}
    		else {
    			report_info("Current scope: " + varNode.getLevel() + ", variable count: " + Tab.currentScope.getnVars(), varBrackets);
    		}
    		
    	}
    }
    
    public void visit(VarNoBrackets varNoBrackets) { 
    	report_info("Declared variable VarNoBrackets: " + varNoBrackets.getVarName(), varNoBrackets); 
    	
    	if(Tab.find(varNoBrackets.getVarName()) != Tab.noObj) {
    		report_error("VarNoBrackets already declared!" + varNoBrackets.getVarName(), varNoBrackets);
    	}
    	Obj varNode = Tab.insert(Obj.Var, varNoBrackets.getVarName(), typeStruct);
    	varNoBrackets.obj = varNode;
    	Tab.currentScope.addToLocals(varNode);
    	
    	if(varNode.getLevel() > 0) {
    		if(Tab.currentScope.getnVars() > 256) {
    			report_error("Local varibale count > 256: " + Tab.currentScope.getnVars(), varNoBrackets);
    		}
    		else {
    			report_info("Current scope: " + varNode.getLevel() + ", variable count: " + Tab.currentScope.getnVars(), varNoBrackets);
    		}
    		
    	} else {
    		if(Tab.currentScope.getnVars() > 65536) {
    			report_error("Global varibale count > 65536: " + Tab.currentScope.getnVars(), varNoBrackets);
    		}
    		else {
    			report_info("Current scope: " + varNode.getLevel() + ", variable count: " + Tab.currentScope.getnVars(), varNoBrackets);
    		}
    		
    	}
    }
    
//    public void visit(VarSemiError VarSemiError) { report_info("VarSemiError", VarSemiError); }
//    public void visit(SemiVar SemiVar) { report_info("SemiVar", SemiVar); }
//    public void visit(VarCommaError VarCommaError) { report_info("VarCommaError", VarCommaError); }
//    public void visit(CommaVar CommaVar) { report_info("CommaVar", CommaVar); }
//    public void visit(SingleVar SingleVar) { report_info("SingleVar", SingleVar); }
//    public void visit(VarList VarList) { report_info("VarList", VarList); }
    
    public void visit(VarDeclaration varDeclaration) { 
    	report_info("Type in VarDeclaration " + varDeclaration.getType().getTypeName(), varDeclaration); 
    }
    
    public void visit(SingleMethodVarDecl SingleMethodVarDecl) { report_info("SingleMethodVarDecl", SingleMethodVarDecl); }
    public void visit(MethodVarDecls MethodVarDecls) { 
    	report_info("MethodVarDecls", MethodVarDecls); 
    }
    
    public void visit(FalseConst FalseConst) { 
    	
    	if(rightType == null) {
    		rightType = new Struct(Struct.Bool);
    	}
    	
    	if(typeStruct.getKind() == Struct.Bool) {
    		report_info("Declared FalseConst!", FalseConst);
    	} else {
    		report_error("Bad declaration! FalseConst isn't good type!", FalseConst);
    	}
    }
    
    public void visit(TrueConst TrueConst) { 
    	
    	if(rightType == null) {
    		rightType = new Struct(Struct.Bool);
    	}
    	
    	if(typeStruct.getKind() == Struct.Bool) {
    		report_info("Declared TrueConst!", TrueConst);
    	} else {
    		report_error("Bad declaration! TrueConst isn't good type!", TrueConst);
    	}
    }
    
    public void visit(CharConst CharConst) { 
    	
    	if(rightType == null) {
    		rightType = new Struct(Struct.Char);
    	}
    	
    	if(typeStruct.getKind() == Struct.Char) {
    		report_info("Declared CharConst!", CharConst);
    	} else {
    		report_error("Bad declaration! CharConst isn't good type!", CharConst);
    	}
    	
    }
    
    public void visit(NumConst NumConst) { 
    	
    	if(rightType == null) {
    		rightType = new Struct(Struct.Int);
    	}
    	
    	if(typeStruct.getKind() == Struct.Int) {
    		report_info("Declared NumConst!", NumConst);
    	}  else {
    		report_error("Bad declaration! NumConst isn't good type!", NumConst);
    	}
    	 
    }
    
    public void visit(Const Const) { 
    	report_info("Declared constant Const: " + Const.getConstName(), Const);
    	
    	if(Tab.find(Const.getConstName()) != Tab.noObj) {
    		report_error("Constant already declared!" + Const.getConstName(), Const);
    	}
    	
    	report_info("ConstVal " + (Const.getConstVal().getClass() == NumConst.class), Const); 
    	
    	Obj constNode = Tab.insert(Obj.Con, Const.getConstName(), typeStruct);
    	Const.struct = typeStruct;
    	
    	Tab.currentScope.addToLocals(constNode);
    	
    	if(constNode.getLevel() > 0) {
    		if(Tab.currentScope.getnVars() > 256) {
    			report_error("Local varibale count > 256: " + Tab.currentScope.getnVars(), Const);
    		}
    		else {
    			report_info("Current scope: " + constNode.getLevel() + ", variable count: " + Tab.currentScope.getnVars(), Const);
    		}
    	} else {
    		if(Tab.currentScope.getnVars() > 65536) {
    			report_error("Global varibale count > 65536: " + Tab.currentScope.getnVars(), Const);
    		}
    		else {
    			report_info("Current scope: " + constNode.getLevel() + ", variable count: " + Tab.currentScope.getnVars(), Const);
    		}
    	}
    	
    }
    
//    public void visit(ConstCommaError ConstCommaError) { report_info("ConstCommaError", ConstCommaError); }
//    public void visit(CommaConst CommaConst) { report_info("CommaConst", CommaConst); }
//    public void visit(ConstSemiError ConstSemiError) { report_info("ConstSemiError", ConstSemiError); }
//    public void visit(SemiConst SemiConst) { report_info("SemiConst", SemiConst); }
//    public void visit(SingleConst SingleConst) { report_info("SingleConst", SingleConst); }
//    public void visit(ConstList ConstList) { report_info("ConstList", ConstList); }
//    public void visit(ConstDeclaration ConstDeclaration) { report_info("ConstDeclaration", ConstDeclaration); }
//    public void visit(ParamVarList ParamVarList) { report_info("ParamVarList", ParamVarList); }
//    public void visit(ParamConstList ParamConstList) { report_info("ParamConstList", ParamConstList); }
//    public void visit(NoParamItem NoParamItem) { report_info("NoParamItem", NoParamItem); }
//    public void visit(ParamItemList ParamItemList) { report_info("ParamItemList", ParamItemList); }
	
    public boolean passed(){
    	return !errorDetected;
    }
}
