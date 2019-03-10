p:- 
	estado_inicial(E0), 
	back(E0,A),
	esc(A,1).

back(e([],A),A).
back(E,Sol):- 
	sucessor(E,E1), 
	lookRestricoes(E1),
    back(E1,Sol).

sucessor(e([c(X,Y,Q,V,D)|R],E),e(R,[c(X,Y,Q,V,D)|E])):-
		member(V,D).


esc(_,10).
esc(A,Y):-
	findall(c(X,Y,Q,V,D), member(c(X,Y,Q,V,D),A), Lista),
	sort(Lista,LP),
	fprint(LP,1),
	fsep(Y),
	Y1 is Y + 1,
	esc(A,Y1).

fprint([],_):-writeln('').
fprint([c(_,_,_,V,_)|L], Q):-
	write(V), space(Q),
	Q1 is Q + 1,
	fprint(L,Q1).

space(Q):-
	Q \= 9,
	X is Q mod 3,
	X = 0,
	write(' | ').
space(_):-
	write(' ').

fsep(X):-
	X1 is X mod 3,
	X1 = 0,
	Y is X mod 9,
	Y \= 0,
	writeln('------+-------+------').
fsep(_).