(ns tictactoe.game-state
	(:use
		[tictactoe.board]
		[tictactoe.players]))

(def game-states {:playing 0, :player1-won 1, :player2-won 2, :draw 3})

(defn print-state [state]
	(if (= state (game-states :player1-won))
		(println "Player 1 has won!")
		(if (= state (game-states :player2-won))
			(println "Player 2 has won!")
			(println "Draw!")
			)
		)
	)

(defn- player-won-row [board player]
	(some #(every? (partial = player) %1) board)
	)

(defn- player-won-column [board player]
	(some #(every? (partial = player) %1) (invert board))
	)

(defn- player-won-first-diagonal [board player]
	(loop [i 0 size (count board)]
		(if (= i size)
			true
			(if (not= (get (get board i) i) player)
				false
				(recur (inc i) size))
			)
		)
	)

(defn- player-won-second-diagonal [board player]
	(loop [i 0 size (count board)]
		(if (= i size)
			true
			(if (not= (get (get board i) (- (- size 1) i)) player)
				false
				(recur (inc i) size))
			)
		)
	)

(defn- player-won-diagonal [board player]
	(or
		(player-won-first-diagonal board player)
		(player-won-second-diagonal board player)
		)
	)

(defn- player-won [board player]
	(or
		(player-won-row board player)
		(player-won-column board player)
		(player-won-diagonal board player)
		)
	)

(defn- board-full? [board]
	(= 0 (count (open-indecies board)))
	)

(defn game-state [board]
	(if (player-won board (players :p1))
		(game-states :player1-won)
		(if (player-won board (players :p2))
			(game-states :player2-won)
			(if (board-full? board)
				(game-states :draw)
				(game-states :playing))
			)
		)
	)