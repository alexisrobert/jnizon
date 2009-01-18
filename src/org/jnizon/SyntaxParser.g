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
	FUNCTIONDEF;
}

@header { package org.jnizon; }

start	:	prog+ -> ^(ROOT prog*);

prog	:	NEWLINE!
	|	stmt NEWLINE!
	|	stmt ENDINSTRUCT!
	|	stmt EOF!;

stmt	:	ID WS? ASSIGN WS? expr -> ^(ASSIGNEMENT ID expr)
	|	ID WS? UNASSIGN -> ^(CLEAR ID)
	|	ID OPENBRK csid? CLOSEBRK WS? BIND WS? expr -> ^(FUNCTIONDEF ID expr csid?)
	|	expr;

csid	:	ID UNDERSCORE! ( COMMA! ID UNDERSCORE! )*;
	
csexpr	:	expr ( COMMA! expr )*;


expr:   multExpr ((PLUS^|MINUS^) multExpr)*
    ; 

multExpr
    :   atom (TIMES^ atom)*
    ; 

atom	:	INT
	|	ID
	|	ID OPENBRK csexpr? CLOSEBRK -> ^(FUNCTIONCALL ID csexpr?)
	|   LPAREN! expr RPAREN!;

