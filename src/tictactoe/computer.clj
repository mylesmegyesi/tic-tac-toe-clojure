(ns tictactoe.computer
	(:use
		[tictactoe.board]
		[tictactoe.game-state]
		[tictactoe.players]
		[tictactoe.console]))

(def scores {:draw 0 :win 1000 :lose -1000})

(defrecord BoardTree [board parent children alpha beta])

(defn- get-state-score [player game-state]
	(cond
		(= game-state (game-states :player1-won)) (if (= player (players :p1)) (scores :win) (scores :lose))
		(= game-state (game-states :player2-won)) (if (= player (players :p2)) (scores :win) (scores :lose))
		:else (scores :draw)
		)
	)

(defn- fluxuating-max [score alpha]
  (if (not= 5 (int (* 5 (Math/random))))
    (max score alpha)
    (min score alpha)
    )
  )

(defn- forward-stupid [player board game-state-fn]
	(let [state (game-state-fn board)]
		(if (not= state (game-states :playing))
			(* -1 (get-state-score player state))
			(loop [alpha (scores :lose) open-spaces (open-indecies board)]
				(if (empty? open-spaces)
					(* -1 alpha)
					(let [score (forward-stupid (get-other-player player) (assoc-in board (first open-spaces) player) game-state-fn)]
						(recur (fluxuating-max score alpha) (rest open-spaces))))))))

(declare forward)
(defn- forward-build [player board game-state-fn]
	(let [state (game-state-fn board)]
		(if (not= state (game-states :playing))
			(* -1 (get-state-score player state))
			(loop [alpha (scores :lose) open-spaces (open-indecies board)]
				(if (empty? open-spaces)
					(* -1 alpha)
					(let [score (forward (get-other-player player) (assoc-in board (first open-spaces) player) game-state-fn)]
						(recur (max score alpha) (rest open-spaces))))))))
(def forward (memoize forward-build))

(declare forward-with-depth-heuristic-and-pruning)
(defn- forward-with-depth-heuristic-and-pruning-build [player board alpha beta game-state-fn depth]
	(let [state (game-state-fn board)]
		(if (not= state (game-states :playing))
			(* -1 (+ (get-state-score player state) depth))
			(loop [alpha alpha open-spaces (open-indecies board)]
				(if (or (empty? open-spaces) (>= alpha beta))
					(* -1 alpha)
					(let [score (forward-with-depth-heuristic-and-pruning (get-other-player player) (assoc-in board (first open-spaces) player) (* -1 beta) (* -1 alpha) game-state-fn (inc depth))]
						(recur (max score alpha) (rest open-spaces))
						)
					)
				)
			)
		)
	)
(def forward-with-depth-heuristic-and-pruning (memoize forward-with-depth-heuristic-and-pruning-build))

(defn- get-parent-with-max [node heuristic]
	(if (> heuristic (-> node :parent :alpha))
		(:parent (assoc-in node [:parent :alpha] heuristic))
		(:parent node)
		)
	)

(defn- tail [player current-node game-state-fn]
	(if (and (= nil (:parent current-node)) (= 0 (count (:children current-node))))
		;base case
		(* -1 (:alpha current-node))
		(let [state (game-state-fn (:board current-node))]
			;determine if this is a leaf node
			(if (not= state (game-states :playing))
				;leaf node
				(if (= nil (:parent current-node)) ;it's a leaf and a root
					(* -1 (get-state-score player state))
					(recur (get-other-player player) (get-parent-with-max current-node (* -1 (get-state-score player state))) game-state-fn)
					)
				(if (empty? (:children current-node))
					;return from child to the parent
					(recur (get-other-player player) (get-parent-with-max current-node (* -1 (:alpha current-node))) game-state-fn)
					;create a new child board and take the child out of the parent's list of children (a.k.a. marking it as visited)
					(let [new-board (assoc-in (:board current-node) (first (:children current-node)) player) old-board (update-in current-node [:children] rest)]
						(recur (get-other-player player) (BoardTree. new-board old-board (open-indecies new-board) Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY) game-state-fn)
						)
					)
				)
			)
		)
	)

(defn- tail-with-depth-heuristic [player current-node game-state-fn depth]
	(if (and (= nil (:parent current-node)) (= 0 (count (:children current-node))))
		;base case
		(* -1 (+ (:alpha current-node) depth))
		(let [state (game-state-fn (:board current-node))]
			;determine if this is a leaf node
			(if (not= state (game-states :playing))
				;leaf node
				(if (= nil (:parent current-node)) ;it's a leaf and a root
					(* -1 (+ (get-state-score player state) depth))
					(recur (get-other-player player) (get-parent-with-max current-node (* -1 (+ (get-state-score player state) depth))) game-state-fn (dec depth))
					)
				(if (empty? (:children current-node))
					;return from child to the parent
					(recur (get-other-player player) (get-parent-with-max current-node (* -1 (:alpha current-node))) game-state-fn (dec depth))
					;create a new child board and take the child out of the parent's list of children (a.k.a. marking it as visited)
					(let [new-board (assoc-in (:board current-node) (first (:children current-node)) player) old-board (update-in current-node [:children] rest)]
						(recur (get-other-player player) (BoardTree. new-board old-board (open-indecies new-board) Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY) game-state-fn (inc depth))
						)
					)
				)
			)
		)
	)

