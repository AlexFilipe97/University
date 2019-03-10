#include "structs.h"



prog *newPrograma(prog *programa, label *etiqueta, inst *instrucao)
{
	prog *newPointer = malloc(sizeof(prog));
	newPointer -> programa = programa;
	newPointer -> etiqueta = etiqueta;
	newPointer -> instrucao = instrucao;

	return newPointer;
}

label *newLabel(char *pointer)
{
	label *newPointer = malloc(sizeof(label));
	newPointer -> identificador = strdup(pointer);
	return newPointer;
}

inst *newInstrucao1(type tipo)
{
	inst *newPointer = malloc(sizeof(inst));
	newPointer -> tipo = tipo;
	return newPointer;
}

inst *newInstrucao2(type tipo, int inteiro1)
{
	inst *newPointer = malloc(sizeof(inst));
	newPointer -> tipo = tipo;
	newPointer -> arg1.inteiro1 = inteiro1;
	return newPointer;
}

inst *newInstrucao3(type tipo, char *string1)
{
	inst *newPointer = malloc(sizeof(inst));
	newPointer -> tipo = tipo;
	newPointer -> arg1.string1 = strdup(string1);
	return newPointer;
}

inst *newInstrucao4(type tipo, int inteiro1, int inteiro2)
{
	inst *newPointer = malloc(sizeof(inst));
	newPointer -> tipo = tipo;
	newPointer -> arg1.inteiro1 = inteiro1;
	newPointer -> arg2.inteiro2 = inteiro2;
	return newPointer;
}

inst *newInstrucao5(type tipo, int inteiro1, char *string2)
{
	inst *newPointer = malloc(sizeof(inst));
	newPointer -> tipo = tipo;
	newPointer -> arg1.inteiro1 = inteiro1;
	newPointer -> arg2.string2 = strdup(string2);
	return newPointer;
}