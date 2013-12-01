(ns audiogen.drums
  (:use overtone.inst.drum
        audiogen.keydispatch)
  (:gen-class))

(def drum-bindings {
  \z #(kick :amp 1),
  \m #(snare :amp 1),
  \n #(closed-hat :amp 1),
  \b #(open-hat :amp 1)
  })

(add-bindings drum-bindings)