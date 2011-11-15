(ns tictactoe.ui.console.game-spec
  (:use
    [speclj.core]
    [tictactoe.constants]
    )
  (:require
    [tictactoe.board :as board]
    [tictactoe.game-state :as game-state]
    [tictactoe.ui.console.utilities :as utilities]
    [tictactoe.ui.console.game :as game]
    [tictactoe.ui.console.user :as user]
    )
  )

(describe "get-game-type"

  (it "repeats until a valid game type is given"
		(with-in-str (apply str (interleave '(0 5 1) (repeat "\n")))
			(should= 1 (utilities/eat-output (game/get-game-type)))
			)
		)

	(it "gets the game type from the user"
		(with-in-str (apply str (interleave '(1) (repeat "\n")))
			(should= 1 (utilities/eat-output (game/get-game-type)))
			)
		)

  )

(describe "get-players"

	(it "gets a human vs. human player map"
		(with-in-str (apply str (interleave '(1) (repeat "\n")))
			(should= (game/game-types 1) (utilities/eat-output (game/get-players)))
			)
		)

	(it "gets a human vs. computer player map"
		(with-in-str (apply str (interleave '(2) (repeat "\n")))
			(should= (game/game-types 2) (utilities/eat-output (game/get-players)))
			)
		)

	(it "gets a computer vs. human player map"
		(with-in-str (apply str (interleave '(3) (repeat "\n")))
			(should= (game/game-types 3) (utilities/eat-output (game/get-players)))
			)
		)

	(it "gets a computer vs. computer player map"
		(with-in-str (apply str (interleave '(4) (repeat "\n")))
			(should= (game/game-types 4) (utilities/eat-output (game/get-players)))
			)
		)

	)

(describe "get-check-quadrants-option"

	(it "returns true when true is given"
		(with-in-str (apply str (interleave '("y") (repeat "\n")))
			(should (utilities/eat-output (game/get-check-quadrants-option)))
			)
		)

	(it "returns false when false is given"
		(with-in-str (apply str (interleave '("n") (repeat "\n")))
			(should-not (utilities/eat-output (game/get-check-quadrants-option)))
			)
		)

	)

(describe "game-loop"

  (it "repeats until the game is over"
    (with-in-str (apply str (interleave '("1 1" "2 2" "1 2" " 2 3" "1 3") (repeat "\n")))
			(utilities/eat-output (game/game-loop (board/create-board 3) P1 P2 {P1 user/get-user-move P2 user/get-user-move} (fn [board] (game-state/game-state board false))))
			)
    )

  )

(run-specs)