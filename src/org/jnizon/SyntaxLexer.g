lexer grammar SyntaxLexer;

@header { package org.jnizon; }

ENDINSTRUCT
	:	';';
NEWLINE	:	'\r'?'\n';
ID	:	('a'..'z'|'A'..'Z') (('a'..'z'|'A'..'Z'|'0'..'9')+)? ;
INT	:	'0'..'9'+;
MISCSEP :	(' ')+ {skip();};
ASSIGN	:	'=';