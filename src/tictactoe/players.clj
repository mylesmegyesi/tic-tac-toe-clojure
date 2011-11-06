(ns tictactoe.players)

(def players {:p1 "X" , :p2 "O", :none " "})

(defn get-other-player [player]
	(if (= player (players :p1))
		(players :p2)
		(if (= player (players :p2))
			(players :p1)
			(players :none)
			)
		)
	)

(defn get-player-name [player]
	(if (= player (players :p1))
		"Player 1"
		"Player 2"
		)
	)