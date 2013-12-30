(ns audiogen.pianoscale
  (:use overtone.inst.piano
        overtone.music.pitch)
  (:gen-class))

(def row-1 '(\1 \2 \3 \4 \5 \6 \7 \8 \9 \0))
(def row-q '(\q \w \e \r \t \y \u \i \o \p))
(def row-w '(\a \s \d \f \g \h \j \k \l))
(def row-z '(\z \x \c \v \b \n \m))

(defn build-scale
  [scale-root scale-name]
  (into {}
    (map #(vector %1 %2)
      row-w
      (scale scale-root scale-name))))

(def mappings
  (agent (build-scale :d4 :egyptian)))

(defn use-scale
  [scale-root scale-name]
  (send mappings (fn [c]
    (build-scale scale-root scale-name))))

(defn play
	"plays scales"
	[note]
  (if (not (nil? note))
    (piano note)))

(def bindings
  (merge
    (into {}
      (map #(vector %1 (fn [] (play (@mappings %1))))
        row-w))
    {
      \1 #(use-scale :c4 :ionian)
      \2 #(use-scale :d4 :phrygian)
      \3 #(use-scale :f4 :locrian)
      \4 #(use-scale :e4 :minor)
      \5 #(use-scale :a3 :aeolian)
      \6 #(use-scale :g5 :yu)
      \7 #(use-scale :f2 :hungarian-minor)
      \8 #(use-scale :a5 :neapolitan-minor)
      \9 #(use-scale :b4 :melodic-major)
      \0 #(use-scale :e4 :todi)
    }))