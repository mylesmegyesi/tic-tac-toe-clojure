(ns tictactoe.board
	(:use
		[tictactoe.players]))

	(defn invert [board]
		(loop [board board acc []]
			(if (= (count board) (count acc))
				acc
				(recur (vec (map rest board)) (conj acc (vec (map first board))))
				)
			)
		)

	(defn create-board [size]
		(vec (repeat size (vec (repeat size (players :none)))))
		)

	(defn fill-row [board row item]
		(let [size (count board)]
			(into (into (subvec board 0 row) [(vec (repeat size item))]) (subvec board (+ row 1) size))
			)
		)

	(defn fill-column [board column item]
		(invert (fill-row (invert board) column item))
		)

	(defn fill-first-diagonal [board item]
		(loop [board board i 0 end (count board)]
			(if (= i end)
				board
				(recur (assoc-in board [i i] item) (inc i) end)
				)
			)
		)

	(defn fill-second-diagonal [board item]
		(loop [board board i 0 end (count board)]
			(if (= i end)
				board
				(recur (assoc-in board [i (- (- end 1) i)] item) (inc i) end)
				)
			)
		)