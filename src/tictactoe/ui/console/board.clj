(ns tictactoe.ui.console.board
  (:require
    [tictactoe.board :as board]
    [tictactoe.ui.console.utilities :as utilities]
    )
  )

(defn- row-str [row]
	(str (reduce #(str %1 " " %2) row) "\n" (reduce #(str %1 " " %2) (repeat (count row) "-")) "\n")
	)

(defn- board-str [board]
	(reduce str (map row-str board))
	)

(defn print-board [board]
	(println (board-str board))
	)

(defn board-sizes-str [valid-sizes]
  (str (first valid-sizes) "-" (last valid-sizes))
  )

(defn get-board-size []
	(print (str "Please enter the size of board you would like to play on (" (board-sizes-str board/valid-board-sizes) "): "))
	(flush)
	(utilities/get-input utilities/get-int (fn [board-size] (some (partial = board-size) board/valid-board-sizes)) "That is not a valid board size, please try again.")
	)

(defn get-board []
	(board/create-board (get-board-size))
	)