parser grammar SyntaxParser;

options {
output = AST;
tokenVocab = SyntaxLexer;
backtrack=true;
}

tokens {
	ROOT;
	STATEMENT;
	ASSIGNEMENT;
	CLEAR;
	FUNCTIONCALL;
}

@header { package org.jnizon; }

start	:	prog+ -> ^(ROOT prog*);

prog	:	NEWLINE!
	|	stmt NEWLINE!
	|	stmt ENDINSTRUCT!
	|	stmt EOF!;

stmt	:	ID MISCSEP? ASSIGN MISCSEP? expr -> ^(ASSIGNEMENT ID expr)
	|	ID MISCSEP? UNASSIGN -> ^(CLEAR ID)
	|	expr;
	
csexpr	:	expr ( COMMA! expr )*;

expr	:	INT
	|	ID
	|	ID OPENBRK csexpr? CLOSEBRK -> ^(FUNCTIONCALL ID csexpr?);

