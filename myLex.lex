# this is a comment
%{
start
}%

delim   [ \t\n]
ws      {delim}+
letter  [abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]
digit   [0123456789]
id      {letter}({letter}|{digit})*
number  {digit}+(\.{digit}*)|{digit}+

%%

{ws}    : { nothing }

if      : { keyword }

public  : { keyword }
void    : {keyword}
class   : {keyword}
static  : {keyword}
;       : {Punctuation}
\.      : {Punctuation}
int     : {keyword}

else    : {keyword }

\{      : { Punctuation }
\}      : { Punctuation }
\[      : {Punctuation }
\]      : { Punctuation}

{id}    : { id }
{number}: { number }
=       : { operator }
<       : {operator }
>       : {operator }
<=      : {operator }
>=      : {operator }
\+      : {operator }
-       : {operator }
\*      : {operator }
/       : {operator }
\(      : {Punctuation }
\)      : {Punctuation }

%%