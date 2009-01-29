lexer grammar SyntaxLexer;

@header { package org.jnizon; }

ENDINSTRUCT
	:	';';
	
BOOL	:	'True'|'False';

NEWLINE	:	'\r'?'\n';
ID	:	('a'..'z'|'A'..'Z') (('a'..'z'|'A'..'Z'|'0'..'9')+)? ;
INT	:	'0'..'9'+;
WS :	(' ')+ {skip();};
ASSIGN	:	'=';
UNASSIGN:	'=.';
OPENBRK : '[';
CLOSEBRK : ']';
COMMA : ',';
BIND : ':=';
UNDERSCORE : '_';
PLUS : '+';
TIMES : '*';
MINUS : '-';
EQSAME 	:	'===';
LPAREN : '(';
RPAREN : ')';
OPENLST : '{';
CLOSELST : '}';
EXCL	: '!';
GT : '>';
LT : '<';
