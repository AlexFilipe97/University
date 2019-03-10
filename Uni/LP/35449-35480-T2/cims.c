#include "cims.h"
#include "stack.c"
#include <math.h>

typedef struct node
{
	char *label;
	int index;
	char *master;
}node;

typedef struct arrayLabels
{	
	node *newNode;
	int size;
	int counter;
}arrayLabels;

typedef struct arrayInstrucoes
{
	inst *instrucao;
	int size; 
	int counter;
}arrayInstrucoes;

typedef struct bloco
{
	int *arrayVars;
	int *arrayArgs;
	int nArgs;
	int nVars;
	int flag;
	int ra;
}bloco;

typedef struct arrayBlocos
{
	bloco *blocos;
	int size;
	int counter;
}arrayBlocos;

int isMaster = 0;
char *lastMaster = "None";

arrayLabels *newArray;

arrayInstrucoes *newArrayInst;

arrayBlocos *blocoCoisas;

void percorreEtiqueta(label *etiqueta)
{
	if(isMaster == 1)
	{
		if(newArray -> counter >= newArray -> size)
		{
			int newSize = (newArray->size * sizeof(node)) * 2;
			newArray->newNode = realloc(newArray->newNode, newSize);
			newArray->size = newArray->size * 2;
		}

		newArray -> newNode[newArray -> counter].label = strdup(etiqueta -> identificador);
		newArray -> newNode[newArray -> counter].index = newArrayInst -> counter - 1;
		newArray -> newNode[newArray -> counter].master = strdup(etiqueta -> identificador);
		lastMaster = strdup(etiqueta -> identificador);
		newArray -> counter++;
	}
	else
	{
		if(newArray -> counter >= newArray -> size)
		{
			int newSize = (newArray->size * sizeof(node)) * 2;
			newArray->newNode = realloc(newArray->newNode, newSize);
			newArray->size = newArray->size * 2;
		}

		newArray -> newNode[newArray -> counter].label = strdup(etiqueta -> identificador);
		newArray -> newNode[newArray -> counter].index = newArrayInst -> counter;
		newArray -> newNode[newArray -> counter].master = strdup(lastMaster);
		newArray -> counter++;
	}
}

void percorreInstrucao(inst *instrucao)
{
	if(instrucao -> tipo == locais)
	{
		isMaster = 1;
	}
	else
	{
		isMaster = 0;
	}
	if(newArrayInst -> counter >= newArrayInst -> size)
	{
		int newSize = (newArrayInst -> size * sizeof(inst *)) * 2;
		newArrayInst -> instrucao = realloc(newArrayInst -> instrucao, newSize);
		newArrayInst -> size = newArrayInst -> size * 2;
	}
	newArrayInst -> instrucao[newArrayInst -> counter] = *(instrucao);

	newArrayInst -> counter++;
}

void percorrePrograma(prog *programa)
{
	if(programa -> programa != NULL)
	{
		percorrePrograma(programa -> programa);
	}

	if(programa->instrucao != NULL)
	{
		percorreInstrucao(programa -> instrucao);
	}
	
	if(programa -> etiqueta != NULL)
	{
		percorreEtiqueta(programa -> etiqueta);
	}
}

