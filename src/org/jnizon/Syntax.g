grammar Syntax;

prog	:	stmt+;

stmt	:	NEWLINE
	|	expr NEWLINE
	|	expr ';';

expr	:	CHARS '=' INT {System.out.println("haiiii");};

NEWLINE	:	'\r'?'\n';
CHARS	:	('a'..'z'|'A'..'Z')+;
INT	:	'0'..'9'+;
