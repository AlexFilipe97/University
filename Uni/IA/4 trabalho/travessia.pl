
%margem(lado, missionarios, canibais).
%barco(lado, missionarios, canibais).

inicio([margem(esquerda, missionarios(3), canibais(3)),
                margem(direita, missionarios(0), canibais(0)),
                barco(esquerda, missionarios(0), canibais(0))]).

objetivo([margem(direita, missionarios(3), canibais(3))]).

pessoasMargem(0).
pessoasMargem(1).
pessoasMargem(2).
pessoasMargem(3).

pessoasBarco(0).
pessoasBarco(1).
pessoasBarco(2).

margemAtual(esquerda).
margemAtual(direita).


acao(
    embarcaMissionario,
    [margem(Margem, missionarios(X), canibais(Y)),
    barco(Margem, missionarios(X1), canibais(Y1))],

    [margem(Margem, missionarios(X2),canibais(Y)),
    barco(Margem, missionarios(X3), canibais(Y1))],

    [margem(Margem, missionarios(X), canibais(Y)),
    barco(Margem, missionarios(X1), canibais(Y1))]
    ):-
        pessoasMargem(X), pessoasMargem(Y), margemAtual(Margem),
        pessoasBarco(X1), pessoasBarco(Y1),
        A is X1+Y1, A < 2,
        X2 is X-1, X3 is X1+1,
        Y =< X2.

acao(
    embarcaCanibal,
    [margem(Margem, missionarios(X), canibais(Y)),
    barco(Margem, missionarios(X1), canibais(Y1))],

    [margem(Margem, missionarios(X),canibais(Y2)),
    barco(Margem, missionarios(X1), canibais(Y3))],

    [margem(Margem, missionarios(X), canibais(Y)),
    barco(Margem, missionarios(X1), canibais(Y1))]
    ):-
        pessoasMargem(X), pessoasMargem(Y), margemAtual(Margem),
        pessoasBarco(X1), pessoasBarco(Y1),
        A is X1+Y1, A < 2,
        Y2 is Y-1, Y3 is Y1+1,
        Y2 =< X.


acao(
    travessia,
    [barco(Margem, missionarios(X), canibais(Y))],

    [barco(Margem1, missionarios(X), canibais(Y))],

    [barco(Margem, missionarios(X), canibais(Y))]
    ):-
        margemAtual(Margem), margemAtual(Margem1),
        Margem \= Margem1,
        pessoasBarco(X), pessoasBarco(Y),
        A is X+Y, A > 0.


acao(
    desembarcaMissionario,
    [barco(Margem, missionarios(X), canibais(Y)),
    margem(Margem, missionarios(X1), canibais(Y1))],

    [barco(Margem, missionarios(X2), canibais(Y)),
    margem(Margem, missionarios(X3), canibais(Y1))],

    [barco(Margem, missionarios(X), canibais(Y)),
    margem(Margem, missionarios(X1), canibais(Y1))]
    ):-
        pessoasBarco(X), pessoasBarco(Y), margemAtual(Margem),
        pessoasMargem(X1), pessoasMargem(Y1),
        X > 0,
        X2 is X-1, X3 is X1+1,
        Y1 =< X3.

acao(
    desembarcaCanibal,
    [barco(Margem, missionarios(X), canibais(Y)),
    margem(Margem, missionarios(X1), canibais(Y1))],

    [barco(Margem, missionarios(X), canibais(Y2)),
    margem(Margem, missionarios(X1), canibais(Y3))],

    [barco(Margem, missionarios(X), canibais(Y)),
    margem(Margem, missionarios(X1), canibais(Y1))]
    ):-
        pessoasBarco(X), pessoasBarco(Y), margemAtual(Margem),
        pessoasMargem(X1), pessoasMargem(Y1),
        Y > 0,
        Y2 is Y-1, Y3 is Y1+1,
        Y3 =< X1.