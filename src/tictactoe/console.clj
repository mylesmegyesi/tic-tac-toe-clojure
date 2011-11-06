(ns tictactoe.console
	(:require
		[clojure.string]))

(defn mock-print [& args])

(defn get-int [error-message]
	(loop []
		(let [input (try (Integer/parseInt (read-line)) (catch NumberFormatException e nil))]
			(if (not= nil input)
				input
				(do
					(println error-message)
					(recur)
					)
				)
			)
		)
	)

(defn get-index [error-message]
	(loop []
		(let [input (filter (partial not= "") (clojure.string/split (clojure.string/trim (read-line)) #" "))]
			(let [row (try (Integer/parseInt (first input)) (catch Exception e nil))
				col (try (Integer/parseInt (second input)) (catch Exception e nil))]
				(if (and (not= nil row) (not= nil col))
					[(dec row) (dec col)]
					(do
						(println error-message)
						(recur)
						)
					)
				)
			)
		)
	)