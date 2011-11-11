(ns tictactoe.ui.console.user-spec
  (:use
    [speclj.core]
		[tictactoe.constants :only (scores players)]
		)
	(:require
	  [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.user :as user]
	  [tictactoe.board :as board]
	  )
	)

(describe "user"

	(it "only accepts moves that are on the board"
		(with-in-str (apply str (interleave '("0 0" "10 10" "11 11" "1 1") (repeat "\n")))
			(should= [0 0] (binding [println utilities/mock-print print utilities/mock-print] (user/get-user-move 0 (board/create-board 3))))
			)
		)

	(it "only accepts indecies that aren't occupied"
		(with-in-str (apply str (interleave '("1 1" "1 2") (repeat "\n")))
			(should= [0 1] (binding [println utilities/mock-print print utilities/mock-print] (user/get-user-move 0 (assoc-in (board/create-board 3) [0 0] (players :p1)))))
			)
		)

	)