parser grammar SyntaxParser;

options {
output = AST;
tokenVocab = SyntaxLexer;
}

@header { package org.jnizon; }

start	:	prog+;

prog	:	NEWLINE
	|	stmt NEWLINE
	|	stmt ENDINSTRUCT;

stmt	:	ID MISCSEP? ASSIGN MISCSEP? expr {System.out.println("Assignation : " + $ID.text + " = " + $expr.text);}
	|	expr;

expr	:	INT {System.out.println("Integer pull : "+$INT.text); }
	|	ID {System.out.println("Variable pull : "+$ID.text); };