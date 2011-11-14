(ns tictactoe.ui.console.utilities
	(:require
		[clojure.string]
	  )
  )

(defmacro eat-output [& body]
  `(let [s# (new java.io.StringWriter)]
    (binding [*out* s#]
      ~@body
      )
    )
  )

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

(defn- input-is-yes [input]
	(let [lower-input (clojure.string/lower-case input)]
		(or (= lower-input "yes") (= lower-input "y"))
		)
	)

(defn- input-is-no [input]
	(let [lower-input (clojure.string/lower-case input)]
		(or (= lower-input "no") (= lower-input "n"))
		)
	)

(defn get-bool [error-message]
	(loop []
		(let [input (clojure.string/trim (read-line))]
			(if (input-is-yes input)
				true
				(if (input-is-no input)
					false
					(do
						(println error-message)
						(recur)
						)
					)
				)
			)
		)
	)

(defn get-input [prompt get-fn validator error-message]
  (print prompt)
  (flush)
 	(loop [],
		(let [ret (get-fn error-message)]
			(if (validator ret)
				ret
				(do
					(println error-message)
					(recur)
					)
				)
			)
		)
  )