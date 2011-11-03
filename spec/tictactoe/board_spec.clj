(ns tictactoe.board-spec
  (:use
    [speclj.core]
    [tictactoe.board]
		[tictactoe.players]))

(defn equal-to-player1 [x]
	(= x (players :p1))
	)

(defn equal-to-player2 [x]
	(= x (players :p2))
	)

(describe "board"

	(it "should create a new board of size 3"
			(let [board (create-board 3)]
				(should= 3 (count board))
				(should= 9 (count (flatten board)))
				)
		)

	(it "fill row should return a full board"
		(should= 9 (count (flatten (fill-row (create-board 3) 0 (players :p1)))))
		)

	(it "should fill the first row of the board"
		(let [board (fill-row (create-board 3) 0 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 (get board 0)))
			)
		)

	(it "should fill the second row of the board"
		(let [board (fill-row (create-board 3) 1 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 (get board 1)))
			)
		)

	(it "should fill the third row of the board"
		(let [board (fill-row (create-board 3) 2 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 (get board 2)))
			)
		)

	(it "should fill the multiple rows of the board"
		(let [board (fill-row (fill-row (create-board 3) 2 (players :p1)) 0 (players :p2))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should= 3 (count (filter equal-to-player2 (flatten board))))
			(should (every? equal-to-player2 (get board 0)))
			(should (every? equal-to-player1 (get board 2)))
			)
		)

	(it "fill column should return a full 9 square board"
		(should= 9 (count (flatten (fill-column (create-board 3) 0 (players :p1)))))
		)

	(it "fill column should return a full 16 square board"
		(should= 16 (count (flatten (fill-column (create-board 4) 0 (players :p1)))))
		)

	(it "should fill the first column of the board"
		(let [board (fill-column (create-board 3) 0 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 0), (get (get board 1) 0), (get (get board 2) 0)]))
			)
		)

	(it "should fill the second column of the board"
		(let [board (fill-column (create-board 3) 1 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 1), (get (get board 1) 1), (get (get board 2) 1)]))
			)
		)

	(it "should fill the third column of the board"
		(let [board (fill-column (create-board 3) 2 (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 2), (get (get board 1) 2), (get (get board 2) 2)]))
			)
		)

	(it "should fill the fourth column of the board"
		(let [board (fill-column (create-board 4) 3 (players :p1))]
			(should= 4 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 3), (get (get board 1) 3), (get (get board 2) 3)]))
			)
		)

	(it "should fill the multiple columns of the board"
		(let [board (fill-column (fill-column (create-board 3) 2 (players :p1)) 0 (players :p2))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should= 3 (count (filter equal-to-player2 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 2), (get (get board 1) 2), (get (get board 2) 2)]))
			(should (every? equal-to-player2 [(get (get board 0) 0), (get (get board 1) 0), (get (get board 2) 0)]))
			)
		)

	(it "fill first diagonal should return a full 9 square board"
		(should= 9 (count (flatten (fill-first-diagonal (create-board 3) (players :p1)))))
		)

	(it "fill first diagonal should return a full 16 square board"
		(should= 16 (count (flatten (fill-first-diagonal (create-board 4) (players :p1)))))
		)

	(it "should fill the first diagonal of the board"
		(let [board (fill-first-diagonal (create-board 3) (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 0), (get (get board 1) 1), (get (get board 2) 2)]))
			)
		)

	(it "fill second diagonal should return a full 9 square board"
		(should= 9 (count (flatten (fill-first-diagonal (create-board 3) (players :p1)))))
		)

	(it "fill second diagonal should return a full 16 square board"
		(should= 16 (count (flatten (fill-first-diagonal (create-board 4) (players :p1)))))
		)

	(it "should fill the second diagonal of the board"
		(let [board (fill-second-diagonal (create-board 3) (players :p1))]
			(should= 3 (count (filter equal-to-player1 (flatten board))))
			(should (every? equal-to-player1 [(get (get board 0) 2), (get (get board 1) 1), (get (get board 2) 0)]))
			)
		)
	)

(run-specs)