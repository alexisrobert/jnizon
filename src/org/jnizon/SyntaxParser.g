parser grammar SyntaxParser;

options {
output = AST;
tokenVocab = SyntaxLexer;
}

tokens {
	ROOT;
	STATEMENT;
	ASSIGNEMENT;
}

@header { package org.jnizon; }

start	:	prog+ -> ^(ROOT prog*);

prog	:	NEWLINE!
	|	stmt NEWLINE!
	|	stmt ENDINSTRUCT!;

stmt	:	ID MISCSEP? ASSIGN MISCSEP? expr -> ^(ASSIGNEMENT ID expr)
	|	expr;
	

expr	:	INT
	|	ID;