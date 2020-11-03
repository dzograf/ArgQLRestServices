/**
 * Define a grammar called ArgQLBNF
 */
grammar ArgQLBNF;

 
query: 
		MATCH (dialogue_pattern (',' dialogue_pattern)*)
		RETURN  (VARIABLE (',' VARIABLE )* )
	;

dialogue_pattern: argpattern (pathpattern[2] argpattern)*;


argpattern :
		 (
		 	VARIABLE |
		 (VARIABLE DOTS)? '<' premisePattern ',' conclusionPattern '>' 
		 ) argFilters ?
	;


premisePattern :  
		propositionSet |
		propositionVar (premiseFilters)? |
		EMPTYSET
	;

conclusionPattern : propositionVar | TEXT;

premiseFilters: '[' (inclusionFilter | joinFilter | disjointFilter ) ']';

argFilters :
	'['
	( SCHEME DOTS TEXT |
	  metadata
	)
	
	']';

metadata:
 TIMESTAMP DOTS TEXT | 
 AUTHOR DOTS TEXT
;

inclusionFilter: '/' (propositionSet | VARIABLE); 

joinFilter: '.' (propositionSet | VARIABLE);

disjointFilter: '!' (propositionSet | VARIABLE);

propositionSet :
		'{'  TEXT (',' TEXT )* '}'
	;

propositionVar : VARIABLE;
 
//argVar : VARIABLE;
//argFilters: aboutFilter;

//aboutFilter: ABOUT topicPattern;

//topicPattern: VARIABLE | STRINGLITERAL;
 
/*propositionpattern : 
		VARIABLE  | 
		STRINGLITERAL ;
*/
//pathpattern: relation | 
//			 pathpattern '/'pathpattern |
//			 '('pathpattern ')' '*' INT;
pathpattern[int a] : pp[2] ('/' pp[2])*;

pp[int a] : relation | 
    '(' pathpattern[2] ')' ('*' INT | '+' INT)
    ;

relation: (ATTACK | REBUT | UNDERCUT | SUPPORT | ENDORSE | BACK) ;


MATCH: ('M'|'m')('A'|'a')('T'|'t')('C'|'c')('H'|'h');

RETURN: ('R'|'r')('E'|'e')('T'|'t')('U'|'u')('R'|'r')('N'|'n');

ATTACK: ('A'|'a')('T'|'t')('T'|'t')('A'|'a')('C'|'c')('K'|'k');

REBUT: ('R'|'r')('E'|'e')('B'|'b')('U'|'u')('T'|'t');

UNDERCUT: ('U'|'u')('N'|'n')('D'|'d')('E'|'e')('R'|'r')('C'|'c')('U'|'u')('T'|'t');

SUPPORT: ('S'|'s')('U'|'u')('P'|'p')('P'|'p')('O'|'o')('R'|'r')('T'|'t');

ENDORSE: ('E'|'e')('N'|'n')('D'|'d')('O'|'o')('R'|'r')('S'|'s')('E'|'e');

BACK: ('B'|'b')('A'|'a')('C'|'c')('K'|'k');

VARIABLE: '?'('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

EMPTYSET: '_';

TIMESTAMP: ('t'|'T')('m'|'M');

AUTHOR: ('A'|'a')('U'|'u')('T'|'t')('H'|'h');

SCHEME: ('S'|'s')('C'|'c')('H'|'h');


DOTS: ':';

 TEXT: '"' .*? '"';
//STRINGLITERAL: '"'.*?'"';
//'"' (options {greedy=false;} : ~('\u0022' |'\u005C' | '\u000A' | '\u000D') | ECHAR)* '"'; 

//ECHAR: '\\' ('t' | 'b' | 'n' | 'r' | 'f' | '\\' | '"' | '\'');

INT: '0'..'9'+;

WS: (' '|'\t'|'\n'|'\r')+ {skip();} ;


//[ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines


