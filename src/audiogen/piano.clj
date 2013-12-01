(ns audiogen.piano
  (:use overtone.inst.piano 
  		audiogen.keydispatch)
  (:gen-class))

(def oct (agent 72))
(defn oct-up [c] (+ c 12))
(defn oct-down [c] (- c 12))

(def oct-bindings {
  45 #(send oct (fn [c] (- c 12))), ;- lower octave
  61 #(send oct (fn [c] (+ c 12)))  ;+ raise octave
  })

(defn play
	"plays a note against the current octave"
	[note]
	(piano (+ @oct note)))

(def note-bindings {
  97 #(play 0), 119 #(play 1), 115 #(play 2), 101 #(play 3), 
  100 #(play 4), 102 #(play 5), 116 #(play 6), 103 #(play 7), 
  121 #(play 8), 104 #(play 9), 117 #(play 10), 106 #(play 11), 
  107 #(play 12), 111 #(play 13), 108 #(play 14), 112 #(play 15)
  })

(add-bindings oct-bindings)
(add-bindings note-bindings)