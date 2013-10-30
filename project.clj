(defproject org.clojars.mylesmegyesi/tic-tac-toe "0.1-SNAPSHOT"
  :description "Tic-Tac-Toe in Clojure"
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[speclj "2.7.5"]]
                   :test-paths ["spec"]
                   :plugins [[speclj "2.7.5"]]}}
  )
