(ns tictactoe.ui.console.computer-spec
	(:use
    [speclj.core]
    )
  (:require
    [tictactoe.ui.console.utilities :as utilities]
	  [tictactoe.ui.console.computer :as computer]
    )
  )

(it "get computer type repeats until a valid game type is given"
	(with-in-str (apply str (interleave '(0 5 1) (repeat "\n")))
		(should= 1 (binding [println utilities/mock-print print utilities/mock-print] (computer/get-computer-type 0)))
		)
	)

(it "get computer type gets the game type from the user"
	(with-in-str (apply str (interleave '(1) (repeat "\n")))
		(should= 1 (binding [println utilities/mock-print print utilities/mock-print] (computer/get-computer-type 0)))
		)
	)