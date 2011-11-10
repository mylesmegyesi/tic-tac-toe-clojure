(ns tictactoe.board
	(:use
		[tictactoe.players]
		[tictactoe.console]))

(def valid-board-sizes [3 4])

(defn- row-str [row]
	(str (reduce #(str %1 " " %2) row) "\n" (reduce #(str %1 " " %2) (repeat (count row) "-")) "\n")
	)

(defn- board-str [board]
	(reduce str (map row-str board))
	)

(defn print-board [board]
	(println (board-str board))
	)

(defn invert [board]
	(loop [board board acc []]
		(if (= (count board) (count acc))
			acc
			(recur (vec (map rest board)) (conj acc (vec (map first board))))
			)
		)
	)

(defn row-size [board]
	(count (get board 0))
	)

(defn open-indecies [board]
	(let [size (row-size board)]
		(vec
			(for [i (range size) j (range size) :when (= (players :none) (get (get board i) j))]
				[i j]
				)
			)
		)
	)

(defn valid-move [board index]
	(not= nil (some (partial = index) (open-indecies board)))
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

(defn get-board-size []
	(print (str "Please enter the size of board you would like to play on (" valid-board-sizes "): "))
	(flush)
	(let [error-message "That is not a valid board size, please try again."]
	 	(loop [],
			(let [board-size (get-int error-message)]
				(if (some (partial = board-size) valid-board-sizes)
					board-size
					(do
						(println error-message)
						(recur)
						)
					)
				)
			)
		)
	)

(defn get-board []
	(create-board (get-board-size))
	)