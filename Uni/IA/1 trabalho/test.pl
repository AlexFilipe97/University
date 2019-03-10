%estado(Bateria, Posição, Salas Sujas, Carregadores, Dimensões).
:-include('tools.pl').

/*[(0,0), (4,0), (0,4), (4,4)]*/
estado_inicial(e(7, (0,2), [(1,2)], [(2,1)], (4,3) )).

estado_final(e(_, A, [], B, (4,3))):- 
		member(A, B).


%estado_final(e(_, (3, 2), _, _, (4,3))).

tr(
	e(A, B, C, _, _),
	limpar,
	e(A1, B, C1, _, _),
	1
	):- A > 0,
		A1 is A - 1,
		member(B, C),
		delete(C, B, C1).

tr(
	e(A, (X, Y), C, D, (N, M)),
	cima,
	e(A1, (X, Y1), C, D, (N, M)),
	1
	) :-A > 0,
		Y > 0,
		A1 is A - 1,
    	Y1 is Y - 1.

tr(
	e(A, (X, Y), C, D, (N, M)),
	direita,
	e(A1, (X1, Y), C, D, (N, M)),
	1
	) :-A > 0,
		A1 is A - 1, 
		X1 is X + 1,
	    X1 < N.

tr(
	e(A, (X, Y), C, D, (N, M)),
	baixo,
	e(A1, (X, Y1), C, D, (N, M)),
	1
	) :- A > 0,
		A1 is A - 1,
		Y1 is Y + 1,
    	Y1 < M.

tr(
	e(A, (X, Y), C, D, (N, M)),
	esquerda,
	e(A1, (X1, Y), C, D, (N, M)),
	1
	) :-A > 0,
		A1 is A - 1,
		X > 0,
    	X1 is X - 1.