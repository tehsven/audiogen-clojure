(ns audiogen.keydispatch
  (:gen-class))

(def all-bindings (agent {}))

(defn add-bindings
	"adds a map of keys"
	[new-bindings]
	(send all-bindings (fn [c n] (merge c n)) new-bindings))

(defn use-bindings
  "adds bindings from the provided namespace"
  [namespace]
  (let [ns `~namespace]
    (require ns)
    (add-bindings (var-get (ns-resolve ns 'bindings)))))

(defn key-dispatch
	"dispatches a keyboard keypress"
	[kp-int]
	(let [kp-char (char kp-int)]
		(if (contains? @all-bindings kp-char)
			((@all-bindings kp-char)) ;evaluate
			(println kp-char kp-int))))