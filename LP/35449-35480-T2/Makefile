CC = gcc -Wall
YACC = bison -d -v
LEX = flex

all : clean parser

parser : parser.tab.c lex.yy.c structs.c cims.c
	$(CC) $^ -o $@ -lfl -lm

parser.tab.c : parser.y
	$(YACC) $^

lex.yy.c : scanner.lex
	$(LEX) $^

runFatorial : parser
	@echo 'A correr programa factorial.'
	@./$^ < exemplos/factorial.cims

runFatorialRec : parser
	@echo 'A correr programa factorial_rec.'
	@./$^ < exemplos/factorial_rec.cims

runMdc : parser
	@echo 'A correr programa mdc.'
	@./$^ < exemplos/mdc.cims

runMixtura : parser
	@echo 'A correr programa mixtura.'
	@./$^ < exemplos/mixtura.cims

runAll : parser
	@echo ''
	@echo 'A correr programa factorial.'
	@./$^ < exemplos/factorial.cims
	@echo ''
	@echo 'A correr programa factorial_rec.'
	@./$^ < exemplos/factorial_rec.cims
	@echo ''
	@echo 'A correr programa mdc.'
	@./$^ < exemplos/mdc.cims
	@echo ''
	@echo 'A correr programa mixtura.'
	@./$^ < exemplos/mixtura.cims
	@echo ''

clean : 
	@$(RM) parser
	@$(RM) parser.tab.c parser.tab.h
	@$(RM) lex.yy.c
	@$(RM) parser.output