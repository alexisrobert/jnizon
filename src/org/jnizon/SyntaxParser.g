parser grammar SyntaxParser;

options {
output = AST;
tokenVocab = SyntaxLexer;
backtrack=true;
}

tokens {
	STATEMENT;
	ASSIGNEMENT;
	CLEAR;
	FUNCTIONDEF;
	CODEBLOCK;
	LISTFUNC;
}

@header { package org.jnizon; }

start	:	expr? -> ^(CODEBLOCK expr?);
csid	:	ID UNDERSCORE! ( COMMA! ID UNDERSCORE! )*;
	
csexpr	:	expr ( COMMA! expr )*;

expr: cexpr WS? (ENDINSTRUCT WS? expr)* -> ^(CODEBLOCK cexpr expr*);

cexpr	:	ID WS? ASSIGN WS? cexpr -> ^(ASSIGNEMENT ID cexpr)
	|	OPENLST csexpr? CLOSELST -> ^(LISTFUNC csexpr?)
	|	ID WS? UNASSIGN -> ^(CLEAR ID)
	|	ID OPENBRK csid? CLOSEBRK WS? BIND WS? plusExpr -> ^(FUNCTIONDEF ID plusExpr csid?)
	|	plusExpr;

plusExpr:   multExpr (PLUS^ multExpr)*
    ; 

multExpr
    :   atom (TIMES^ atom)*
    ; 

atom	:	INT
	|	ID
	|	ID OPENBRK csexpr? CLOSEBRK -> ^(ID csexpr?)
	|   LPAREN! expr RPAREN!;

