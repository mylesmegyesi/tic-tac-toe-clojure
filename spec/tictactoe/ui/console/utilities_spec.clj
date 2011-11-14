(ns tictactoe.ui.console.utilities-spec
	(:use
    [speclj.core]
    )
  (:require
    [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.board :as board]
    )
  )

(describe "get-int"

  (it "repeats until an integer is given"
		(with-in-str (apply str (interleave '("m" 0) (repeat "\n")))
			(should= 0 (utilities/eat-output (utilities/get-int "")))
			)
		)

	(it "outputs an error messge each time bad input is given"
		(with-in-str (apply str (interleave '("m" "m" "m" 0) (repeat "\n")))
			(should= 3 (count (clojure.string/split (with-out-str (utilities/get-int "bad int")) #"\n")))
			)
		)

	(it "outputs the same error messge each time bad input is given"
		(with-in-str (apply str (interleave '("m" "m" "m" 0) (repeat "\n")))
			(should (every? (partial = "bad int") (clojure.string/split (with-out-str (utilities/get-int "bad int")) #"\n")))
			)
		)

  )

(describe "get-index"

  (it "repeats until an index is given"
  	(with-in-str (apply str (interleave '("m" "1 1") (repeat "\n")))
  		(should= [0 0] (utilities/eat-output (utilities/get-index "")))
  		)
  	)

  (it "only accepts two integers"
  	(with-in-str (apply str (interleave '("m" "m m" "m 0" "0 m" "1 1") (repeat "\n")))
  		(should= [0 0] (utilities/eat-output (utilities/get-index "")))
  		)
  	)

  (it "accepts two integers with variable space in between them"
  	(with-in-str (apply str (interleave '("1   1") (repeat "\n")))
  		(should= [0 0] (utilities/eat-output (utilities/get-index "")))
  		)
  	)

  (it "outputs an error messge each time bad input is given"
  	(with-in-str (apply str (interleave '("m" "m" "m" "1 1") (repeat "\n")))
  		(should= 3 (count (clojure.string/split (with-out-str (utilities/get-index "bad index")) #"\n")))
  		)
  	)

  (it "outputs the same error messge each time bad input is given"
  	(with-in-str (apply str (interleave '("m" "m" "m" "1 1") (repeat "\n")))
  		(should (every? (partial = "bad index") (clojure.string/split (with-out-str (utilities/get-index "bad index")) #"\n")))
  		)
  	)

  )

(describe "get-bool"

  (it "accepts yes"
		(with-in-str (apply str (interleave '("yes") (repeat "\n")))
			(should (utilities/eat-output (utilities/get-bool "")))
			)
		)

  (it "accepts y"
  	(with-in-str (apply str (interleave '("y") (repeat "\n")))
  		(should (utilities/eat-output (utilities/get-bool "")))
  		)
  	)

	(it "accepts no"
		(with-in-str (apply str (interleave '("no") (repeat "\n")))
			(should-not (utilities/eat-output (utilities/get-bool "")))
			)
		)

	(it "accepts n"
		(with-in-str (apply str (interleave '("n") (repeat "\n")))
			(should-not (utilities/eat-output (utilities/get-bool "")))
			)
		)

  (it "outputs an error messge each time bad input is given"
  	(with-in-str (apply str (interleave '("m" "m" "m" "y") (repeat "\n")))
  		(should= 3 (count (clojure.string/split (with-out-str (utilities/get-bool "bad index")) #"\n")))
  		)
  	)

  (it "outputs the same error messge each time bad input is given"
  	(with-in-str (apply str (interleave '("m" "m" "m" "y") (repeat "\n")))
  		(should (every? (partial = "bad") (clojure.string/split (with-out-str (utilities/get-bool "bad")) #"\n")))
  		)
  	)

  )

(describe "get-input"

  (it "returns when the validation passes"
    (with-in-str (apply str (interleave '("3") (repeat "\n")))
  		(should= 3 (utilities/eat-output (utilities/get-input "" utilities/get-int #(> % 2) "bad")))
  		)
    )

  (it "repeats until the validation passes"
    (with-in-str (apply str (interleave '("1" "2" "3") (repeat "\n")))
  		(should= 3 (utilities/eat-output (utilities/get-input "" utilities/get-int #(> % 2) "bad")))
  		)
    )

  (it "outputs an error messge each time bad input is given"
  	(with-in-str (apply str (interleave '("1" "2" "3" "4") (repeat "\n")))
  		(should= 3 (count (clojure.string/split (with-out-str (utilities/get-input "" utilities/get-int #(> % 3) "bad")) #"\n")))
  		)
  	)

  (it "outputs the same error messge each time bad input is given"
  	(with-in-str (apply str (interleave '("1" "2" "3" "4") (repeat "\n")))
  		(should (every? (partial = "bad") (clojure.string/split (with-out-str (utilities/get-input "" utilities/get-int #(> % 3) "bad")) #"\n")))
  		)
  	)

  )

(run-specs)