(ns tictactoe.game-spec
  (:use
    [speclj.core]
    [tictactoe.game]
		[tictactoe.board]
		[tictactoe.console]))

(describe "game"

	(it "get game type repeats until a valid game type is given"
		(with-in-str (apply str (interleave '(0 5 1) (repeat "\n")))
			(should= 1 (binding [println mock-print print mock-print] (get-game-type)))
			)
		)

	(it "get game type gets the game type from the user"
		(with-in-str (apply str (interleave '(1) (repeat "\n")))
			(should= 1 (binding [println mock-print print mock-print] (get-game-type)))
			)
		)

	(it "get players gets the correct player map based on the given game type"
		(with-in-str (apply str (interleave '(1) (repeat "\n")))
			(should= (game-types 1) (binding [println mock-print print mock-print] (get-players)))
			)
		)

	(it "get players gets the correct player map based on the given game type"
		(with-in-str (apply str (interleave '(2) (repeat "\n")))
			(should= (game-types 2) (binding [println mock-print print mock-print] (get-players)))
			)
		)

	(it "get players gets the correct player map based on the given game type"
		(with-in-str (apply str (interleave '(5 3) (repeat "\n")))
			(should= (game-types 3) (binding [println mock-print print mock-print] (get-players)))
			)
		)

	)

(run-specs)