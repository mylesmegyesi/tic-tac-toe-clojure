(ns tictactoe.user
	(:use
		[tictactoe.board]
		[tictactoe.console]))

(defn get-user-move [board player]
	(print "Please enter your move (ex: 1 1, for row 1 column 1): ")
	(let [error-message "That is not a valid move, please try again."]
		(let [move (get-index error-message)]
			(if (valid-move board move)
				move
				(do
					(println error-message)
					(recur board player)
					)
				)
			)
		)
	)