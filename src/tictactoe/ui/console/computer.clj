(ns tictactoe.ui.console.computer
  (:require
    [tictactoe.ui.console.utilities :as utilities]
    [tictactoe.computer :as computer]
    [tictactoe.players :as players]
    )
  )

(defn- display-computer-types []
	(println "These are the available computer types,")
	(doseq [keyval computer/computer-types]
		(println (str (key keyval) ". " ((val keyval) :name)))
		)
	)

(defn get-computer-type [player]
  (display-computer-types)
	(utilities/get-input (str "Please enter the level you would like the " (players/get-player-name player) " computer to play at: ") utilities/get-int (fn [computer-type] (contains? computer/computer-types computer-type)) "That is not a valid computer type, please try again.")
  )

(defn get-computer-mover [game-state-fn player]
  (partial ((computer/computer-types (get-computer-type player)) :fn) game-state-fn)
	)