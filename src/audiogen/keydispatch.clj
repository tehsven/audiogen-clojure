(ns audiogen.keydispatch
  (:gen-class))

(defmulti key-dispatch
  "converts a keystroke to a function call"
  int)

(defmethod key-dispatch 113 [_] ; q quit
  false)