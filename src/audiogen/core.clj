(ns audiogen.core
  (:import jline.console.ConsoleReader)
  (:use overtone.live
        audiogen.keydispatch
        audiogen.piano
        audiogen.drums)
  (:gen-class))

(def key-reader (ConsoleReader.))

(defn print-usage
  []
  (println 
  "start typing for instrument playback!
  +- : change octave
  ESC : quit"))

(defn start
  "convert keystrokes into musical instrument playback"
  []
  (print-usage)
  (let [loop-fn #(key-dispatch (.readCharacter key-reader))]
      (while (not= (loop-fn) :quit)))
  (println "thanks for playing!"))