(ns tictactoe.board-spec
  (:use
    [speclj.core]
    [tictactoe.board]
		[tictactoe.players]
		[tictactoe.console]))

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

	(it "row size returns the square root of the board"
		(should= 3 (row-size (create-board 3)))
		)

	(it "open indecies returns all indecies for an empty board"
		(should= 9 (count (open-indecies (create-board 3))))
		)

	(it "open indecies returns nothing for a full board"
		(should= 0 (count (open-indecies (fill-row (fill-row (fill-row (create-board 3) 0 (players :p1)) 1 (players :p1)) 2 (players :p1)))))
		)

	(it "open indecies returns only row 1 column 1"
		(should= [[0 0]] (open-indecies (assoc-in (fill-row (fill-row (fill-row (create-board 3) 0 (players :p1)) 1 (players :p1)) 2 (players :p1)) [0 0] (players :none))))
		)

	(it "valid move returns true for an empty space"
		(should (valid-move (create-board 3) [0 0]))
		)

	(it "valid move returns false for an occupied space"
		(should-not (valid-move (assoc-in (create-board 3) [0 0] (players :p2)) [0 0]))
		)

	(it "get board size gets an number greater than or equal to three"
		(with-in-str (apply str (interleave '(-1 0 1 2 3) (repeat "\n")))
			(should= 3 (binding [println mock-print print mock-print] (get-board-size)))
			)
		)

	(it "get board size gets an number less than or equal to the max board size"
		(with-in-str (apply str (interleave (list 5 6 7 4) (repeat "\n")))
			(should= 4 (binding [println mock-print print mock-print] (get-board-size)))
			)
		)
	)

(run-specs)