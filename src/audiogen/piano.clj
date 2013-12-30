(ns audiogen.piano
  (:use overtone.inst.piano)
  (:gen-class))

(def oct
  (agent 72))

(defn play
	"plays a note against the current octave"
	[note]
	(piano (+ @oct note)))

(def bindings {
  \, #(send oct (fn [c] (- c 12))),
  \. #(send oct (fn [c] (+ c 12))),
  \a #(play 0),
  \w #(play 1),
  \s #(play 2),
  \e #(play 3),
  \d #(play 4),
  \f #(play 5),
  \t #(play 6),
  \g #(play 7),
  \y #(play 8),
  \h #(play 9),
  \u #(play 10),
  \j #(play 11),
  \k #(play 12),
  \o #(play 13),
  \l #(play 14),
  \p #(play 15)
  })