(ns audiogen.sysexit
	(:gen-class))

(def system-exit
  (agent false))

(def bindings {
  (char 27) (fn [] (send system-exit (fn [c] true))) ;escape
  })