(ns audiogen.core
  (:import jline.console.ConsoleReader)
  (:use overtone.live
        overtone.inst.piano)
  (:gen-class))

(def int-to-note {
  97 72, 119 73, 115 74, 101 75, 
  100 76, 102 77, 116 78, 103 79, 
  121 80, 104 81, 117 82, 106 83, 
  107 84, 111 85, 108 86, 112 87
  })

(defn note-player
  "plays the provided note using the current instrument"
  [note]
  (piano note)
  :note-played)

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
      (note-player (int-to-note read-key))
      (case read-key
        113 :quit ;q
        :other))))

(defn key-convert
  [key-sym]
  (case key-sym
    :quit false
    :note-played true
    :other true))

(defn start
  "convert keystrokes into musical instrument playback"
  []
  (println "start typing for great musical fun!")
  (let [loop-fn #(key-convert (key-parse))]
      (loop [continue? (loop-fn)]
        (if continue?
            (recur (loop-fn)))))
  "thanks for playing!")