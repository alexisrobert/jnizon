grammar Syntax;

start	:	prog+;

prog	:	NEWLINE
	|	stmt NEWLINE
	|	stmt ';';

stmt	:	ID '=' expr {System.out.println("Assignation : " + $ID + " = " + $INT);};

expr	:	INT {System.out.println("Integer pull : "+$INT); }
	|	ID {System.out.println("Variable pull : "+$ID); };

NEWLINE	:	'\r'?'\n';
ID	:	('a'..'z'|'A'..'Z') (('a'..'z'|'A'..'Z'|'0'..'9')+) ;
INT	:	'0'..'9'+;
