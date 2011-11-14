(ns tictactoe.ui.console.board-spec
	(:use
    [speclj.core]
    )
  (:require
    [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.board :as board]
    )
  )

(describe "get-board-size"

  (it "gets a number greater than or equal to three"
  	(with-in-str (apply str (interleave '(-1 0 1 2 3) (repeat "\n")))
  		(should= 3 (utilities/eat-output (board/get-board-size)))
  		)
  	)

  (it "gets a number less than or equal to the max board size"
  	(with-in-str (apply str (interleave (list 7 4) (repeat "\n")))
  		(should= 4 (utilities/eat-output (board/get-board-size)))
  		)
  	)

  )

(run-specs)