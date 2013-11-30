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

(defn start
  "convert keystrokes into musical instrument playback"
  []
  (println "start typing for great musical fun!")
  (let [keyreader (ConsoleReader.)
        keynotegrabber #(int-to-note (.readCharacter keyreader))]
      (loop [keyednote (keynotegrabber)]
        (if keyednote
          (do
            (piano keyednote)
            (recur (keynotegrabber))))))
  (println "thanks for playing!"))

(start)