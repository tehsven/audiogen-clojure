(ns audiogen.sysexit
  (:gen-class))

(def bindings {
	"escape" #(System/exit 0)
	})