(ns audiogen.inst.drums
  (:use overtone.inst.drum)
  (:gen-class))

(def bindings {
  \z #(kick :amp 1.5),
  \m #(snare :amp 1.5),
  \n #(closed-hat :amp 1.5),
  \b #(open-hat :amp 1.5)
  })