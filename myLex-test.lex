# this is a comment
%{
start
}%

delim   [ \t\n]
ws      {delim}+
letter  [abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]
digit   [01]
id      {letter}({letter}|{digit})*
number  {digit}+(\.{digit}*)

%%


\+ : { }


#aa*(b*|cd*(a|bbc)(ccc|dd))* : { }
#aa*(b|c)+ : {}
#abcdefg : {}
#aa*(bab*a)*(a|b)b* : { }
#a(bab*a)*(a|b)b* : { }
#aa*((bab*a)*(a|b)b*)* : {}
#a|b|c|d|e : { }
#aa* : {}

%%