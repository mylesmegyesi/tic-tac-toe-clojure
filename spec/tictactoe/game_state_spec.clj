(ns tictactoe.game-state-spec
  (:use
    [speclj.core]
		[tictactoe.constants]
		)
	(:require
	  [tictactoe.game-state :as game-state]
	  [tictactoe.board :as board]
	  )
	)

(def board-with-draw [[P1 P2 P1] [P1 P2 P1] [P2 P1 P2]])

(describe "game-state"

  (it "reports a player 1 win on row one"
    (should= :player1-won (game-state/game-state (board/fill-row (board/create-board 3) 0 P1) false))
		)

	(it "reports a player 1 win on row two"
    (should= :player1-won (game-state/game-state (board/fill-row (board/create-board 3) 1 P1) false))
		)

	(it "reports a player 1 win on row three"
    (should= :player1-won (game-state/game-state (board/fill-row (board/create-board 3) 2 P1) false))
		)

	(it "reports a player 1 win on row four"
    (should= :player1-won (game-state/game-state (board/fill-row (board/create-board 4) 3 P1) false))
		)

	(it "reports a player 1 win on row twenty"
    (should= :player1-won (game-state/game-state (board/fill-row (board/create-board 20) 19 P1) false))
		)

	(it "reports a player 1 win on column one"
    (should= :player1-won (game-state/game-state (board/fill-column (board/create-board 3) 0 P1) false))
		)

	(it "reports a player 1 win on column two"
    (should= :player1-won (game-state/game-state (board/fill-column (board/create-board 3) 1 P1) false))
		)

	(it "reports a player 1 win on column three"
    (should= :player1-won (game-state/game-state (board/fill-column (board/create-board 3) 2 P1) false))
		)

	(it "reports a player 1 win on column four"
    (should= :player1-won (game-state/game-state (board/fill-column (board/create-board 4) 3 P1) false))
		)

	(it "reports a player 1 win on column twenty"
    (should= :player1-won (game-state/game-state (board/fill-column (board/create-board 20) 19 P1) false))
		)

	(it "reports a player 1 win on first diagonal with board size 3"
    (should= :player1-won (game-state/game-state (board/fill-first-diagonal (board/create-board 3) P1) false))
		)

	(it "reports a player 1 win on first diagonal with board size 20"
    (should= :player1-won (game-state/game-state (board/fill-first-diagonal (board/create-board 20) P1) false))
		)

	(it "reports a player 1 win on second diagonal with board size 3"
    (should= :player1-won (game-state/game-state (board/fill-second-diagonal (board/create-board 3) P1) false))
		)

	(it "reports a player 1 win on second diagonal with board size 20"
    (should= :player1-won (game-state/game-state (board/fill-second-diagonal (board/create-board 20) P1) false))
		)

	(it "reports a player 1 win on quadrant 1"
		(should= :player1-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 0] P1) [0 1] P1) [1 0] P1) [1 1] P1) true))
		)

	(it "reports a player 1 win on quadrant 2"
		(should= :player1-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 1] P1) [0 2] P1) [1 1] P1) [1 2] P1) true))
		)

	(it "reports a player 1 win on quadrant 3"
		(should= :player1-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 0] P1) [1 1] P1) [2 0] P1) [2 1] P1) true))
		)

	(it "reports a player 1 win on quadrant 4"
		(should= :player1-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 1] P1) [1 2] P1) [2 1] P1) [2 2] P1) true))
		)

  (it "reports a player 2 win on row one"
    (should= :player2-won (game-state/game-state (board/fill-row (board/create-board 3) 0 P2) false))
		)

	(it "reports a player 2 win on row two"
    (should= :player2-won (game-state/game-state (board/fill-row (board/create-board 3) 1 P2) false))
		)

	(it "reports a player 2 win on row three"
    (should= :player2-won (game-state/game-state (board/fill-row (board/create-board 3) 2 P2) false))
		)

	(it "reports a player 2 win on row four"
    (should= :player2-won (game-state/game-state (board/fill-row (board/create-board 4) 3 P2) false))
		)

	(it "reports a player 2 win on row twenty"
    (should= :player2-won (game-state/game-state (board/fill-row (board/create-board 20) 19 P2) false))
		)

	(it "reports a player 2 win on column one"
    (should= :player2-won (game-state/game-state (board/fill-column (board/create-board 3) 0 P2) false))
		)

	(it "reports a player 2 win on column two"
    (should= :player2-won (game-state/game-state (board/fill-column (board/create-board 3) 1 P2) false))
		)

	(it "reports a player 2 win on column three"
    (should= :player2-won (game-state/game-state (board/fill-column (board/create-board 3) 2 P2) false))
		)

	(it "reports a player 2 win on column four"
    (should= :player2-won (game-state/game-state (board/fill-column (board/create-board 4) 3 P2) false))
		)

	(it "reports a player 2 win on row twenty"
    (should= :player2-won (game-state/game-state (board/fill-column (board/create-board 20) 19 P2) false))
		)

	(it "reports a player 2 win on first diagonal with board size 3"
    (should= :player2-won (game-state/game-state (board/fill-first-diagonal (board/create-board 3) P2) false))
		)

	(it "reports a player 2 win on first diagonal with board size 20"
    (should= :player2-won (game-state/game-state (board/fill-first-diagonal (board/create-board 20) P2) false))
		)

	(it "reports a player 2 win on second diagonal with board size 3"
    (should= :player2-won (game-state/game-state (board/fill-second-diagonal (board/create-board 3) P2) false))
		)

	(it "reports a player 2 win on second diagonal with board size 20"
    (should= :player2-won (game-state/game-state (board/fill-second-diagonal (board/create-board 20) P2) false))
		)

	(it "reports a player 2 win on quadrant 1"
		(should= :player2-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 0] P2) [0 1] P2) [1 0] P2) [1 1] P2) true))
		)

	(it "reports a player 2 win on quadrant 2"
		(should= :player2-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 1] P2) [0 2] P2) [1 1] P2) [1 2] P2) true))
		)

	(it "reports a player 2 win on quadrant 3"
		(should= :player2-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 0] P2) [1 1] P2) [2 0] P2) [2 1] P2) true))
		)

	(it "reports a player 2 win on quadrant 4"
		(should= :player2-won (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [1 1] P2) [1 2] P2) [2 1] P2) [2 2] P2) true))
		)

	(it "reports a draw"
    (should= :draw (game-state/game-state board-with-draw false))
		)

	(it "reports that the game is still in progress"
    (should= :playing (game-state/game-state (assoc-in board-with-draw [0 0] NOONE) false))
		)

	(it "when quadrant checking is off it reports that the game is still in progress with a win in quadrant 1"
		(should= :playing (game-state/game-state (assoc-in (assoc-in (assoc-in (assoc-in (board/create-board 3) [0 0] P1) [0 1] P1) [1 0] P1) [1 1] P1) false))
		)

	)

(run-specs)