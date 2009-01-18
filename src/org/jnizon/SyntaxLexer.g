lexer grammar SyntaxLexer;

@header { package org.jnizon; }

ENDINSTRUCT
	:	';';
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
LPAREN : '(';
RPAREN : ')';
DBLOPENBRK : '[[';
DBLCLOSEBRK : ']]';
OPENLST : '{';
CLOSELST : '}';