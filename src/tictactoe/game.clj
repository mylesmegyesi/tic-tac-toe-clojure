(ns tictactoe.game
	(:use
		[tictactoe.computer]
		[tictactoe.user]
		[tictactoe.players]
		[tictactoe.console]
		[tictactoe.board]
		[tictactoe.game-state]))

(def game-types
	{1 {(players :p1) get-user-move (players :p2) get-user-move}
		2 {(players :p1) get-user-move (players :p2) get-computer-move}
		3 {(players :p1) get-computer-move (players :p2) get-computer-move}}
	)

(defn- display-game-types []
	(println "These are the available game types,")
	(println "1. Human vs. Human")
	(println "2. Human vs. Computer")
	(println "3. Computer vs. Computer")
	)

(defn get-game-type []
	(display-game-types)
	(print "Please enter the type of game you would like to play: ")
	(flush)
	(let [error-message "That is not a valid game type, please try again."]
	 	(loop []
			(let [game-type (get-int error-message)]
				(if (and (>= game-type 1) (<= game-type (count game-types)))
					game-type
					(do
						(println error-message)
						(recur)
						)
					)
				)
			)
		)
	)

(defn get-players []
	(game-types (get-game-type))
	)

(defn- get-move [player-movers player board]
	((player-movers player) board player)
	)

(defn play []
	(println "Welcome to Tic-Tac-Toe!")
	(let [player-movers (get-players)]
		(loop [board (create-board 3) current (players :p1) next (players :p2)]
			(print-board board)
			(let [state (game-state board)]
				(if (not= (game-states :playing) state)
					(print-state state)
					(recur (assoc-in board (get-move player-movers current board) current) next current)
					)
				)
			)
		)
	)