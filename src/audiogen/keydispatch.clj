(ns audiogen.keydispatch
  (:gen-class))

(def all-bindings
	(agent {}))

(defn add-bindings
	"adds a map of keys"
	[new-bindings]
	(send all-bindings #(merge %1 %2) new-bindings))

(defn use-bindings
  "adds bindings from the provided namespace"
  [namespace]
  	(require namespace)
  	(add-bindings (var-get (ns-resolve namespace 'bindings))))

(defn key-dispatch
	"dispatches a keyboard keypress"
	[kp-str]
	((@all-bindings kp-str #(println kp-str)))) ;evaluate