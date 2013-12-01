(defproject audiogen "0.1.1"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
  				 [overtone "0.9.1"]
  				 [jline "2.11"]]
  :repl-options {:init (load-file "src/audiogen/repl.clj") })
