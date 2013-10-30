(ns tic-tac-toe.players
  (:require [tic-tac-toe.constants :refer :all]))

(defn get-other-player [player]
	(if (= player P1)
		P2
		(if (= player P2)
			P1
			NOONE)))

(defn get-player-name [player]
	(if (= player P1)
		"Player 1"
		"Player 2"))
