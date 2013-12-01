(ns audiogen.core
  (:import jline.console.ConsoleReader)
  (:use audiogen.keydispatch
        audiogen.piano
        overtone.live
        overtone.inst.piano)
  (:gen-class))

(def key-reader (ConsoleReader.))

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