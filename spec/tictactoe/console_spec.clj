(ns tictactoe.console-spec
	(:use
    [speclj.core]
		[tictactoe.console]))

(describe "console"

	(it "get int repeats until an integer is given"
		(with-in-str (apply str (interleave '("m" 0) (repeat "\n")))
			(should= 0 (get-int ""))
			)
		)

	(it "get int outputs three error messages"
		(with-in-str (apply str (interleave '("m" "m" "m" 0) (repeat "\n")))
			(should= 3 (count (clojure.string/split (with-out-str (get-int "bad int")) #"\n")))
			)
		)

	(it "all the error messgaes are the same as the given error message"
		(with-in-str (apply str (interleave '("m" "m" "m" 0) (repeat "\n")))
			(should (every? (partial = "bad int") (clojure.string/split (with-out-str (get-int "bad int")) #"\n")))
			)
		)

	(it "get index repeats until an index is given"
		(with-in-str (apply str (interleave '("m" "1 1") (repeat "\n")))
			(should= [0 0] (get-index ""))
			)
		)

	(it "get index won't accept anything except two integers"
		(with-in-str (apply str (interleave '("m" "m m" "m 0" "0 m" "1 1") (repeat "\n")))
			(should= [0 0] (get-index ""))
			)
		)

	(it "get index will accept two integers with variable space in between them"
		(with-in-str (apply str (interleave '("1   1") (repeat "\n")))
			(should= [0 0] (get-index ""))
			)
		)

	(it "get index outputs three error messages"
		(with-in-str (apply str (interleave '("m" "m" "m" "1 1") (repeat "\n")))
			(should= 3 (count (clojure.string/split (with-out-str (get-index "bad index")) #"\n")))
			)
		)

	(it "all the error messgaes are the same as the given error message"
		(with-in-str (apply str (interleave '("m" "m" "m" "1 1") (repeat "\n")))
			(should (every? (partial = "bad index") (clojure.string/split (with-out-str (get-index "bad index")) #"\n")))
			)
		)

	)