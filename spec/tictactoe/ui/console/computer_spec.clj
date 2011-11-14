(ns tictactoe.ui.console.computer-spec
	(:use
    [speclj.core]
    )
  (:require
    [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.computer :as computer]
    )
  )

(describe "get-computer-type"

  (it "repeats until a valid computer type is given"
  	(with-in-str (apply str (interleave '(0 4 5 1) (repeat "\n")))
  		(should= 1 (utilities/eat-output (computer/get-computer-type 0)))
  		)
  	)

  (it "gets the computer type from the user"
  	(with-in-str (apply str (interleave '(1) (repeat "\n")))
  		(should= 1 (utilities/eat-output (computer/get-computer-type 0)))
  		)
  	)

  )

(run-specs)