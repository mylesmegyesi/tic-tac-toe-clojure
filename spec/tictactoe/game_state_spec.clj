(ns tictactoe.game-state-spec
  (:use
    [speclj.core]
		[tictactoe.board]
    [tictactoe.game-state]
		[tictactoe.players]))

(def board-with-draw [[(players :p1) (players :p2) (players :p1)] [(players :p1) (players :p2) (players :p1)] [(players :p2) (players :p1) (players :p2)]])

(describe "game-state"

  (it "should report a player 1 win on row one"
    (should= (game-states :player1-won) (game-state (fill-row (create-board 3) 0 (players :p1))))
		)

	(it "should report a player 1 win on row two"
    (should= (game-states :player1-won) (game-state (fill-row (create-board 3) 1 (players :p1))))
		)

	(it "should report a player 1 win on row three"
    (should= (game-states :player1-won) (game-state (fill-row (create-board 3) 2 (players :p1))))
		)

	(it "should report a player 1 win on row four"
    (should= (game-states :player1-won) (game-state (fill-row (create-board 4) 3 (players :p1))))
		)

	(it "should report a player 1 win on row twenty"
    (should= (game-states :player1-won) (game-state (fill-row (create-board 20) 19 (players :p1))))
		)

	(it "should report a player 1 win on column one"
    (should= (game-states :player1-won) (game-state (fill-column (create-board 3) 0 (players :p1))))
		)

	(it "should report a player 1 win on column two"
    (should= (game-states :player1-won) (game-state (fill-column (create-board 3) 1 (players :p1))))
		)

	(it "should report a player 1 win on column three"
    (should= (game-states :player1-won) (game-state (fill-column (create-board 3) 2 (players :p1))))
		)

	(it "should report a player 1 win on column four"
    (should= (game-states :player1-won) (game-state (fill-column (create-board 4) 3 (players :p1))))
		)

	(it "should report a player 1 win on column twenty"
    (should= (game-states :player1-won) (game-state (fill-column (create-board 20) 19 (players :p1))))
		)

	(it "reports a player 1 win on first diagonal with board size 3"
    (should= (game-states :player1-won) (game-state (fill-first-diagonal (create-board 3) (players :p1))))
		)

	(it "reports a player 1 win on first diagonal with board size 20"
    (should= (game-states :player1-won) (game-state (fill-first-diagonal (create-board 20) (players :p1))))
		)

	(it "reports a player 1 win on second diagonal with board size 3"
    (should= (game-states :player1-won) (game-state (fill-second-diagonal (create-board 3) (players :p1))))
		)

	(it "reports a player 1 win on second diagonal with board size 20"
    (should= (game-states :player1-won) (game-state (fill-second-diagonal (create-board 20) (players :p1))))
		)

  (it "should report a player 2 win on row one"
    (should= (game-states :player2-won) (game-state (fill-row (create-board 3) 0 (players :p2))))
		)

	(it "should report a player 2 win on row two"
    (should= (game-states :player2-won) (game-state (fill-row (create-board 3) 1 (players :p2))))
		)

	(it "should report a player 2 win on row three"
    (should= (game-states :player2-won) (game-state (fill-row (create-board 3) 2 (players :p2))))
		)

	(it "should report a player 2 win on row four"
    (should= (game-states :player2-won) (game-state (fill-row (create-board 4) 3 (players :p2))))
		)

	(it "should report a player 2 win on row twenty"
    (should= (game-states :player2-won) (game-state (fill-row (create-board 20) 19 (players :p2))))
		)

	(it "should report a player 2 win on column one"
    (should= (game-states :player2-won) (game-state (fill-column (create-board 3) 0 (players :p2))))
		)

	(it "should report a player 2 win on column two"
    (should= (game-states :player2-won) (game-state (fill-column (create-board 3) 1 (players :p2))))
		)

	(it "should report a player 2 win on column three"
    (should= (game-states :player2-won) (game-state (fill-column (create-board 3) 2 (players :p2))))
		)

	(it "should report a player 2 win on column four"
    (should= (game-states :player2-won) (game-state (fill-column (create-board 4) 3 (players :p2))))
		)

	(it "should report a player 2 win on row twenty"
    (should= (game-states :player2-won) (game-state (fill-column (create-board 20) 19 (players :p2))))
		)

	(it "reports a player 2 win on first diagonal with board size 3"
    (should= (game-states :player2-won) (game-state (fill-first-diagonal (create-board 3) (players :p2))))
		)

	(it "reports a player 2 win on first diagonal with board size 20"
    (should= (game-states :player2-won) (game-state (fill-first-diagonal (create-board 20) (players :p2))))
		)

	(it "reports a player 2 win on second diagonal with board size 3"
    (should= (game-states :player2-won) (game-state (fill-second-diagonal (create-board 3) (players :p2))))
		)

	(it "reports a player 2 win on second diagonal with board size 20"
    (should= (game-states :player2-won) (game-state (fill-second-diagonal (create-board 20) (players :p2))))
		)

	(it "reports a draw"
    (should= (game-states :draw) (game-state board-with-draw))
		)

	(it "reports that the game is still in progress"
    (should= (game-states :playing) (game-state (assoc-in board-with-draw [0 0] (players :none))))
		)

	)

(run-specs)