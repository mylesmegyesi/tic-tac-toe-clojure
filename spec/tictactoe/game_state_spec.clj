(ns tictactoe.game-state-spec
  (:use
    [speclj.core]
    [tictactoe.game-state]
		[tictactoe.players]))

(describe "Game State"

  (it "should report a player 1 win"
    (should= (game-states :player1-won) (game-state
			[(players :p1), (players :p1), (players :p1),
			(players :none), (players :none), (players :none),
			(players :none), (players :none), (players :none)])))

(run-specs)