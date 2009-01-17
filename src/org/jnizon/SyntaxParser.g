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
	FUNCTIONCALL;
}

@header { package org.jnizon; }

start	:	prog+ -> ^(ROOT prog*);

prog	:	NEWLINE!
	|	stmt NEWLINE!
	|	stmt ENDINSTRUCT!;

stmt	:	ID MISCSEP? ASSIGN MISCSEP? expr -> ^(ASSIGNEMENT ID expr)
	|	expr;
	
csexpr	:	expr ( COMMA! expr )*;

expr	:	INT
	|	ID
	|	ID OPENBRK csexpr? CLOSEBRK -> ^(FUNCTIONCALL ID csexpr?);