(defn- tail-with-depth-heuristic-and-pruning [player current-node game-state-fn depth]
	(if (and (= nil (:parent current-node)) (= 0 (count (:children current-node))))
		;base case
		(* -1 (+ (:alpha current-node) depth))
		(let [state (game-state-fn (:board current-node))]
			;determine if this is a leaf node
			(if (not= state (game-states :playing))
				;leaf node
				(if (= nil (:parent current-node)) ;it's a leaf and a root
					(* -1 (+ (get-state-score player state) depth))
					;prune the tree
					(let [parent (get-parent-with-max current-node (* -1 (+ (get-state-score player state) depth)))]
						(if (>= (:alpha parent) (:beta parent))
							; prune the children from the parent if alpha is greater than beta
							(recur (get-other-player player) (assoc-in parent [:children] (empty (:children parent))) game-state-fn (dec depth))
							(recur (get-other-player player) parent game-state-fn (dec depth))
							)
						)
					)
				(if (empty? (:children current-node))
					;return from child to the parent
					(recur (get-other-player player) (get-parent-with-max current-node (* -1 (:alpha current-node))) game-state-fn (dec depth))
					;create a new child board and take the child out of the parent's list of children (a.k.a. marking it as visited)
					(let [new-board (assoc-in (:board current-node) (first (:children current-node)) player) old-board (update-in current-node [:children] rest)]
						(recur (get-other-player player) (BoardTree. new-board old-board (open-indecies new-board) (* -1 (:beta old-board)) (* -1 (:alpha old-board))) game-state-fn (inc depth))
						)
					)
				)
			)
		)
	)

(defn get-computer-move-forward-stupid [game-state-fn player board]
	(first
		(reduce
			(fn [x y]
				(if (> (second x) (second y)) x y )
				)
			(map
				(fn [index]
					(let [new-board (assoc-in board index player)]
						[index (forward-stupid (get-other-player player) new-board game-state-fn)]
						)
					)
				(open-indecies board))
			)
		)
	)

(defn get-computer-move-forward-smart [game-state-fn player board]
	(first
		(reduce
			(fn [x y]
				(if (> (second x) (second y)) x y )
				)
			(map
				(fn [index]
					(let [new-board (assoc-in board index player)]
						[index (forward (get-other-player player) new-board game-state-fn)]
						)
					)
				(open-indecies board))
			)
		)
	)

(defn get-computer-move-forward-smartest [game-state-fn player board]
	(first
		(reduce
			(fn [x y]
				(if (> (second x) (second y)) x y)
				)
			(map
				(fn [index]
					(let [new-board (assoc-in board index player)]
						[index (forward-with-depth-heuristic-and-pruning (get-other-player player) new-board Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY game-state-fn 0)]
						)
					)
				(open-indecies board))
			)
		)
	)

(defn get-computer-move-tail [game-state-fn player board]
	(first
		(reduce
			(fn [x y]
				(if (> (second x) (second y)) x y )
				)
			(map
				(fn [index]
					(let [new-board (assoc-in board index player)]
						[index (tail (get-other-player player) (BoardTree. new-board nil (open-indecies new-board) Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY) game-state-fn)]
						)
					)
				(open-indecies board))
			)
		)
	)

(defn get-computer-move-tail-depth [game-state-fn player board]
	(first
		(reduce
			(fn [x y]
				(if (> (second x) (second y)) x y )
				)
			(map
				(fn [index]
					(let [new-board (assoc-in board index player)]
						[index (tail-with-depth-heuristic (get-other-player player) (BoardTree. new-board nil (open-indecies new-board) Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY) game-state-fn 0)]
						)
					)
				(open-indecies board))
			)
		)
	)

(defn get-computer-move-tail-depth-pruning [game-state-fn player board]
	(first
		(reduce
			(fn [x y]
				(if (> (second x) (second y)) x y )
				)
			(map
				(fn [index]
					(let [new-board (assoc-in board index player)]
						[index (tail-with-depth-heuristic-and-pruning (get-other-player player) (BoardTree. new-board nil (open-indecies new-board) Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY) game-state-fn 0)]
						)
					)
				(open-indecies board))
			)
		)
	)

(declare get-computer-move)
(defn get-computer-move-build [game-state-fn player board]
	(get-computer-move-forward-smartest game-state-fn player board)
	)
(def get-computer-move (memoize get-computer-move-build))

(def computer-types
	{1 {:fn get-computer-move-forward-stupid :name "Not so smart"}
	  2 {:fn get-computer-move-forward-smart :name "Smarter"}
    3 {:fn get-computer-move-forward-smartest :name "Smartest"}
    }
	)

(defn- display-computer-types []
	(println "These are the available computer types,")
	(doseq [keyval computer-types]
		(println (str (key keyval) ". " ((val keyval) :name)))
		)
	)

(defn get-computer-type [player]
  (display-computer-types)
	(print "Please enter the level you would like the" (get-player-name player) "computer to play at: ")
	(flush)
	(let [error-message "That is not a valid computer type, please try again."]
	 	(loop []
			(let [computer-type (get-int error-message)]
				(if (contains? computer-types computer-type)
				  computer-type
					(do
						(println error-message)
						(recur)
						)
					)
				)
			)
		)
  )

(defn get-computer-mover [game-state-fn player]
  (partial ((computer-types (get-computer-type player)) :fn) game-state-fn)
	)
