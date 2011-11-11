(ns tictactoe.ui.console.game-state
  (:use
    [tictactoe.constants :only (game-states)])
  )

(defn print-state [state]
	(if (= state (game-states :player1-won))
		(println "Player 1 has won!")
		(if (= state (game-states :player2-won))
			(println "Player 2 has won!")
			(println "Draw!")
			)
		)
	)