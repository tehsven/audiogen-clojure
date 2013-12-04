(ns audiogen.sysexit
  (:gen-class))

(def bindings {
	(char 27) (fn [] :quit) ;escape
	})