(ns audiogen.core
  (:use overtone.live
        audiogen.input
        audiogen.keydispatch)
  (:gen-class))

(defn print-usage
  []
  (println 
  "start typing for instrument playback!
  +- : change octave
  ESC : quit"))

(defn key-pressed [k]
  (key-dispatch k))

(defn key-released [k]
  )

(defn start-default
  "convert keystrokes into musical instrument playback"
  []
  (print-usage)
  (use-bindings 'audiogen.sysexit)
  (use-bindings 'audiogen.piano)
  (use-bindings 'audiogen.drums)

  (start-listening key-pressed key-released)

  (println "thanks for playing!"))