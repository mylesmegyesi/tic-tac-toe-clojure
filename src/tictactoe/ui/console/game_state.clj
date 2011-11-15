(ns tictactoe.ui.console.game-state
  (:use
    [tictactoe.constants])
  )

(defn print-state [state]
	(if (= state :player1-won)
		(println "Player 1 has won!")
		(if (= state :player2-won)
			(println "Player 2 has won!")
			(println "Draw!")
			)
		)
	)