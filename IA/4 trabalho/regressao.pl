:- consult(pesquisa).

estado_inicial(E) :-
	objetivo(E).

estado_final(E) :-
	inicio(F),
	satisfaz(F, E).	

tr(E, X, F, C) :-
	acao(X, P, A, R),	
	relevante(A, E),
	consistente(R, E),
	predecessor(E, P, A, F),
	length(F, C).

relevante(A, E) :-
	member(X, A), 
	member(X, E).

consistente([], _).
consistente([X | R], E) :-
	\+ member(X, E),
	consistente(R, E).

predecessor(E, P, A, F) :-
	subtract(E, A, E1),
	append(E1, P, F1),
	list_to_set(F1, F).

planear(Problema, Algoritmo, Solucao, Stat) :-
	pesquisa(Problema, Algoritmo, Solucao, Stat).

teste(Problema, Algoritmo) :-
	planear(Problema, Algoritmo, Solucao, _),
	write('Por REGRESSÃO'), nl,
	escreve(Solucao).

teste(Problema) :-
	teste(Problema, iterativa).

escreve(nenhum).
escreve(no(E, P, O, _, _)) :-
	write(O), write(' => '), write(E), nl,
	escreve(P).