%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <getopt.h>
#include <errno.h>
#include "cims.h"

int yyerror(const char* msg);
int yylex(void);


%}

%error-verbose

%union 
{
	char* string;
	int val;
	struct programa *programa;
	struct etiqueta *etiqueta;
	struct instrucao *instrucao;
}

%token CHAMA LOCAIS REGRESSA COLOCA_ARG 
%token EMPILHA_VAR EMPILHA_ARG EMPILHA ATRIBUI_VAR ATRIBUI_ARG
%token SOMA SUB MULT DIV EXP MOD
%token SIGUAL SMENOR SALTA
%token ESCREVE_INT ESCREVE_STR MUDA_LINHA
%token <string> IDENTIFICADOR
%token <string> STRING
%token <val> INTEIRO
%token DOIS_PONTOS

%type <programa> programa
%type <etiqueta> etiqueta
%type <instrucao> instrucao



%debug 
%%

//non terminal CIMS programa ;
//non terminal      etiqueta, instrucao ;

inicio : programa {executar_maquina($1);}
	   ;

programa : programa etiqueta instrucao { $$ = newPrograma($1, $2, $3); }
         |	{$$ = newPrograma(NULL, NULL, NULL); }
	 ;
etiqueta : IDENTIFICADOR DOIS_PONTOS { $$ = newLabel($1); }
	 | {$$ = NULL; }
	 ;
instrucao : CHAMA INTEIRO IDENTIFICADOR { $$ = newInstrucao5(chama, $2, $3); }
	  |   LOCAIS INTEIRO INTEIRO { $$ = newInstrucao4(locais, $2, $3); }
	  |   REGRESSA {$$ = newInstrucao1(regressa); }
	  |   COLOCA_ARG INTEIRO { $$ = newInstrucao2(coloca_arg, $2); }
	  |   EMPILHA_VAR INTEIRO INTEIRO { $$ = newInstrucao4(empilha_var, $2, $3); }
	  |   EMPILHA_ARG INTEIRO INTEIRO { $$ = newInstrucao4(empilha_arg, $2, $3); }
	  |   EMPILHA INTEIRO { $$ = newInstrucao2(empilha, $2); }
	  |   ATRIBUI_VAR INTEIRO INTEIRO { $$ = newInstrucao4(atribui_var, $2, $3); }
	  |   ATRIBUI_ARG INTEIRO INTEIRO { $$ = newInstrucao4(atribui_arg, $2, $3); }
	  |   SOMA { $$ = newInstrucao1(soma); }
	  |   SUB  { $$ = newInstrucao1(sub_); }
	  |   MULT { $$ = newInstrucao1(mult_); }
	  |   DIV  { $$ = newInstrucao1(div_); }
	  |   EXP  { $$ = newInstrucao1(exp_); }
	  |   MOD  { $$ = newInstrucao1(mod_); }
	  |   SIGUAL IDENTIFICADOR { $$ = newInstrucao3(sigual, $2); }
	  |   SMENOR IDENTIFICADOR { $$ = newInstrucao3(smenor, $2); }
	  |   SALTA IDENTIFICADOR { $$ = newInstrucao3(salta, $2); }
	  |   ESCREVE_INT { $$ = newInstrucao1(escreve_int); }
	  |   ESCREVE_STR STRING { $$ = newInstrucao3(escreve_str, $2); }
	  |   MUDA_LINHA { $$ = newInstrucao1(muda_linha); }
	  ;

%%


//code here2

int yyerror(const char* msg) 
{
    fprintf(stderr,"%s\n",msg);
    return 0;
}



int main ()
{	
	return yyparse();
}