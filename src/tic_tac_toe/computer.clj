(ns tic-tac-toe.computer
  (:require [tic-tac-toe.constants :refer :all]
            [tic-tac-toe.players   :as players]
            [tic-tac-toe.board     :as board]))

(defn- get-state-score [player game-state]
  (cond
    (= game-state :player1-won) (if (= player P1) WIN LOSE)
    (= game-state :player2-won) (if (= player P2) WIN LOSE)
    :else DRAW))

(defn- forward [player board alpha beta game-state-fn max-depth depth]
  (let [state (game-state-fn board)]
    (if (not= state :playing)
      (* -1 (+ (get-state-score player state) depth))
      (if (>= depth max-depth)
        DRAW
        (loop [alpha alpha open-spaces (board/open-indecies board)]
          (if (or (empty? open-spaces) (>= alpha beta))
            (* -1 alpha)
            (let [score (forward (players/get-other-player player) (assoc-in board (first open-spaces) player) (* -1 beta) (* -1 alpha) game-state-fn max-depth (inc depth))]
              (recur (max score alpha) (rest open-spaces)))))))))

(defn get-computer-move-forward [max-depth game-state-fn player board]
  (first
    (reduce
      (fn [x y]
        (if (> (second x) (second y)) x y))
      (map
        (fn [index]
          (let [new-board (assoc-in board index player)]
            [index (forward (players/get-other-player player) new-board Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY game-state-fn max-depth 0)]))
        (board/open-indecies board)))))

(def computer-types
  {1 {:fn (partial get-computer-move-forward 2) :name "Not so smart (fastest move time)"}
   2 {:fn (partial get-computer-move-forward 4) :name "Smarter (slower move time)"}
   3 {:fn (partial get-computer-move-forward 7) :name "Smartest (slowest move time)"}})

; left over tail recursive functions
(comment

  (defrecord BoardTree [board parent children alpha beta])

  (defn- get-parent-with-max [node heuristic]
    (if (> heuristic (-> node :parent :alpha))
      (:parent (assoc-in node [:parent :alpha] heuristic))
      (:parent node)))

  (defn- tail [player current-node game-state-fn max-depth depth]
    (if (and (= nil (:parent current-node)) (= 0 (count (:children current-node))))
      ;base case
      (* -1 (+ (:alpha current-node) depth))
      (let [state (game-state-fn (:board current-node))]
        ;determine if this is a leaf node
        (if (not= state :playing)
          ;leaf node
          (if (= nil (:parent current-node)) ;it's a leaf and a root
            (* -1 (+ (get-state-score player state) depth))
            ;prune the tree
            (let [parent (get-parent-with-max current-node (* -1 (+ (get-state-score player state) depth)))]
              (if (>= (:alpha parent) (:beta parent))
                ; prune the children from the parent if alpha is greater than beta
                (recur (players/get-other-player player) (assoc-in parent [:children] (empty (:children parent))) game-state-fn max-depth (dec depth))
                (recur (players/get-other-player player) parent game-state-fn max-depth (dec depth)))))
          (if (empty? (:children current-node))
            ;return from child to the parent
            (recur (players/get-other-player player) (get-parent-with-max current-node (* -1 (:alpha current-node))) game-state-fn max-depth (dec depth))
            (if (>= depth max-depth)
              ;return from child to the parent
              (recur (players/get-other-player player) (get-parent-with-max current-node 0) game-state-fn max-depth (dec depth))
              ;create a new child board and take the child out of the parent's list of children (a.k.a. marking it as visited)
              (let [new-board (assoc-in (:board current-node) (first (:children current-node)) player) old-board (update-in current-node [:children] rest)]
                (recur (players/get-other-player player) (BoardTree. new-board old-board (board/open-indecies new-board) (* -1 (:beta old-board)) (* -1 (:alpha old-board))) game-state-fn max-depth (inc depth)))))))))

  (defn get-computer-move-tail [max-depth game-state-fn player board]
    (first
      (reduce
        (fn [x y]
          (if (> (second x) (second y)) x y )
          )
        (map
          (fn [index]
            (let [new-board (assoc-in board index player)]
              [index (tail (players/get-other-player player) (BoardTree. new-board nil (board/open-indecies new-board) Float/NEGATIVE_INFINITY Float/POSITIVE_INFINITY) game-state-fn max-depth 0)]))
          (board/open-indecies board)))))
  )
