
%{
#include "parser.tab.h"

%}

%option noinput
%option nounput

%%

chama			{ return CHAMA; }
locais			{ return LOCAIS; }
regressa			{ return REGRESSA; }
coloca_arg			{ return COLOCA_ARG; }

empilha_var		{ return EMPILHA_VAR; }
empilha_arg		{ return EMPILHA_ARG; }
empilha			{ return EMPILHA; }

atribui_var		{ return ATRIBUI_VAR; }
atribui_arg		{ return ATRIBUI_ARG; }

soma			{ return SOMA; }
sub				{ return SUB; }
mult			{ return MULT; }
div				{ return DIV; }
exp				{ return EXP; }
mod				{ return MOD; }

sigual			{ return SIGUAL; }
smenor			{ return SMENOR; }
salta			{ return SALTA; }

escreve_int			{ return ESCREVE_INT; }
escreve_str			{ return ESCREVE_STR; }
muda_linha			{ return MUDA_LINHA; }

[a-zA-Z][a-zA-Z0-9_]*	{ yylval.string = strdup(yytext); return IDENTIFICADOR;}

:			{ return DOIS_PONTOS; }

-?[0-9]+		{ yylval.val = atoi(yytext); return INTEIRO; }
\"(\\[\\\"]|[^\"\\])*\"	{ 
							char* tmp = malloc(sizeof(char)*strlen(yytext));
							
							for(int i=0;i<strlen(yytext);i++)
							{
								tmp[i]='\0';
							}
							
							for(int i=1;i<(strlen(yytext)-1);i++)
							{
								tmp[i-1]=yytext[i];
							}
							
							yylval.string = strdup(tmp);
							return STRING; }

[\ \n\t]		{ /* ignora brancos */ }

#.*				{ /* e comentários */ }

.	{
		printf("Upsie: %s\n",yytext);
	}
%%


int yywrap()
{
	return(1);
}