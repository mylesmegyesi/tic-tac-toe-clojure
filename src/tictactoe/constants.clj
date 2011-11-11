(ns tictactoe.constants)

(def scores {:draw 0 :win 1000 :lose -1000})

(def players {:p1 "X" , :p2 "O", :none " "})

(def game-states {:playing 0, :player1-won 1, :player2-won 2, :draw 3})