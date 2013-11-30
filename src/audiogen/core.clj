(ns audiogen.core
	(:use [seesaw.core :exclude [select config timer]]
        overtone.live
        overtone.inst.piano)
	(:gen-class))

(def key-to-note
    {
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

(defn keypressed
	"handle when a key is pressed"
	[k]
  (piano (key-to-note (.getKeyChar k))))

(defn -main
  "Convert keystrokes into musical instrument playback"
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (let [f (frame :title "keyboard to piano player")]
    (listen f :key-pressed keypressed)
    (show! f)))