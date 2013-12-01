(ns audiogen.keydispatch
  (:gen-class))

(def all-bindings (agent {}))

(defn add-bindings
	"adds a map of keys"
	[new-bindings]
	(send all-bindings (fn [c n] (merge c n)) new-bindings))

(defn key-dispatch
	"dispatches a keyboard keypress"
	[kp-int]
	(let [kp-char (char kp-int)]
		(if (contains? @all-bindings kp-char)
			((@all-bindings kp-char)) ;evaluate
			(println kp-char kp-int))))

(defonce sys-bindings {
	(char 27) (fn [] :quit) ;escape
	})

(add-bindings sys-bindings)