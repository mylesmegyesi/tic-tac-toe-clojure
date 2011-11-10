(ns tictactoe.computer-spec
  (:use
    [speclj.core]
		[tictactoe.computer]
		[tictactoe.game-state]
    [tictactoe.board]
		[tictactoe.players]))

(defn recurse [board check-quadrants]
	(let [size (row-size board)]
		(doseq [index (open-indecies board)]
			(let [board-with-p1-move (assoc-in board index (players :p1))]
				(let [p1-state (game-state board-with-p1-move check-quadrants)]
					(if (not= p1-state (game-states :playing))
						(should-not= p1-state (game-states :player1-won))
						(let [board-with-p2-move (assoc-in board-with-p1-move (get-computer-move (players :p2) board-with-p1-move check-quadrants) (players :p2))]
							(let [p2-state (game-state board-with-p2-move check-quadrants)]
								(if (not= p2-state (game-states :playing))
									(should-not= p2-state (game-states :player1-won))
									(recurse board-with-p2-move check-quadrants)
									)
								)
							)
						)
					)
				)
			)
		)
	)

(describe "computer"

	(it "blocks in position row 2 column 1"
		(let [board (assoc-in (assoc-in (assoc-in (create-board 3) [0 0] (players :p1)) [2 0] (players :p1)) [1 1] (players :p2))]
			(should= [1 0] (get-computer-move (players :p2) board false))
			)
		)

	(it "blocks in position row 1 column 1"
		(let [board (assoc-in (assoc-in (create-board 3) [0 0] (players :p1)) [1 1] (players :p1))]
			(should= [2 2] (get-computer-move (players :p2) board false))
			)
		)

	(it "blocks in position row 3 column 1"
		(let [board (assoc-in (assoc-in (assoc-in (assoc-in (assoc-in (create-board 3) [1 1] (players :p1)) [0 2] (players :p1)) [1 2] (players :p1)) [1 0] (players :p2)) [2 2] (players :p2))]
			(should= [2 0] (get-computer-move (players :p2) board false))
			)
		)

	(it "picks more immediate moves"
		(let [board (assoc-in (assoc-in (assoc-in (create-board 3) [1 1] (players :p1)) [1 2] (players :p1)) [2 2] (players :p2))]
			(should= [1 0] (get-computer-move (players :p2) board true))
			)
		)

	(it "never looses board size 3 without quadrant checking"
		(recurse (create-board 3) false)
		)

	(it "never looses board size 4 without quadrant checking"
		;(recurse (create-board 4) false)
		)

	)