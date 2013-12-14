(ns audiogen.core
  (:import jline.console.ConsoleReader
           org.jsfml.window.Keyboard)
  (:use overtone.live
        audiogen.keydispatch)
  (:gen-class))

(defn print-usage
  []
  (println 
  "start typing for instrument playback!
  +- : change octave
  ESC : quit"))

;(def key-reader (ConsoleReader.)
;  (.initializeTerminal key-reader))

(defn start-default
  "convert keystrokes into musical instrument playback"
  []
  (print-usage)
  (use-bindings 'audiogen.sysexit)
  (use-bindings 'audiogen.piano)
  (use-bindings 'audiogen.drums)
  (while true (do
    (doseq [k (org.jsfml.window.Keyboard$Key/values)]
      (if (not= k (org.jsfml.window.Keyboard$Key/UNKNOWN))
        (if (org.jsfml.window.Keyboard/isKeyPressed k)
          (println k))))
    (Thread/sleep 5)
  ))
  ;(let [loop-fn #(key-dispatch (.readCharacter key-reader))]
  ;    (while (not= (loop-fn) :quit)))
  (println "thanks for playing!"))