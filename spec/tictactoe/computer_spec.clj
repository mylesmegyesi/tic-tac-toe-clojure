(ns tictactoe.computer-spec
  (:use
    [speclj.core]
		[tictactoe.constants :only (game-states scores players)]
	  )
	(:require
	  [tictactoe.board :as board]
	  [tictactoe.computer :as computer]
	  [tictactoe.game-state :as game-state]
	  [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.game-state :as console.game-state]
	  )
	)

(defn recurse [board game-state-fn computer-move-fn]
	(let [size (board/row-size board)]
		(doseq [index (board/open-indecies board)]
			(let [board-with-p1-move (assoc-in board index (players :p1))]
				(let [p1-state (game-state-fn board-with-p1-move)]
					(if (not= p1-state (game-states :playing))
						(should-not= (game-states :player1-won) p1-state)
						(let [board-with-p2-move (assoc-in board-with-p1-move (computer-move-fn game-state-fn (players :p2) board-with-p1-move) (players :p2))]
							(let [p2-state (game-state-fn board-with-p2-move)]
								(if (not= p2-state (game-states :playing))
									(should-not= (game-states :player1-won) p2-state)
									(recurse board-with-p2-move game-state-fn computer-move-fn))))))))))

(describe "computer"

	(it "blocks in position row 2 column 1"
		(let [board (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 0] (players :p1)) [2 0] (players :p1)) [1 1] (players :p2))]
			(should= [1 0] (computer/get-computer-move (fn [board] (game-state/game-state board false)) (players :p2) board))
			)
		)

	(it "blocks in position row 3 column 3"
		(let [board (assoc-in (assoc-in (board/create-board 3) [0 0] (players :p1)) [1 1] (players :p1))]
			(should= [2 2] (computer/get-computer-move (fn [board] (game-state/game-state board false)) (players :p2) board))
			)
		)

	(it "blocks in position row 3 column 1"
		(let [board (assoc-in (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 1] (players :p1)) [0 2] (players :p1)) [1 2] (players :p1)) [1 0] (players :p2)) [2 2] (players :p2))]
			(should= [2 0] (computer/get-computer-move (fn [board] (game-state/game-state board false)) (players :p2) board))
			)
		)

	(it "picks more immediate moves"
		(let [board (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 1] (players :p1)) [1 2] (players :p1)) [2 2] (players :p2))]
			(should= [1 0] (computer/get-computer-move (fn [board] (game-state/game-state board true)) (players :p2) board))
			)
		)

  (it "get-computer-move-tail-depth-pruning never looses board size 3 without quadrant checking"
    (recurse (board/create-board 3) (fn [board] (game-state/game-state board false)) computer/get-computer-move)
		)

	(it "never looses board size 4 without quadrant checking"
	  (recurse (board/create-board 4) (fn [board] (game-state/game-state board false)) computer/get-computer-move)
		)

	)