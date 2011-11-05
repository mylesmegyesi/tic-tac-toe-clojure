(ns tictactoe.computer
	(:use
		[tictactoe.board]
		[tictactoe.game-state]
		[tictactoe.players]))

(def scores {:draw 0 :win 1000 :lose -1000})

(declare memoized-negamax)

(defn negamax [board player alpha beta]
	(let [state (game-state board)]
		(if (not= state (game-states :playing))
			(cond
				(= state (game-states :player1-won)) (if (= player (players :p1)) (scores :win) (scores :lose))
				(= state (game-states :player2-won)) (if (= player (players :p2)) (scores :win) (scores :lose))
				:else (scores :draw)
				)
			(let [open-spaces (open-indecies board)]
				(loop [alpha alpha i 0 end (count open-spaces)]
					(if (= i end)
						(+ alpha 1)
						(let [score (* -1 (memoized-negamax (assoc-in board (get open-spaces i) player) (get-other-player player) (* -1 beta) (* -1 alpha)))]
							(let [new-alpha (max score alpha)]
								(if (>= new-alpha beta)
									(+ new-alpha 1)
									(recur new-alpha (inc i) end)
									)
								)
							)
						)
					)
				)
			)
		)
	)

(def memoized-negamax (memoize negamax))

(defn get-computer-move [board player]
	(first
		(reduce
			(fn [x y]
				(if (> (second x) (second y))
					x
					y
					)
				)
			(map-indexed
				(fn [index item]
					[item (* -1 (memoized-negamax (assoc-in board item player) (get-other-player player) (scores :lose) (scores :win)))]
					)
				(open-indecies board))
			)
		)
	)