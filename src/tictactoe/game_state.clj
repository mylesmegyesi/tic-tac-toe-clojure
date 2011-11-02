(ns tictactoe.game-state
	(:use
		[tictactoe.players]))

(def game-states {:playing 0, :player1-won 1, :player2-won 2, :draw 3})

(defn player-won [board player]
	(or
		(and (= player (get board 0) (get board 1) (get board 2)))
		(and (= player (get board 3) (get board 4) (get board 5)))
		(and (= player (get board 6) (get board 7) (get board 8)))
		)
	)

(defn game-state [board]
	(if (player-won board (players :p1))
	(game-states :player1-won))
	)