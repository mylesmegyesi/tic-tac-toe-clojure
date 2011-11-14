(ns tictactoe.board-spec
  (:use
    [speclj.core]
		[tictactoe.constants :only (players)]
	  )
	(:require
	  [tictactoe.board :as board]
	  )
	)

(defn- equal-to-player1 [x]
	(= x (players :p1))
	)

(defn- equal-to-player2 [x]
	(= x (players :p2))
	)

  (defn- equal-to-none [x]
  	(= x (players :none))
  	)

(describe "create-board"

  (it "creates a new board of size 3"
		(let [board (board/create-board 3)]
			(should= 3 (count board))
			(should= 9 (count (flatten board)))
			(should (every? equal-to-none (flatten board)))
			)
		)

  (it "creates a new board of size 4"
		(let [board (board/create-board 4)]
			(should= 4 (count board))
			(should= 16 (count (flatten board)))
			(should (every? equal-to-none (flatten board)))
			)
		)

  )

(describe "fill-row"

  (it "does not harm the original board"
		(should= 9 (count (flatten (board/fill-row (board/create-board 3) 0 (players :p1)))))
		)

  (it "fills the first row of the board"
		(let [board (board/fill-row (board/create-board 3) 0 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 (get board 0)))
			)
		)

	(it "fills the second row of the board"
		(let [board (board/fill-row (board/create-board 3) 1 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 (get board 1)))
			)
		)

	(it "fills the third row of the board"
		(let [board (board/fill-row (board/create-board 3) 2 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 (get board 2)))
			)
		)

	(it "fills multiple rows of the board"
		(let [board (board/fill-row (board/fill-row (board/create-board 3) 2 (players :p1)) 0 (players :p2))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should= 3 (count (filter equal-to-player2 (flatten board))))
			(should (every? equal-to-player2 (get board 0)))
			(should (every? equal-to-player1 (get board 2)))
			)
		)

  )

(describe "fill-column"

  (it "does not harm the original board size 3"
		(should= 9 (count (flatten (board/fill-column (board/create-board 3) 0 (players :p1)))))
		)

	(it "does not harm the original board size 4"
		(should= 16 (count (flatten (board/fill-column (board/create-board 4) 0 (players :p1)))))
		)

	(it "fills the first column of the board"
		(let [board (board/fill-column (board/create-board 3) 0 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 0), (get (get board 1) 0), (get (get board 2) 0)]))
			)
		)

	(it "fills the second column of the board"
		(let [board (board/fill-column (board/create-board 3) 1 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 1), (get (get board 1) 1), (get (get board 2) 1)]))
			)
		)

	(it "fills the third column of the board"
		(let [board (board/fill-column (board/create-board 3) 2 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 2), (get (get board 1) 2), (get (get board 2) 2)]))
			)
		)

	(it "fills the fourth column of the board"
		(let [board (board/fill-column (board/create-board 4) 3 (players :p1))]
			(should= 4 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 3), (get (get board 1) 3), (get (get board 2) 3)]))
			)
		)

	(it "fills multiple columns of the board"
		(let [board (board/fill-column (board/fill-column (board/create-board 3) 2 (players :p1)) 0 (players :p2))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should= 3 (count (filter equal-to-player2 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 2), (get (get board 1) 2), (get (get board 2) 2)]))
			(should (every? equal-to-player2 [(get (get board 0) 0), (get (get board 1) 0), (get (get board 2) 0)]))
			)
		)
  )

(describe "fill-first-diagonal"

  (it "does not harm the original board size 3"
  	(should= 9 (count (flatten (board/fill-first-diagonal (board/create-board 3) (players :p1)))))
  	)

  (it "does not harm the original board size 4"
  	(should= 16 (count (flatten (board/fill-first-diagonal (board/create-board 4) (players :p1)))))
  	)

  (it "fills the left to right diagonal of the board"
  	(let [board (board/fill-first-diagonal (board/create-board 3) (players :p1))]
  		(should= 3 (count (filter equal-to-player1 (flatten board))))
  		(should (every? equal-to-player1 [(get (get board 0) 0), (get (get board 1) 1), (get (get board 2) 2)]))
  		)
  	)

  )

(describe "fill-second-diagonal"

  (it "does not harm the original board size 3"
		(should= 9 (count (flatten (board/fill-first-diagonal (board/create-board 3) (players :p1)))))
		)

	(it "does not harm the original board size 4"
		(should= 16 (count (flatten (board/fill-first-diagonal (board/create-board 4) (players :p1)))))
		)

	(it "fills the right to left diagonal of the board"
		(let [board (board/fill-second-diagonal (board/create-board 3) (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 2), (get (get board 1) 1), (get (get board 2) 0)]))
			)
		)
  )

(describe "board-size"

  (it "returns the size of the board"
		(should= 3 (board/board-size (board/create-board 3)))
		)

  )

(describe "open-indecies"

  (it "returns all indecies for an empty board"
		(should= 9 (count (board/open-indecies (board/create-board 3))))
		)

	(it "returns nothing for a full board"
		(should= 0 (count (board/open-indecies (board/fill-row (board/fill-row (board/fill-row (board/create-board 3) 0 (players :p1)) 1 (players :p1)) 2 (players :p1)))))
		)

	(it "returns only row 1 column 1"
		(should= [[0 0]] (board/open-indecies (assoc-in (board/fill-row (board/fill-row (board/fill-row (board/create-board 3) 0 (players :p1)) 1 (players :p1)) 2 (players :p1)) [0 0] (players :none))))
		)

  )

(describe "valid-move"

	(it "returns true for an empty space"
		(should (board/valid-move (board/create-board 3) [0 0]))
		)

	(it "returns false for an occupied space"
		(should-not (board/valid-move (assoc-in (board/create-board 3) [0 0] (players :p2)) [0 0]))
		)

	)

(run-specs)