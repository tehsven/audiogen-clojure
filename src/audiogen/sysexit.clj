(ns audiogen.sysexit
  	(:gen-class))

(def system-exit
	(agent false))

(def bindings {
	(char 27) (fn [] (println "sending") (send system-exit (fn [c n] n) true)) ;escape
	})