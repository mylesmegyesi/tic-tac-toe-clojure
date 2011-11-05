(ns tictactoe.game-spec
  (:use
    [speclj.core]
    [tictactoe.game]
		[tictactoe.board]))

(def test-board nil)

(describe "game"

	(before
		(do
			(binding [test-board (create-board 3)])
			(binding [board test-board])
			)
		)

	)

(run-specs)