grammar Syntax;

options {
output = AST;
}

@header { package org.jnizon; }

start	:	prog+;

prog	:	NEWLINE
	|	stmt NEWLINE
	|	stmt ';';

stmt	:	ID MISCSEP '=' MISCSEP expr {System.out.println("Assignation : " + $ID.text + " = " + $expr.text);};

expr	:	INT {System.out.println("Integer pull : "+$INT.text); }
	|	ID {System.out.println("Variable pull : "+$ID.text); };

NEWLINE	:	'\r'?'\n';
ID	:	('a'..'z'|'A'..'Z') (('a'..'z'|'A'..'Z'|'0'..'9')+) ;
INT	:	'0'..'9'+;
MISCSEP :	' '*;