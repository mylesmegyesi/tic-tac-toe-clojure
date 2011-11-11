(ns tictactoe.game-state
	(:use
		[tictactoe.constants :only (game-states players)]
	  )
	(:require
	  [tictactoe.board :as board]
	  )
	)

(defn- player-won-row [board player]
	(some #(every? (partial = player) %1) board)
	)

(defn- player-won-column [board player]
	(some #(every? (partial = player) %1) (board/invert board))
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

(defn- player-won-quadrant-index [board player index]
	(let [i (first index) j (second index)]
		(= player (get (get board i) j) (get (get board i) (+ j 1)) (get (get board (+ i 1)) j) (get (get board (+ i 1)) (+ j 1)))
		)
	)

(defn- player-won-quadrant [board player]
	(let [row-size (board/row-size board)]
		(not= nil
			(some
				(partial player-won-quadrant-index board player)
				(for [i (range 0 (- row-size 1)) j (range 0 (- row-size 1))]
					[i j]
					)
				)
			)
		)
	)

(defn- player-won [board player check-quadrants]
	(or
		(player-won-row board player)
		(player-won-column board player)
		(player-won-diagonal board player)
		(if check-quadrants
			(player-won-quadrant board player)
			false
			)
		)
	)

(defn- board-full? [board]
	(= 0 (count (board/open-indecies board)))
	)

(defn game-state [board check-quadrants]
	(if (player-won board (players :p1) check-quadrants)
		(game-states :player1-won)
		(if (player-won board (players :p2) check-quadrants)
			(game-states :player2-won)
			(if (board-full? board)
				(game-states :draw)
				(game-states :playing))
			)
		)
	)