beat(garry, barry). % (2)

beat(X, Y) :- (X \= garry -> wearred(X), wearblue(Y); wearblue(Y)). %(7)

wearred(X) :- write(X), write(" wears red."), nl.

wearblue(X) :- write(X), write(" wears blue."), nl.

weargreen(X) :- place(X, 2), number(X, 3), write(X), write(" wears green."), nl.

wearyellow(barry). % (7)

wearyellow(X) :- beat(larry, X), write(X), write(" wears yellow."), nl. % (3)

place(harry, 4). % (5)

place(X, Y) :- write(X), write("'s place is "), write(Y), nl, number(X, Y).

number(X,Y) :- write(X), write("'s number is "), write(Y), nl.

%% place(X, 2) :- weargreen(X). % (6)

%% number(X, Y) :- place(X, Y). % (1)

%% number(X, 3) :- weargreen(X). % (4)

number(X, 2) :- place(X, 1). % (5)

start() :- write("The order is: "), query([gary, barry, larry, harry]).

query([A,B|C]) :- ().











