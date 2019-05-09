front(X,Y) :- behind(Y,X).

front(grumpy,dopey).
front(doc,droopy).
front(doc,happy).
front(happy,sleepy).
front(happy,smelly).
front(happy,bashful).
front(sneezy,dopey).
front(smelly,grumpy).
front(smelly,stumpy).
front(smelly,sneezy).
front(dopey,droopy).
front(sleepy,grumpy).
front(sleepy,bashful).
front(stumpy,dopey).

behind(X,Y) :- front(Y,X).

behind(stumpy,sneezy).
behind(stumpy,doc).
behind(sleepy,stumpy).
behind(sleepy,smelly).
behind(sleepy,happy).
behind(bashful,smelly).
behind(bashful,droopy).
behind(bashful,sleepy).
behind(dopey,sneezy).
behind(dopey,doc).
behind(dopey,sleepy).
behind(smelly,doc).

start() :- order([bashful, droopy, dopey, doc, happy, sneezy, smelly, sleepy, stumpy, grumpy]).

order([Dwarf|[]], Order) :- write("Order is: "), write(Order), nl.

order([Dwarf|Others], [First|[]]) :- (front(Dwarf, First) -> order(Others, [Dwarf, First]) ; order([Others], [First, Dwarf])
	).

order([Dwarf|Others], [First|Rest]) :- (front(Dwarf, First) -> order(Others, [Dwarf, First|Rest]) ; order([Dwarf|Others], [First, Rest])
	).




