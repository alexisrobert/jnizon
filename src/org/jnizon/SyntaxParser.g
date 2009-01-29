parser grammar SyntaxParser;

options {
output = AST;
tokenVocab = SyntaxLexer;
backtrack=true;
}

tokens {
	STATEMENT;
	SET;
	CLEAR;
	FUNCTIONDEF;
	CODEBLOCK;
	LIST;
	SAMEQ;
	NOT;
	BLANK;
	PATTERN;
	FUNCTIONCALL;
	SETDELAYED;
	GREATER;
	LESS;
	MINUSONE;
	PART;
}

@header { package org.jnizon; }

start	:	expr? -> ^(CODEBLOCK expr?);

csid	:	ID UNDERSCORE! ( COMMA! ID UNDERSCORE! )*;
	
csexpr	:	expr ( COMMA! expr )*;

expr: cexpr WS? (ENDINSTRUCT WS? expr)* -> ^(CODEBLOCK cexpr expr*);

cexpr	:	plusExpr WS? ASSIGN WS? cexpr -> ^(FUNCTIONCALL SET plusExpr cexpr)
	|	plusExpr WS? BIND WS? cexpr -> ^(FUNCTIONCALL SETDELAYED plusExpr cexpr)
	|	ID WS? UNASSIGN -> ^(CLEAR ID)
//	|	ID OPENBRK csid? CLOSEBRK WS? BIND WS? plusExpr -> ^(FUNCTIONDEF ID plusExpr csid?)
	|	atom (EQSAME atom)+ -> ^(SAMEQ atom atom+)
	|	atom OPENBRK OPENBRK plusExpr CLOSEBRK CLOSEBRK -> ^(FUNCTIONCALL PART atom plusExpr)
	|	conditional
	|	plusExpr;

conditional: e1=plusExpr GT e2=plusExpr -> ^(FUNCTIONCALL GREATER $e1 $e2)
	|	e1=plusExpr LT e2=plusExpr -> ^(FUNCTIONCALL LESS $e1 $e2)
	;

plusExpr:   multExpr (PLUS multExpr)+ -> ^(FUNCTIONCALL PLUS multExpr multExpr+)
	|	multExpr
    ; 

multExpr
    :   atom (TIMES atom)+ -> ^(FUNCTIONCALL TIMES atom atom+)
    | atom
    ; 

atom	:	INT
	|	BOOL
	|	MINUS atom -> ^(FUNCTIONCALL TIMES MINUSONE atom)
	|	ID
	|	OPENLST csexpr? CLOSELST -> ^(FUNCTIONCALL LIST csexpr?) 
	|	UNDERSCORE -> ^(FUNCTIONCALL BLANK)
	|	ID UNDERSCORE -> ^(FUNCTIONCALL PATTERN ID ^(FUNCTIONCALL BLANK))
	|	ID OPENBRK csexpr? CLOSEBRK -> ^(FUNCTIONCALL ID csexpr?)
	|	EXCL WS? BOOL -> ^(NOT BOOL)
	|   LPAREN! expr RPAREN!;

