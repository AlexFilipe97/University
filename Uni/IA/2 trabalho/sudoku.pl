%Cada posição é: (X, Y, Quadrante, Valor, Dominio)
estado_inicial(e([
				c(1,1,1,_, [1,2,3,4,5,6,7,8,9]),c(2,1,1,_, [1,2,3,4,5,6,7,8,9]), 
				c(4,1,2,_, [1,2,3,4,5,6,7,8,9]),c(6,1,2,_, [1,2,3,4,5,6,7,8,9]),
				c(8,1,3,_, [1,2,3,4,5,6,7,8,9]),c(9,1,3,_, [1,2,3,4,5,6,7,8,9]),
				c(2,2,1,_, [1,2,3,4,5,6,7,8,9]),c(3,2,1,_, [1,2,3,4,5,6,7,8,9]),
				c(5,2,2,_, [1,2,3,4,5,6,7,8,9]),c(7,2,3,_, [1,2,3,4,5,6,7,8,9]),
				c(8,2,3,_, [1,2,3,4,5,6,7,8,9]),c(3,3,1,_, [1,2,3,4,5,6,7,8,9]), 
				c(4,3,2,_, [1,2,3,4,5,6,7,8,9]),c(5,3,2,_, [1,2,3,4,5,6,7,8,9]),
				c(6,3,2,_, [1,2,3,4,5,6,7,8,9]),c(7,3,3,_, [1,2,3,4,5,6,7,8,9]),

				c(1,4,4,_, [1,2,3,4,5,6,7,8,9]),c(3,4,4,_, [1,2,3,4,5,6,7,8,9]),
				c(5,4,5,_, [1,2,3,4,5,6,7,8,9]),c(7,4,6,_, [1,2,3,4,5,6,7,8,9]),
				c(9,4,6,_, [1,2,3,4,5,6,7,8,9]),c(1,5,4,_, [1,2,3,4,5,6,7,8,9]),
				c(2,5,4,_, [1,2,3,4,5,6,7,8,9]),c(4,5,5,_, [1,2,3,4,5,6,7,8,9]),
				c(5,5,5,_, [1,2,3,4,5,6,7,8,9]),c(6,5,5,_, [1,2,3,4,5,6,7,8,9]),
				c(8,5,6,_, [1,2,3,4,5,6,7,8,9]),c(9,5,6,_, [1,2,3,4,5,6,7,8,9]),
				c(1,6,4,_, [1,2,3,4,5,6,7,8,9]),c(3,6,4,_, [1,2,3,4,5,6,7,8,9]), 
				c(5,6,5,_, [1,2,3,4,5,6,7,8,9]),c(7,6,6,_, [1,2,3,4,5,6,7,8,9]),
				c(9,6,6,_, [1,2,3,4,5,6,7,8,9]),

				c(3,7,7,_, [1,2,3,4,5,6,7,8,9]),c(4,7,8,_, [1,2,3,4,5,6,7,8,9]),
				c(5,7,8,_, [1,2,3,4,5,6,7,8,9]),c(6,7,8,_, [1,2,3,4,5,6,7,8,9]), 
				c(7,7,9,_, [1,2,3,4,5,6,7,8,9]),c(2,8,7,_, [1,2,3,4,5,6,7,8,9]),
				c(3,8,7,_, [1,2,3,4,5,6,7,8,9]),c(5,8,8,_, [1,2,3,4,5,6,7,8,9]), 
				c(7,8,9,_, [1,2,3,4,5,6,7,8,9]),c(8,8,9,_, [1,2,3,4,5,6,7,8,9]),
				c(1,9,7,_, [1,2,3,4,5,6,7,8,9]),c(2,9,7,_, [1,2,3,4,5,6,7,8,9]), 
				c(4,9,8,_, [1,2,3,4,5,6,7,8,9]),c(6,9,8,_, [1,2,3,4,5,6,7,8,9]), 
				c(8,9,9,_, [1,2,3,4,5,6,7,8,9]),c(9,9,9,_, [1,2,3,4,5,6,7,8,9])

				], [

				c(3,1,1,5, [1,2,3,4,5,6,7,8,9]),c(5,1,2,8, [1,2,3,4,5,6,7,8,9]),
				c(7,1,3,7, [1,2,3,4,5,6,7,8,9]),c(1,2,1,7, [1,2,3,4,5,6,7,8,9]),
				c(4,2,2,2, [1,2,3,4,5,6,7,8,9]),c(6,2,2,4, [1,2,3,4,5,6,7,8,9]),
				c(9,2,3,5, [1,2,3,4,5,6,7,8,9]),c(1,3,1,3, [1,2,3,4,5,6,7,8,9]),
				c(2,3,1,2, [1,2,3,4,5,6,7,8,9]),c(8,3,3,8, [1,2,3,4,5,6,7,8,9]),
				c(9,3,3,4, [1,2,3,4,5,6,7,8,9]),c(2,4,4,6, [1,2,3,4,5,6,7,8,9]),
				c(4,4,5,1, [1,2,3,4,5,6,7,8,9]),c(6,4,5,5, [1,2,3,4,5,6,7,8,9]),
				c(8,4,6,4, [1,2,3,4,5,6,7,8,9]),c(3,5,4,8, [1,2,3,4,5,6,7,8,9]),
				c(7,5,6,5, [1,2,3,4,5,6,7,8,9]),c(2,6,4,7, [1,2,3,4,5,6,7,8,9]),
				c(4,6,5,8, [1,2,3,4,5,6,7,8,9]),c(6,6,5,3, [1,2,3,4,5,6,7,8,9]),
				c(8,6,6,1, [1,2,3,4,5,6,7,8,9]),c(1,7,7,4, [1,2,3,4,5,6,7,8,9]),
				c(2,7,7,5, [1,2,3,4,5,6,7,8,9]),c(8,7,9,9, [1,2,3,4,5,6,7,8,9]),
				c(9,7,9,1, [1,2,3,4,5,6,7,8,9]),c(1,8,7,6, [1,2,3,4,5,6,7,8,9]),
				c(4,8,8,5, [1,2,3,4,5,6,7,8,9]),c(6,8,8,8, [1,2,3,4,5,6,7,8,9]),
				c(9,8,9,7, [1,2,3,4,5,6,7,8,9]),c(3,9,7,3, [1,2,3,4,5,6,7,8,9]),
				c(5,9,8,1, [1,2,3,4,5,6,7,8,9]),c(7,9,9,6, [1,2,3,4,5,6,7,8,9])])).

%
% Check restrições
%
lookRestricoes(E) :-
	differentL(E),
	differentC(E),
	differentQ(E).

%
% Restrição para linhas.
%
differentL(e(_, [c(X,_,_,V1,_)|R])):-	
	findall(V, member(c(X,_,_,V,_),R), R1),
	diff([V1|R1]).

%
% Restrição para colunas.
%
differentC(e(_, [c(_,Y,_,V1,_)|R])) :-
	findall(V, member(c(_,Y,_,V,_),R),R1),	
	diff([V1|R1]).
%
% Restrição para Quadrantes
%
differentQ(e(_, [c(_,_,Q,V1,_)|R])):-	
	findall(V, member(c(_,_,Q,V,_),R), R1),
	diff([V1|R1]).



%
% Ver se está na lista.
%
diff([]):-!.
diff([H|T]):-
	\+member(H,T), diff(T).