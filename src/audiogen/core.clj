(ns audiogen.core
  (:import jline.console.ConsoleReader)
  (:use overtone.live
        overtone.inst.piano)
  (:gen-class))

(def oct (agent 72))
(defn oct-up [c] (+ c 12))
(defn oct-down [c] (- c 12))

(def int-to-note {
  97 #(+ @oct 0), 119 #(+ @oct 1), 115 #(+ @oct 2), 101 #(+ @oct 3), 
  100 #(+ @oct 4), 102 #(+ @oct 5), 116 #(+ @oct 6), 103 #(+ @oct 7), 
  121 #(+ @oct 8), 104 #(+ @oct 9), 117 #(+ @oct 10), 106 #(+ @oct 11), 
  107 #(+ @oct 12), 111 #(+ @oct 13), 108 #(+ @oct 14), 112 #(+ @oct 15)
  })

(defn note-player
  "plays the provided note using the current instrument"
  [note]
  (piano note))

(defn is-note?
  "returns if the input key is a note or not"
  [note]
  (contains? int-to-note note))

(def key-reader (ConsoleReader.))

(defn key-parse
  "parses a key into a symbol"
  []
  (let [read-key (.readCharacter key-reader)]
    (if (contains? int-to-note read-key)
      (note-player ((int-to-note read-key)))
      (case read-key
        113 :quit ;q
        45 (send oct oct-down) ;-
        61 (send oct oct-up) ;+
        :default))))

(defn key-convert
  [key-sym]
  (case key-sym
    :quit false
    true))

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
  (let [loop-fn #(key-convert (key-parse))]
      (while (loop-fn)))
  "thanks for playing!")