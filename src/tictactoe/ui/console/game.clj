(ns tictactoe.ui.console.game
	(:use
		[tictactoe.constants :only (game-states players)]
	  )
  (:require
	  [tictactoe.players :as players]
	  [tictactoe.game-state :as game-state]
	  [tictactoe.ui.console.computer :as computer]
	  [tictactoe.ui.console.user :as user]
	  [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.board :as board]
	  [tictactoe.ui.console.game-state :as console.game-state]
	  )
	)

(def game-types
	{1 {(players :p1) user/get-user-mover, (players :p2) user/get-user-mover, :name "Human vs. Human"}
		2 {(players :p1) user/get-user-mover, (players :p2) computer/get-computer-mover, :name "Human vs. Computer"}
		3 {(players :p1) computer/get-computer-mover, (players :p2) user/get-user-mover, :name "Computer vs. Human"}
		4 {(players :p1) computer/get-computer-mover, (players :p2) computer/get-computer-mover, :name "Computer vs. Computer"}}
	)

(defn- display-game-types []
	(println "These are the available game types,")
	(doseq [keyval game-types]
		(println (str (key keyval) ". " ((val keyval) :name)))
		)
	)

(defn get-game-type []
	(display-game-types)
	(utilities/get-input "Please enter the type of game you would like to play: " utilities/get-int (fn [game-type] (contains? game-types game-type)) "That is not a valid game type, please try again.")
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

(defn get-check-quadrants-option []
	(utilities/get-input "Would you like to turn on the quadrant win option (yes or no): " utilities/get-bool (fn [& args] true) "That is not a valid answer, please try again.")
	)

(defn game-loop [board current-player next-player player-movers game-state-fn]
  (loop [board board current-player current-player next-player next-player]
    (board/print-board board)
    (let [state (game-state-fn board)]
  		(if (not= (game-states :playing) state)
  			(console.game-state/print-state state)
  			(recur (assoc-in board (get-move player-movers current-player board) current-player) next-player current-player)
  			)
  		)
    )
  )

(defn play []
	(println "Welcome to Tic-Tac-Toe!")
	(let [player-options (get-players) check-quadrants (get-check-quadrants-option) game-state-fn (fn [board] (game-state/game-state board check-quadrants)) player-movers (get-player-movers player-options game-state-fn)]
		(game-loop (board/get-board) (players :p1) (players :p2) player-movers game-state-fn)
		)
	)