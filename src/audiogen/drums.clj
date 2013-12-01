(ns audiogen.drums
  (:use overtone.inst.drum
        audiogen.keydispatch)
  (:gen-class))

(def drum-bindings {
  122 #(kick :amp 1), ;z
  109 #(snare :amp 1), ;m
  110 #(closed-hat :amp 1), ;n
  98 #(open-hat :amp 1) ;b
  })

(add-bindings drum-bindings)