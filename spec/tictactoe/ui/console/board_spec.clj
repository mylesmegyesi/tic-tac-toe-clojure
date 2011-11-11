(ns tictactoe.ui.console.board-spec
	(:use
    [speclj.core]
    )
  (:require
    [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.board :as board]
    )
  )

(it "get board size gets an number greater than or equal to three"
	(with-in-str (apply str (interleave '(-1 0 1 2 3) (repeat "\n")))
		(should= 3 (binding [println utilities/mock-print print utilities/mock-print] (board/get-board-size)))
		)
	)

(it "get board size gets an number less than or equal to the max board size"
	(with-in-str (apply str (interleave (list 5 6 7 4) (repeat "\n")))
		(should= 4 (binding [println utilities/mock-print print utilities/mock-print] (board/get-board-size)))
		)
	)