hoofer(tony).
hoofer(shikuo).
hoofer(ellen).

hoofer(X) :- skier(X); mountainclimber(X); skier(X), mountainclimber(X).

likes(tony, snow).
likes(tony, rain).

likes(tony, _) :- dislikes(ellen, _).
dislikes(ellen, _) :- likes(tony, _).

skier(X) :- likes(X, snow).
mountainclimber(X) :- dislikes(X, rain).

start() :-
	write("Is there a member of the Hoofers Club who is a mountain climber but not a skier?"), nl,
	nl, write("The member of the Hoofers Club who is a mountain climber but not a skier is "),
	query_climber([shikuo, tony, ellen]).

query_climber([A|B]):- (mountainclimber(A) -> query_skier([A|B]), nl; query_climber(B) ).

query_skier([A|B]):- (skier(A) -> query_climber(B); write(A), nl ).
