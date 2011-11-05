(ns tictactoe.computer-spec
  (:use
    [speclj.core]
		[tictactoe.computer]
		[tictactoe.game-state]
    [tictactoe.board]
		[tictactoe.players]))

(defn recurse [board]
	(let [size (row-size board)]
		(map
			(fn [[i j]]
				(let [board-with-p1-move (assoc-in board [i j] (players :p1))]
					(let [p1-state (game-state board-with-p1-move)]
						(if (not= p1-state (game-states :playing))
							(should-not= p1-state (game-states :player1-won))
							(let [board-with-p2-move (assoc-in board-with-p1-move (get-computer-move board-with-p1-move (players :p2)) (players :p2))]
								(let [p2-state (game-state board-with-p2-move)]
									(if (not= p2-state (game-states :playing))
										(should-not= p2-state (game-states :player1-won))
										(recurse board-with-p2-move)
										)
									)
								)
							)
						)
					)
				)
			(open-indecies board)
			)
		)
	)

(describe "computer"

	(it "blocks in position row 2 column 1"
		(let [board (assoc-in (assoc-in (assoc-in (create-board 3) [0 0] (players :p1)) [2 0] (players :p1)) [1 1] (players :p2))]
			(should= [1 0] (get-computer-move board (players :p2)))
			)
		)

	(it "blocks in position row 1 column 1"
		(let [board (assoc-in (assoc-in (create-board 3) [0 0] (players :p1)) [1 1] (players :p1))]
			(should= [2 2] (get-computer-move board (players :p2)))
			)
		)

	(it "never looses"
		(doall (flatten (recurse (create-board 3))))
		)
	)