(ns tictactoe.game
	(:use
		[tictactoe.computer]
		[tictactoe.user]
		[tictactoe.players]
		[tictactoe.console]
		[tictactoe.board]
		[tictactoe.game-state]))

(def game-types
	{1 {(players :p1) get-user-mover, (players :p2) get-user-mover, :name "Human vs. Human"}
		2 {(players :p1) get-user-mover, (players :p2) get-computer-mover, :name "Human vs. Computer"}
		3 {(players :p1) get-computer-mover, (players :p2) get-user-mover, :name "Computer vs. Human"}
		4 {(players :p1) get-computer-mover, (players :p2) get-computer-mover, :name "Computer vs. Computer"}}
	)

(defn- display-game-types []
	(println "These are the available game types,")
	(doseq [keyval game-types]
		(println (str (key keyval) ". " ((val keyval) :name)))
		)
	)

(defn get-game-type []
	(display-game-types)
	(print "Please enter the type of game you would like to play: ")
	(flush)
	(let [error-message "That is not a valid game type, please try again."]
	 	(loop [],
			(let [game-type (get-int error-message)]
				(if (contains? game-types game-type)
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

(defn- get-player-movers [game-type game-state-fn]
  {(players :p1) ((game-type (players :p1)) game-state-fn (players :p1))
    (players :p2) ((game-type (players :p2)) game-state-fn (players :p2))}
  )

(defn- get-move [player-movers player board]
	((player-movers player) player board)
	)

(defn- get-check-quadrants-option []
	(print "Would you like to turn on the quadrant win option (yes or no): ")
	(flush)
	(let [error-message "That is not a valid answer, please try again."]
		(get-bool error-message)
		)
	)

(defn play []
	(println "Welcome to Tic-Tac-Toe!")
	(let [check-quadrants (get-check-quadrants-option) game-state-fn (fn [board] (game-state board check-quadrants)) player-movers (get-player-movers (get-players) game-state-fn)]
		(loop [board (get-board) current (players :p1) next (players :p2)]
			(print-board board)
			(let [state (game-state-fn board)]
				(if (not= (game-states :playing) state)
					(print-state state)
					(recur (assoc-in board (get-move player-movers current board) current) next current)
					)
				)
			)
		)
	)