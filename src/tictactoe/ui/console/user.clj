(ns tictactoe.ui.console.user
  (:require
    [tictactoe.players :as players]
    [tictactoe.board :as board]
    [tictactoe.ui.console.utilities :as utilities]
    )
  )

(defn get-user-move [player board]
	(print (str (players/get-player-name player) ", please enter your move (ex: 1 1, for row 1 column 1): "))
	(flush)
	(utilities/get-input utilities/get-index (fn [move] (board/valid-move board move)) "That is not a valid move, please try again.")
	)

(defn get-user-mover [game-state-fn player]
  get-user-move
  )