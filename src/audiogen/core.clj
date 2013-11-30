(ns audiogen.core
  (:import jline.console.ConsoleReader)
  (:use overtone.live
        overtone.inst.piano)
  (:gen-class))

(def key-reader (ConsoleReader.))

(def oct (agent 72))
(defn oct-up [c] (+ c 12))
(defn oct-down [c] (- c 12))

(def int-to-note {
  97 #(+ @oct 0), 119 #(+ @oct 1), 115 #(+ @oct 2), 101 #(+ @oct 3), 
  100 #(+ @oct 4), 102 #(+ @oct 5), 116 #(+ @oct 6), 103 #(+ @oct 7), 
  121 #(+ @oct 8), 104 #(+ @oct 9), 117 #(+ @oct 10), 106 #(+ @oct 11), 
  107 #(+ @oct 12), 111 #(+ @oct 13), 108 #(+ @oct 14), 112 #(+ @oct 15)
  })

(defn is-note?
  "returns if the input key is a note or not"
  [note]
  (contains? int-to-note note))

(defn note-player
  "plays the provided note using the current instrument"
  [note]
  (if (is-note? note)
    (piano ((int-to-note note)))))

(defmulti key-dispatch
  "converts a keystroke to a function call"
  int)
(defmethod key-dispatch 113 [_] ;q quit
  false)
(defmethod key-dispatch 45 [_] ;- lower octave
  (send oct oct-down)
  true) 
(defmethod key-dispatch 61 [_] ;+ raise octave
  (send oct oct-up)
  true)
(defmethod key-dispatch :default [note] ;attempt to play
  (note-player note)
  true)

(defn print-usage
  []
  (println 
  "start typing for instrument playback!
  +- : change octave
  q : quit"))

(defn start
  "convert keystrokes into musical instrument playback"
  []
  (print-usage)
  (let [loop-fn #(key-dispatch (.readCharacter key-reader))]
      (while (loop-fn)))
  "thanks for playing!")