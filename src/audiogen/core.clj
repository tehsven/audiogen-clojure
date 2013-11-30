(ns audiogen.core
  (:import jline.Terminal)
  (:use overtone.live
        overtone.inst.piano)
  (:gen-class))

(def char-to-note {
  \a 72,
  \w 73,
  \s 74,
  \e 75,
  \d 76,
  \f 77,
  \t 78,
  \g 79,
  \y 80,
  \h 81,
  \u 82,
  \j 83,
  \k 84,
  \o 85,
  \l 86,
  \p 87
  })

(def int-to-note {
  97 72,
  119 73,
  115 74,
  101 75,
  100 76,
  102 77,
  116 78,
  103 79,
  121 80,
  104 81,
  117 82,
  106 83,
  107 84,
  111 85,
  108 86,
  112 87
  })

(def curinst piano)

(defn -main
  "Convert keystrokes into musical instrument playback"
  [& args]
  (println "Start typing for great musical fun!")
  (let [term (Terminal/getTerminal)
        keynotegrabber #(int-to-note (.readCharacter term System/in))]
      (loop [keyednote (keynotegrabber)]
        (curinst keyednote)
        (recur (keynotegrabber)))))
