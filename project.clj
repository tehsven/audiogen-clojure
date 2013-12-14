(defproject audiogen "0.1.3"
  :description "exploring audio generation with clojure"
  :url "https://github.com/tehsven/audiogen-clojure/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
  				 [overtone "0.9.1"]
  				 [jline "2.11"]]
  :resource-paths ["lib/jsfml.jar"]
  :repl-options {:init (load-file "src/audiogen/repl.clj") })
