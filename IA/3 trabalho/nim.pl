alvo([0,0,0,0]).

opmenos(j(1,1), [A, B, C, D], X):-
			A > 0,
			A1 is A - 1,
			X = [A1, B, C, D].

opmenos(j(1,2), [A, B, C, D], X):-
			B > 0,
			B1 is B - 1,
			X = [A, B1, C, D].

opmenos(j(2,2), [A, B, C, D], X):-
			B > 1,
			B1 is B - 2,
			X = [A, B1, C, D].

opmenos(j(1,3), [A, B, C, D], X):-
			C > 0,
			C1 is C - 1,
			X = [A, B, C1, D].

opmenos(j(2,3), [A, B, C, D], X):-
			C > 1,
			C1 is C - 2,
			X = [A, B, C1, D].

opmenos(j(3,3), [A, B, C, D], X):-
			C > 2,
			C1 is C - 3,
			X = [A, B, C1, D].

opmenos(j(1,4), [A, B, C, D], X):-
			D > 0,
			D1 is D - 1,
			X = [A, B, C, D1].
			
opmenos(j(2,4), [A, B, C, D], X):-
			D > 1,
			D1 is D - 2,
			X = [A, B, C, D1].

opmenos(j(3,4), [A, B, C, D], X):-
			D > 2,
			D1 is D - 3,
			X = [A, B, C, D1].

opmenos(j(4,4), [A, B, C, D], X):-
			D > 3,
			D1 is D - 4,
			X = [A, B, C, D1].


estado_inicial(e(a, [1,2,3,4])).

estado_terminal(e(_, N)) :- alvo(N).


utilidade(e(b, N), +1) :- alvo(N).
utilidade(e(a, N), -1) :- alvo(N).
utilidade(e(_, _), 0)  :- alvo(_).

sucessor(e(J, [A,B,C,D]), menos(Q, L), e(K, Y)) :-
	opmenos(j(Q, L), [A, B, C, D], X),
	Y = X,
	outro(J, K).

escreve(e(_, [A,B,C,D])) :-
	nl, write('linha 1: '), write(A),nl,
	write('linha 2: '), write(B), nl,
	write('linha 3: '), write(C), nl,
	write('linha 4: '), write(D), nl.

outro(a,b).
outro(b,a).