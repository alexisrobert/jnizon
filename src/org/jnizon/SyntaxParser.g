parser grammar SyntaxParser;

options {
output = AST;
tokenVocab = SyntaxLexer;
}

tokens {
	START;
	STATEMENT;
	ASSIGN;
	INT_EXPR;
	ID_EXPR;
}

@header { package org.jnizon; }

start	:	prog+ -> ^(START prog*);

prog	:	NEWLINE!
	//|	stmt NEWLINE -> ^(STATEMENT stmt)
	|	stmt ENDINSTRUCT -> ^(STATEMENT stmt);

stmt	:	ID MISCSEP? ASSIGN MISCSEP? expr {System.out.println("Assignation : " + $ID.text + " = " + $expr.text);} -> ^(ASSIGN ID expr);
	|	expr;

expr	:	value=INT {System.out.println("Integer pull : "+$INT.text); } -> ^(INT_EXPR $value)
	|	value=ID {System.out.println("Variable pull : "+$ID.text); } -> ^(ID_EXPR $value);
