(ns audiogen.pianochords
  (:use overtone.inst.piano
        overtone.music.pitch)
  (:gen-class))

(def row-1 '(\1 \2 \3 \4 \5 \6 \7 \8 \9 \0))
(def row-q '(\q \w \e \r \t \y \u \i \o \p))
(def row-w '(\a \s \d \f \g \h \j \k \l))
(def row-z '(\z \x \c \v \b \n \m))

(defn build-chord-scale
  [root chord-name scale-name]
  (into {}
    (map #(vector %1 (chord %2 chord-name))
      row-w
      (scale root scale-name))))

(def mappings
  (agent (build-chord-scale :c4 :major :major)))

(defn use-chord-scale
  [root scale-name chord-name]
  (send mappings (fn [c]
    (build-chord-scale root chord-name scale-name))))

(defn play-chord
	"plays chords"
	[notes]
  (if (not (nil? notes))
    (doall (map #(piano %1) notes))))

(def bindings
  (merge
    (into {}
      (map #(vector %1 (fn [] (play-chord (@mappings %1))))
        row-w))
    {
      \1 #(use-chord-scale :c4 :major :major)
      \2 #(use-chord-scale :d4 :minor :major)
      \3 #(use-chord-scale :f4 :locrian :minor)
    }))