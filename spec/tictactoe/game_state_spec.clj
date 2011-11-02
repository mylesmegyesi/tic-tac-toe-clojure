(ns tictactoe.game-state-spec
  (:use
    [speclj.core]
    [tictactoe.game-state]
		[tictactoe.players]))

(defn create-board [size]
	(vec (repeat (* size size) (players :none)))
	)

(defn fill-row [board row item]
	(let [board-size (Math/sqrt (count board))]
		(let [row-size (Math/sqrt board-size)]
			(let [first-part (subvec board 0 (* row row-size))]
				(let [middle-part (vec (repeat row-size item))]
					(let [last-part (subvec board (* (+ row 1) row-size) board-size)]
						(conj first-part middle-part last-part)
						)
					)
				)
			)
		)
	)

(describe "Game State"

  (it "should report a player 1 win on row one"
    (should= (game-states :player1-won)
			(do
				(println (create-board 3))
				(println (fill-row (create-board 3) 1 (players :p1)))
				(game-state (fill-row (create-board 3) 0 (players :p1)))
				)
			)
		)

	(it "should report a player 1 win on row two"
    (should= (game-states :player1-won) (game-state
			[(players :none), (players :none), (players :none),
			(players :p1), (players :p1), (players :p1),
			(players :none), (players :none), (players :none)])))

	(it "should report a player 1 win on row three"
    (should= (game-states :player1-won) (game-state
			[(players :none), (players :none), (players :none),
			(players :none), (players :none), (players :none),
			(players :p1), (players :p1), (players :p1)])))

	(it "should report a player 1 win on row four"
    (should= (game-states :player1-won) (game-state
			[(players :none), (players :none), (players :none), (players :none),
			(players :none), (players :none), (players :none), (players :none),
			(players :none), (players :none), (players :none), (players :none),
			(players :p1), (players :p1), (players :p1), (players :p1)])))

	)
(run-specs)