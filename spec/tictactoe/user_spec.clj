(ns tictactoe.user-spec
  (:use
    [speclj.core]
		[tictactoe.user]
		[tictactoe.console]
		[tictactoe.board]
		[tictactoe.players]))

(describe "user"

	(it "only accepts moves that are on the board"
		(with-in-str (apply str (interleave '("0 0" "10 10" "11 11" "1 1") (repeat "\n")))
			(should= [0 0] (binding [println mock-print print mock-print] (get-user-move (create-board 3) 0)))
			)
		)

	(it "only accepts indecies that aren't occupied"
		(with-in-str (apply str (interleave '("1 1" "1 2") (repeat "\n")))
			(should= [0 1] (binding [println mock-print print mock-print] (get-user-move (assoc-in (create-board 3) [0 0] (players :p1)) 0)))
			)
		)

	)