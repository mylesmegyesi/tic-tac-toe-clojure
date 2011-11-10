(ns tictactoe.user
	(:use
		[tictactoe.board]
		[tictactoe.players]
		[tictactoe.console]))

(defn get-user-move [player board check-quadrants]
	(print (str (get-player-name player) ", please enter your move (ex: 1 1, for row 1 column 1): "))
	(flush)
	(loop []
		(let [error-message "That is not a valid move, please try again."]
			(let [move (get-index error-message)]
				(if (valid-move board move)
					move
					(do
						(println error-message)
						(recur)
						)
					)
				)
			)
		)
	)