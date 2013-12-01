(ns audiogen.keydispatch
  (:gen-class))

(def all-bindings (agent {}))

(defn add-bindings
	"adds a map of keys"
	[new-bindings]
	(send all-bindings (fn [c n] (merge c n)) new-bindings))

(defn key-dispatch
	"dispatches a keyboard keypress"
	[keypress]
	(if (contains? @all-bindings keypress)
		((@all-bindings keypress)) ;evaluate
		(println keypress)))

(defonce sys-bindings {
	27 (fn [] :quit) ;escape to quit
	})

(add-bindings sys-bindings)