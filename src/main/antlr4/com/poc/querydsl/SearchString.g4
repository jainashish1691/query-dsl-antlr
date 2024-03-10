grammar SearchString;
options {
    language = Java;
    // Generate visitor
    visitor = true;
}
searchString: expression;

expression: logicalExpression;

logicalExpression: atom (logicalOp atom)*;

atom: '(' expression ')' | comparison;

comparison: field operator value;

field: IDENTIFIER;

operator: EQ | LT | GT | LTE | GTE;

value: STRING;

logicalOp: 'AND' | 'OR';

EQ: '=';
LT: '<';
GT: '>';
LTE: '<=';
GTE: '>=';

IDENTIFIER: [a-zA-Z]+;
STRING: '\'' .*? '\'';
WS: [ \t\r\n]+ -> skip;