void executar_maquina(prog *programa)
{
	newArray = malloc(sizeof(arrayLabels));
	newArray -> newNode = calloc(1000, sizeof(node));
	newArray -> size = 1000;
	newArray -> counter = 0;

	newArrayInst = malloc(sizeof(arrayInstrucoes));
	newArrayInst -> instrucao = calloc(1000, sizeof(inst *));
	newArrayInst -> size = 1000;
	newArrayInst -> counter = 0;

	blocoCoisas = malloc(sizeof(blocoCoisas));
	blocoCoisas -> blocos = calloc(1000, sizeof(bloco));
	blocoCoisas -> size = 1000;
	blocoCoisas -> counter = 0;

	for (int i = 0; i < blocoCoisas -> size; i++)
	{
		blocoCoisas -> blocos[i].flag = -1;
	}

	percorrePrograma(programa);

	stack *pilha = createStack(1000);
	stack *temp1 = createStack(100);
	stack *temp2 = createStack(100);

	int sturt = 0;
	int tempRa = -1;

	for (int i = 0; i < newArray -> counter; i++)
	{
		if(strcmp(newArray -> newNode[i].label, "main") == 0)
		{
			sturt = newArray -> newNode[i].index;
			break;
		}
	}

	for (int pc = sturt; pc < newArrayInst->counter; pc++)
	{
		switch(newArrayInst -> instrucao[pc].tipo)
		{
			case chama:
			{
				int newPc = 0;
				for (int i = 0; i < newArray -> counter; i++)
				{
					if(strcmp(newArray -> newNode[i].label, newArrayInst -> instrucao[pc].arg2.string2) == 0)
					{
						newPc = newArray -> newNode[i].index;
						break;
					}
				}
				tempRa = pc + 1;
				pc = newPc - 1;
				break;
			}
			case locais:
			{
				if(blocoCoisas -> counter >= blocoCoisas -> size)
				{
					int newSize = (blocoCoisas->size * sizeof(bloco)) * 2;
					blocoCoisas -> blocos = realloc(blocoCoisas -> blocos, newSize);
					blocoCoisas -> size = blocoCoisas -> size * 2;
				}

				if (blocoCoisas -> blocos[blocoCoisas -> counter].flag == 0)
				{
					blocoCoisas -> blocos[blocoCoisas -> counter].flag = 1;
				}
				else
				{
					blocoCoisas -> blocos[blocoCoisas -> counter].arrayVars = calloc(100, sizeof(int));
					blocoCoisas -> blocos[blocoCoisas -> counter].arrayArgs = calloc(100, sizeof(int));
					blocoCoisas -> blocos[blocoCoisas -> counter].nArgs = newArrayInst -> instrucao[pc].arg1.inteiro1;
					blocoCoisas -> blocos[blocoCoisas -> counter].nVars = newArrayInst -> instrucao[pc].arg2.inteiro2;
					blocoCoisas -> blocos[blocoCoisas -> counter].flag = 1;
					blocoCoisas -> blocos[blocoCoisas -> counter].ra = tempRa;
				}
				
				while(!isEmpty(temp1))
				{
					push(temp2, pop(temp1));
				}

				int i = 0;
				while(!isEmpty(temp2))
				{
					blocoCoisas -> blocos[blocoCoisas -> counter].arrayArgs[i++] = pop(temp2);
				}

				blocoCoisas -> counter++;
				break;
			}
			case regressa:
			{
				if(blocoCoisas -> blocos[blocoCoisas -> counter - 1].ra == -1)
				{
					return;
				}
				else
				{
					blocoCoisas -> blocos[blocoCoisas -> counter - 1].flag = 0;
					pc = blocoCoisas -> blocos[blocoCoisas -> counter - 1].ra - 1;
					blocoCoisas -> counter--;
					break;
				}
			}
			case coloca_arg:
			{
				push(temp1, pop(pilha));
				break;
			}
			case empilha_var:
			{
				int pos = (blocoCoisas -> counter - 1) - (newArrayInst -> instrucao[pc].arg1.inteiro1);
				push(pilha, blocoCoisas -> blocos[pos].arrayVars[newArrayInst -> instrucao[pc].arg2.inteiro2 - 1]);
				break;
			}
			case empilha_arg:
			{
				int pos = (blocoCoisas -> counter - 1) - (newArrayInst -> instrucao[pc].arg1.inteiro1);
				push(pilha, blocoCoisas -> blocos[pos].arrayArgs[newArrayInst -> instrucao[pc].arg2.inteiro2 - 1]);
				break;
			}
			case empilha:
			{
				push(pilha, newArrayInst -> instrucao[pc].arg1.inteiro1);
				break;
			}
			case atribui_var:
			{
				int pos = (blocoCoisas -> counter - 1) - (newArrayInst -> instrucao[pc].arg1.inteiro1);
				int val = pop(pilha);
				blocoCoisas -> blocos[pos].arrayVars[newArrayInst -> instrucao[pc].arg2.inteiro2 - 1] = val;
				break;
			}
			case atribui_arg:
			{
				int pos = (blocoCoisas -> counter - 1) - (newArrayInst -> instrucao[pc].arg1.inteiro1);
				int val = pop(pilha);
				blocoCoisas -> blocos[pos].arrayArgs[newArrayInst -> instrucao[pc].arg2.inteiro2 - 1] = val;
				break;
			}
			case soma:
			{
				int y = pop(pilha);
				int x = pop(pilha);

				x = x + y;

				push(pilha, x);
				break;
			}
			case sub_:
			{
				int y = pop(pilha);
				int x = pop(pilha);

				x = x - y;

				push(pilha, x);
				break;
			}
			case mult_:
			{
				int y = pop(pilha);
				int x = pop(pilha);

				x = x * y;

				push(pilha, x);
				break;
			}
			case div_:
			{
				int y = pop(pilha);
				int x = pop(pilha);
				double w = 0;

				if(y == 0)
				{
					push( pilha, 0);
				}
				else
				{
					w = x / y;
					push(pilha, (int) round(w));
				}
				break;
			}
			case exp_:
			{
				int y = pop(pilha);
				int x = pop(pilha);
				double w = 0;

				w = pow((double) x, (double) y);

				push(pilha, (int) w);
				break;
			}
			case mod_:
			{
				int y = pop(pilha);
				int x = pop(pilha);

				x = x % y;

				push(pilha, x);
				break;
			}
			case sigual:
			{
				int y = pop(pilha);
				int x = pop(pilha);

				if(x == y)
				{
					int newPc = 0;
					for (int i = 0; i < newArray -> counter; i++)
					{
						if(strcmp(newArray -> newNode[i].label, newArrayInst -> instrucao[pc].arg1.string1) == 0)
						{
							newPc = newArray -> newNode[i].index;
							break;
						}
					}
					pc = newPc - 2;
				}
				break;
			}
			case smenor:
			{
				int y = pop(pilha);
				int x = pop(pilha);

				if(x < y)
				{
					int newPc = 0;
					for (int i = 0; i < newArray -> counter; i++)
					{
						if(strcmp(newArray -> newNode[i].label, newArrayInst -> instrucao[pc].arg1.string1) == 0)
						{
							newPc = newArray -> newNode[i].index;
							break;
						}
					}
					pc = newPc - 2;
				}
				break;
			}
			case salta:
			{
				int newPc = 0;
				for (int i = 0; i < newArray -> counter; i++)
				{
					if(strcmp(newArray -> newNode[i].label, newArrayInst -> instrucao[pc].arg1.string1) == 0)
					{
						newPc = newArray -> newNode[i].index;
						break;
					}
				}
				pc = newPc - 2;
				break;
			}
			case escreve_int:
			{
				printf("%d", pop(pilha));
				break;
			}
			case escreve_str:
			{
				printf("%s", newArrayInst -> instrucao[pc].arg1.string1);
				break;
			}
			case muda_linha:
			{
				printf("\n");
				continue;
				break;
			}
		}
	}
}