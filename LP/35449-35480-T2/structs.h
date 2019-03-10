#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct programa prog;
typedef struct etiqueta label;
typedef struct instrucao inst;
typedef enum funcs {chama=0, locais, regressa, coloca_arg, empilha_var, empilha_arg, empilha, atribui_var, atribui_arg, soma, sub_, mult_, div_, exp_, mod_, sigual, smenor, salta, escreve_int, escreve_str, muda_linha} type;

struct programa 
{
	prog *programa;
	label *etiqueta;
	inst *instrucao;
};

struct etiqueta
{
	char *identificador;
};

struct instrucao
{
	type tipo;
	union 
	{
		int inteiro1;
		char *string1;
	}arg1;

	union
	{
		int inteiro2;
		char *string2;
	}arg2;
};

/*
typedef struct bloco
{
	int *arrayVars;
	int *arrayArgs;
	int nArgs;
	int nVars;
}bloco;

typedef struct arrayBlocos
{
	bloco *blocos;
	int size;
	int counter;
}arrayBlocos;
*/

prog *newPrograma(prog *programa, label *etiqueta, inst *instrucao);

label *newLabel(char *pointer);

inst *newInstrucao1(type tipo);

inst *newInstrucao2(type tipo, int inteiro1);

inst *newInstrucao3(type tipo, char *string1);

inst *newInstrucao4(type tipo, int inteiro1, int inteiro2);

inst *newInstrucao5(type tipo, int inteiro1, char *string2);