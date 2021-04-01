quicksortAscending([],[]).
quicksortAscending([H|T],Sorted) :-
	partitionAscending(H,T,Smaller,Greater),
	quicksortAscending(Smaller, SortedSmaller),
	quicksortAscending(Greater,SortedGreater),
	append(SortedSmaller,[H|SortedGreater],Sorted).

partitionAscending(_,[],[],[]).
partitionAscending(P,[H|T],[H| Smaller],Greater) :-
	H =< P,
	partitionAscending(P,T, Smaller,Greater).
partitionAscending(P,[H|T], Smaller,[H|Greater]):-
    H > P,
	partitionAscending(P,T, Smaller,Greater).


quicksortDescending([],[]).
quicksortDescending([H|T],Sorted) :-
	partitionDescending(H,T,Smaller,Greater),
	quicksortDescending(Smaller, SortedSmaller),
	quicksortDescending(Greater,SortedGreater),
	append(SortedSmaller,[H|SortedGreater],Sorted).

partitionDescending(_,[],[],[]).
partitionDescending(P,[H|T],[H| Smaller],Greater) :-
	H > P,
	partitionDescending(P,T, Smaller,Greater).
partitionDescending(P,[H|T], Smaller,[H|Greater]):-
    H =< P,
	partitionDescending(P,T, Smaller,Greater).