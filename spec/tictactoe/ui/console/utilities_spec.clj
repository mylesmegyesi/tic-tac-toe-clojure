(ns tictactoe.ui.console.utilities-spec
	(:use
    [speclj.core]
    )
  (:require
    [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.board :as board]
    )
  )

(describe "utilities"

	(it "get int repeats until an integer is given"
		(with-in-str (apply str (interleave '("m" 0) (repeat "\n")))
			(should= 0 (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-int "")))
			)
		)

	(it "get int outputs three error messages"
		(with-in-str (apply str (interleave '("m" "m" "m" 0) (repeat "\n")))
			(should= 3 (count (clojure.string/split (with-out-str (utilities/get-int "bad int")) #"\n")))
			)
		)

	(it "all the error messgaes are the same as the given error message"
		(with-in-str (apply str (interleave '("m" "m" "m" 0) (repeat "\n")))
			(should (every? (partial = "bad int") (clojure.string/split (with-out-str (utilities/get-int "bad int")) #"\n")))
			)
		)

	(it "get index repeats until an index is given"
		(with-in-str (apply str (interleave '("m" "1 1") (repeat "\n")))
			(should= [0 0] (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-index "")))
			)
		)

	(it "get index won't accept anything except two integers"
		(with-in-str (apply str (interleave '("m" "m m" "m 0" "0 m" "1 1") (repeat "\n")))
			(should= [0 0] (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-index "")))
			)
		)

	(it "get index will accept two integers with variable space in between them"
		(with-in-str (apply str (interleave '("1   1") (repeat "\n")))
			(should= [0 0] (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-index "")))
			)
		)

	(it "get index outputs three error messages"
		(with-in-str (apply str (interleave '("m" "m" "m" "1 1") (repeat "\n")))
			(should= 3 (count (clojure.string/split (with-out-str (utilities/get-index "bad index")) #"\n")))
			)
		)

	(it "all the error messgaes are the same as the given error message"
		(with-in-str (apply str (interleave '("m" "m" "m" "1 1") (repeat "\n")))
			(should (every? (partial = "bad index") (clojure.string/split (with-out-str (utilities/get-index "bad index")) #"\n")))
			)
		)

	(it "get bool loops until yes is given"
		(with-in-str (apply str (interleave '("m" "yes") (repeat "\n")))
			(should (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-bool "")))
			)
		)

	(it "get bool outputs three error messages"
		(with-in-str (apply str (interleave '("m" "m" "m" "yes") (repeat "\n")))
			(should= 3 (count (clojure.string/split (with-out-str (utilities/get-bool "bad int")) #"\n")))
			)
		)

	(it "get bool loops until no is given"
		(with-in-str (apply str (interleave '("m m m" "no") (repeat "\n")))
			(should-not (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-bool "")))
			)
		)

	(it "get bool loops until n is given"
		(with-in-str (apply str (interleave '("m m m" "n") (repeat "\n")))
			(should-not (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-bool "")))
			)
		)

	(it "get bool loops until y is given"
		(with-in-str (apply str (interleave '("m m m" "y") (repeat "\n")))
			(should (binding [println utilities/mock-print print utilities/mock-print] (utilities/get-bool "")))
			)
		)

	)