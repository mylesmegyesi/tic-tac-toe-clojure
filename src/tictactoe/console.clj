(ns tictactoe.console)

(defn get-int [error-message]
	(let [input (try (Integer/parseInt (read-line)) (catch NumberFormatException e nil))]
		(if (not= nil input)
			input
			(do
				(println error-message)
				(recur error-message)
				)
			)
		)
	)

(defn get-index [error-message]
	(let [input (filter (partial not= "") (clojure.string/split (clojure.string/trim (read-line)) #" "))]
		(if (< 2 (count input))
			(do
				(println error-message)
				(recur error-message)
				)
			(let [row (try (Integer/parseInt (first input)) (catch NumberFormatException e nil))
				col (try (Integer/parseInt (second input)) (catch NumberFormatException e nil))]
				(if (or (= nil row) (= nil col))
					(do
						(println error-message)
						(recur error-message)
						)
					[(dec row) (dec col)]
					)
				)
			)
		)
	)