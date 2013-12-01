(ns audiogen.piano
  (:use overtone.inst.piano 
  		audiogen.keydispatch)
  (:gen-class))

(def oct (agent 72))

(def oct-bindings {
  45 #(send oct (fn [c] (- c 12))), ;-
  61 #(send oct (fn [c] (+ c 12)))  ;+
  })

(defn play
	"plays a note against the current octave"
	[note]
	(piano (+ @oct note)))

(def note-bindings {
  97 #(play 0), ;a
  119 #(play 1), ;w
  115 #(play 2), ;s
  101 #(play 3), ;e
  100 #(play 4), ;d
  102 #(play 5), ;f
  116 #(play 6), ;t
  103 #(play 7), ;g
  121 #(play 8), ;y
  104 #(play 9), ;h
  117 #(play 10), ;u
  106 #(play 11), ;j
  107 #(play 12), ;k
  111 #(play 13), ;o
  108 #(play 14), ;l
  112 #(play 15) ;p
  })

(add-bindings oct-bindings)
(add-bindings note-bindings)