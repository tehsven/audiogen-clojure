(ns audiogen.core
  (:import jline.console.ConsoleReader)
  (:use overtone.live
        audiogen.keydispatch)
  (:gen-class))

(defn print-usage
  []
  (println 
  "start typing for instrument playback!
  +- : change octave
  ESC : quit"))

(def key-reader (ConsoleReader.))

(defn start
  "convert keystrokes into musical instrument playback"
  []
  (print-usage)
  (use-bindings 'audiogen.sysexit)
  (use-bindings 'audiogen.piano)
  (use-bindings 'audiogen.drums)
  (let [loop-fn #(key-dispatch (.readCharacter key-reader))]
      (while (not= (loop-fn) :quit)))
  (println "thanks for playing!"))