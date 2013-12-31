(ns audiogen.inst.switcher
  (:use audiogen.keydispatch)
  (:gen-class))

(def bindings {
  \1 #(use-bindings 'audiogen.inst.drums),
  \2 #(use-bindings 'audiogen.inst.piano),
  \3 #(use-bindings 'audiogen.inst.pianoscale),
  \4 #(use-bindings 'audiogen.inst.pianochords)
  })