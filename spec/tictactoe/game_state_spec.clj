(ns tictactoe.game-state-spec
  (:use
    [speclj.core]
		[tictactoe.constants :only (game-states scores players)]
		)
	(:require
	  [tictactoe.game-state :as game-state]
	  [tictactoe.board :as board]
	  )
	)

(def board-with-draw [[(players :p1) (players :p2) (players :p1)] [(players :p1) (players :p2) (players :p1)] [(players :p2) (players :p1) (players :p2)]])

(describe "game-state"

  (it "should report a player 1 win on row one"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-row (board/create-board 3) 0 (players :p1)) false))
		)

	(it "should report a player 1 win on row two"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-row (board/create-board 3) 1 (players :p1)) false))
		)

	(it "should report a player 1 win on row three"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-row (board/create-board 3) 2 (players :p1)) false))
		)

	(it "should report a player 1 win on row four"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-row (board/create-board 4) 3 (players :p1)) false))
		)

	(it "should report a player 1 win on row twenty"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-row (board/create-board 20) 19 (players :p1)) false))
		)

	(it "should report a player 1 win on column one"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-column (board/create-board 3) 0 (players :p1)) false))
		)

	(it "should report a player 1 win on column two"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-column (board/create-board 3) 1 (players :p1)) false))
		)

	(it "should report a player 1 win on column three"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-column (board/create-board 3) 2 (players :p1)) false))
		)

	(it "should report a player 1 win on column four"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-column (board/create-board 4) 3 (players :p1)) false))
		)

	(it "should report a player 1 win on column twenty"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-column (board/create-board 20) 19 (players :p1)) false))
		)

	(it "reports a player 1 win on first diagonal with board size 3"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-first-diagonal (board/create-board 3) (players :p1)) false))
		)

	(it "reports a player 1 win on first diagonal with board size 20"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-first-diagonal (board/create-board 20) (players :p1)) false))
		)

	(it "reports a player 1 win on second diagonal with board size 3"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-second-diagonal (board/create-board 3) (players :p1)) false))
		)

	(it "reports a player 1 win on second diagonal with board size 20"
    (should= (game-states :player1-won) (game-state/game-state (board/fill-second-diagonal (board/create-board 20) (players :p1)) false))
		)

	(it "reports a player 1 win on quadrant 1"
		(should= (game-states :player1-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 0] (players :p1)) [0 1] (players :p1)) [1 0] (players :p1)) [1 1] (players :p1)) true))
		)

	(it "reports a player 1 win on quadrant 2"
		(should= (game-states :player1-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 1] (players :p1)) [0 2] (players :p1)) [1 1] (players :p1)) [1 2] (players :p1)) true))
		)

	(it "reports a player 1 win on quadrant 3"
		(should= (game-states :player1-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 0] (players :p1)) [1 1] (players :p1)) [2 0] (players :p1)) [2 1] (players :p1)) true))
		)

	(it "reports a player 1 win on quadrant 4"
		(should= (game-states :player1-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 1] (players :p1)) [1 2] (players :p1)) [2 1] (players :p1)) [2 2] (players :p1)) true))
		)

  (it "should report a player 2 win on row one"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-row (board/create-board 3) 0 (players :p2)) false))
		)

	(it "should report a player 2 win on row two"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-row (board/create-board 3) 1 (players :p2)) false))
		)

	(it "should report a player 2 win on row three"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-row (board/create-board 3) 2 (players :p2)) false))
		)

	(it "should report a player 2 win on row four"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-row (board/create-board 4) 3 (players :p2)) false))
		)

	(it "should report a player 2 win on row twenty"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-row (board/create-board 20) 19 (players :p2)) false))
		)

	(it "should report a player 2 win on column one"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-column (board/create-board 3) 0 (players :p2)) false))
		)

	(it "should report a player 2 win on column two"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-column (board/create-board 3) 1 (players :p2)) false))
		)

	(it "should report a player 2 win on column three"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-column (board/create-board 3) 2 (players :p2)) false))
		)

	(it "should report a player 2 win on column four"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-column (board/create-board 4) 3 (players :p2)) false))
		)

	(it "should report a player 2 win on row twenty"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-column (board/create-board 20) 19 (players :p2)) false))
		)

	(it "reports a player 2 win on first diagonal with board size 3"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-first-diagonal (board/create-board 3) (players :p2)) false))
		)

	(it "reports a player 2 win on first diagonal with board size 20"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-first-diagonal (board/create-board 20) (players :p2)) false))
		)

	(it "reports a player 2 win on second diagonal with board size 3"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-second-diagonal (board/create-board 3) (players :p2)) false))
		)

	(it "reports a player 2 win on second diagonal with board size 20"
    (should= (game-states :player2-won) (game-state/game-state (board/fill-second-diagonal (board/create-board 20) (players :p2)) false))
		)

	(it "reports a player 2 win on quadrant 1"
		(should= (game-states :player2-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 0] (players :p2)) [0 1] (players :p2)) [1 0] (players :p2)) [1 1] (players :p2)) true))
		)

	(it "reports a player 2 win on quadrant 2"
		(should= (game-states :player2-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 1] (players :p2)) [0 2] (players :p2)) [1 1] (players :p2)) [1 2] (players :p2)) true))
		)

	(it "reports a player 2 win on quadrant 3"
		(should= (game-states :player2-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 0] (players :p2)) [1 1] (players :p2)) [2 0] (players :p2)) [2 1] (players :p2)) true))
		)

	(it "reports a player 2 win on quadrant 4"
		(should= (game-states :player2-won) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 1] (players :p2)) [1 2] (players :p2)) [2 1] (players :p2)) [2 2] (players :p2)) true))
		)

	(it "reports a draw"
    (should= (game-states :draw) (game-state/game-state board-with-draw false))
		)

	(it "reports that the game is still in progress"
    (should= (game-states :playing) (game-state/game-state (assoc-in board-with-draw [0 0] (players :none)) false))
		)

	(it "reports that the game is still in progress with a win in quadrant 1"
		(should= (game-states :playing) (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 0] (players :p1)) [0 1] (players :p1)) [1 0] (players :p1)) [1 1] (players :p1)) false))
		)

	)

(run-specs)