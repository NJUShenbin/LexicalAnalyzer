# this is a comment
%{
start
}%

delim   [ \t\n]
ws      {delim}+
letter  [abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]
digit   [0123456789]
id      {letter}({letter}|{digit})*
number  {digit}+(\.{digit}*)

%%

#aa*(b*|cd*(a|bbc)(ccc|dd))* : { }
<       : { }
>       : { }
<=      : { }
>=      : { }

#aa*(b|c)+ : {}
#abcdefg : {}

%%