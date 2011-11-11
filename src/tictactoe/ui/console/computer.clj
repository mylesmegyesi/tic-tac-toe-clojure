(ns tictactoe.ui.console.computer
  (:require
    [tictactoe.ui.console.utilities :as utilities]
    [tictactoe.computer :as computer]
    [tictactoe.players :as players]
    )
  )

(def computer-types
	{1 {:fn (partial computer/get-computer-move-forward 3) :name "Not so smart"}
	  2 {:fn (partial computer/get-computer-move-forward 4) :name "Smarter"}
    3 {:fn (partial computer/get-computer-move-forward 7) :name "Smartest"}
    }
	)

(defn- display-computer-types []
	(println "These are the available computer types,")
	(doseq [keyval computer-types]
		(println (str (key keyval) ". " ((val keyval) :name)))
		)
	)

(defn get-computer-type [player]
  (display-computer-types)
	(print "Please enter the level you would like the" (players/get-player-name player) "computer to play at: ")
	(flush)
	(utilities/get-input utilities/get-int (fn [computer-type] (contains? computer-types computer-type)) "That is not a valid computer type, please try again.")
  )

(defn get-computer-mover [game-state-fn player]
  (partial ((computer-types (get-computer-type player)) :fn) game-state-fn)
	)