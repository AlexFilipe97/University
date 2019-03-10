%estado(jogador, tab)

alvo(	[N,N,N,
	   	_,_,_,
	  	_,_,_]) :-
			N \= a.
alvo(  	[_,_,_,
	 	N,N,N,
		_,_,_]) :- 
			N \= a.
alvo(	[_,_,_,
		_,_,_,
		N,N,N]) :-
			N \= a.
alvo(	[N,_,_,
		N,_,_,
		N,_,_]) :-
			N \= a.
alvo(	[_,N,_,
		_,N,_,
		_,N,_]) :-
			N \= a.
alvo(	[_,_,N,
		_,_,N,
		_,_,N]) :-
			N \= a.
alvo(	[N,_,_,
		_,N,_,
		_,_,N]) :-
			N \= a.
alvo(	[_,_,N,
		_,N,_,
		N,_,_]) :-
			N \= a.
alvo(	[Q,W,E,
		 R,T,Y,
		 U,I,O]) :-
			Q \= a,
			W \= a,
			E \= a,
			R \= a,
			T \= a,
			Y \= a,
			U \= a,
			I \= a,
			O \= a.

%e(P, [_,_,_,_,_,_,_,_,_]).

estado_inicial(e(x, [a,a,a,a,a,a,a,a,a])).

estado_terminal(e(_,N)):- alvo(N).


utilidade(e(x, N), +1) 	:- alvo(N).
utilidade(e(o, N), -1) 	:- alvo(N).
utilidade(e(x, _), 0) 	:- alvo(_).
utilidade(e(o, _), 0) 	:- alvo(_).

sucessor(e(J,[A,B,C,D,E,F,G,H,I]), joga(S,P), e(K,Y)) :-
	opjoga(j(S,P), [A,B,C,D,E,F,G,H,I], X),
	Y = X,
	outro(J, K).

outro(x, o).
outro(o, x).

opjoga(j(x,1), [_,B,C,D,E,F,G,H,I], X):-
	X = [x,B,C,D,E,F,G,H,I].

opjoga(j(x,2), [A,_,C,D,E,F,G,H,I], X):-
	X = [A,x,C,D,E,F,G,H,I].

opjoga(j(x,3), [A,B,_,D,E,F,G,H,I], X):-
	X = [A,B,x,D,E,F,G,H,I].

opjoga(j(x,4), [A,B,C,_,E,F,G,H,I], X):-
	X = [A,B,C,x,E,F,G,H,I].

opjoga(j(x,5), [A,B,C,D,_,F,G,H,I], X):-
	X = [A,B,C,D,x,F,G,H,I].

opjoga(j(x,6), [A,B,C,D,E,_,G,H,I], X):-
	X = [A,B,C,D,E,x,G,H,I].

opjoga(j(x,7), [A,B,C,D,E,F,_,H,I], X):-
	X = [A,B,C,D,E,F,x,H,I].

opjoga(j(x,8), [A,B,C,D,E,F,G,_,I], X):-
	X = [A,B,C,D,E,F,G,x,I].

opjoga(j(x,9), [A,B,C,D,E,F,G,H,_], X):-
	X = [A,B,C,D,E,F,G,H,x].

opjoga(j(o,1), [_,B,C,D,E,F,G,H,I], X):-
	X = [o,B,C,D,E,F,G,H,I].

opjoga(j(o,2), [A,_,C,D,E,F,G,H,I], X):-
	X = [A,o,C,D,E,F,G,H,I].

opjoga(j(o,3), [A,B,_,D,E,F,G,H,I], X):-
	X = [A,B,o,D,E,F,G,H,I].

opjoga(j(o,4), [A,B,C,_,E,F,G,H,I], X):-
	X = [A,B,C,o,E,F,G,H,I].

opjoga(j(o,5), [A,B,C,D,_,F,G,H,I], X):-
	X = [A,B,C,D,o,F,G,H,I].

opjoga(j(o,6), [A,B,C,D,E,_,G,H,I], X):-
	X = [A,B,C,D,E,o,G,H,I].

opjoga(j(o,7), [A,B,C,D,E,F,_,H,I], X):-
	X = [A,B,C,D,E,F,o,H,I].

opjoga(j(o,8), [A,B,C,D,E,F,G,_,I], X):-
	X = [A,B,C,D,E,F,G,o,I].

opjoga(j(o,9), [A,B,C,D,E,F,G,H,_], X):-
	X = [A,B,C,D,E,F,G,H,o].

escreve(e(_, [A,B,C,D,E,F,G,H,I])) :-
	nl, write(A), write('|'), write(B), write('|'), write(C), nl,
	write('-----'), nl,
	write(D), write('|'), write(E), write('|'), write(F), nl,
	write('-----'), nl,
	write(G), write('|'), write(H), write('|'), write(I), nl.