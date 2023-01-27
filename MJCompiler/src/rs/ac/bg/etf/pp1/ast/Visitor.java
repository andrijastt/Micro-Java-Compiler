// generated with ast extension for cup
// version 0.8
// 27/0/2023 14:29:3


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Designator Designator);
    public void visit(MethodDecl MethodDecl);
    public void visit(Factor Factor);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(MethodVarDecl MethodVarDecl);
    public void visit(Var Var);
    public void visit(ConstDecl ConstDecl);
    public void visit(VarSemi VarSemi);
    public void visit(ParamList ParamList);
    public void visit(ParamItem ParamItem);
    public void visit(Expr Expr);
    public void visit(DesignatorList DesignatorList);
    public void visit(VarDeclList VarDeclList);
    public void visit(DesignatorTemp DesignatorTemp);
    public void visit(VarDecl VarDecl);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(ConstComma ConstComma);
    public void visit(ConstVal ConstVal);
    public void visit(NumConstList NumConstList);
    public void visit(Term Term);
    public void visit(ConstSemi ConstSemi);
    public void visit(VarComma VarComma);
    public void visit(StatementList StatementList);
    public void visit(Modop Modop);
    public void visit(Divop Divop);
    public void visit(Mulop Mulop);
    public void visit(Minusop Minusop);
    public void visit(Addop Addop);
    public void visit(Assignop Assignop);
    public void visit(DesignatorBrackets DesignatorBrackets);
    public void visit(DesignatorNoBrackets DesignatorNoBrackets);
    public void visit(Expression Expression);
    public void visit(NewFuncExpr NewFuncExpr);
    public void visit(FalseFactorConst FalseFactorConst);
    public void visit(TrueFactorConst TrueFactorConst);
    public void visit(CharFactorConst CharFactorConst);
    public void visit(NumFactorConst NumFactorConst);
    public void visit(DisgnatorNoPars DisgnatorNoPars);
    public void visit(SignleTerm SignleTerm);
    public void visit(TermExpr TermExpr);
    public void visit(SingleNegativeExpr SingleNegativeExpr);
    public void visit(SingleExpr SingleExpr);
    public void visit(PositiveExpr PositiveExpr);
    public void visit(NegativeExpr NegativeExpr);
    public void visit(SingleDesignatorList SingleDesignatorList);
    public void visit(DesignatorLists DesignatorLists);
    public void visit(NoDesignatorTemp NoDesignatorTemp);
    public void visit(DesignatroTemp DesignatroTemp);
    public void visit(DesignatorStatementError DesignatorStatementError);
    public void visit(DesignatorStatementBrackets DesignatorStatementBrackets);
    public void visit(DesignatorDEC DesignatorDEC);
    public void visit(DesignatorINC DesignatorINC);
    public void visit(DesignatorAssign DesignatorAssign);
    public void visit(NoNumConsts NoNumConsts);
    public void visit(NumConsts NumConsts);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(Type Type);
    public void visit(MethodType MethodType);
    public void visit(VoidMethodDecl VoidMethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(VarBrackets VarBrackets);
    public void visit(VarNoBrackets VarNoBrackets);
    public void visit(VarSemiError VarSemiError);
    public void visit(SemiVar SemiVar);
    public void visit(VarCommaError VarCommaError);
    public void visit(CommaVar CommaVar);
    public void visit(SingleVar SingleVar);
    public void visit(VarList VarList);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(NoMethodVarDecls NoMethodVarDecls);
    public void visit(MethodVarDecls MethodVarDecls);
    public void visit(FalseConst FalseConst);
    public void visit(TrueConst TrueConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(Const Const);
    public void visit(ConstCommaError ConstCommaError);
    public void visit(CommaConst CommaConst);
    public void visit(ConstSemiError ConstSemiError);
    public void visit(SemiConst SemiConst);
    public void visit(SingleConst SingleConst);
    public void visit(ConstList ConstList);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(ParamVarList ParamVarList);
    public void visit(ParamConstList ParamConstList);
    public void visit(NoParamItem NoParamItem);
    public void visit(ParamItemList ParamItemList);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
