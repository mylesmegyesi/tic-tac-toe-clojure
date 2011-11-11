(ns tictactoe.ui.console.game-spec
  (:use
    [speclj.core]
    )
  (:require
    [tictactoe.ui.console.utilities :as utilities]
    [tictactoe.ui.console.game :as game]
    )
  )

(describe "game"

	(it "get game type repeats until a valid game type is given"
		(with-in-str (apply str (interleave '(0 5 1) (repeat "\n")))
			(should= 1 (binding [println utilities/mock-print print utilities/mock-print] (game/get-game-type)))
			)
		)

	(it "get game type gets the game type from the user"
		(with-in-str (apply str (interleave '(1) (repeat "\n")))
			(should= 1 (binding [println utilities/mock-print print utilities/mock-print] (game/get-game-type)))
			)
		)

	(it "get players gets the correct player map based on the given game type"
		(with-in-str (apply str (interleave '(1) (repeat "\n")))
			(should= (game/game-types 1) (binding [println utilities/mock-print print utilities/mock-print] (game/get-players)))
			)
		)

	(it "get players gets the correct player map based on the given game type"
		(with-in-str (apply str (interleave '(2) (repeat "\n")))
			(should= (game/game-types 2) (binding [println utilities/mock-print print utilities/mock-print] (game/get-players)))
			)
		)

	(it "get players gets the correct player map based on the given game type"
		(with-in-str (apply str (interleave '(5 3) (repeat "\n")))
			(should= (game/game-types 3) (binding [println utilities/mock-print print utilities/mock-print] (game/get-players)))
			)
		)

	)

(run-specs)