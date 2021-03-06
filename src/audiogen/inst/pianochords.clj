(ns audiogen.inst.pianochords
  (:use overtone.inst.piano
        overtone.music.pitch)
  (:gen-class))

(def degrees '(:i :ii :iii :iv :v :vi :vii))
(def row-q '(\q \w \e \r \t \y \u \i \o \p))
(def row-w '(\a \s \d \f \g \h \j \k \l))
(def row-z '(\z \x \c \v \b \n \m))

(defn build-chord-scale
  [root scale-name]
  (into {}
    (map #(vector %1 (chord-degree %2 root scale-name 3))
      row-w
      degrees)))

(def mappings
  (agent (build-chord-scale :c4 :major)))

(defn use-chord-scale
  [root scale-name]
  (send mappings (fn [c]
    (build-chord-scale root scale-name))))

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
      \q #(use-chord-scale :c4 :major)
      \w #(use-chord-scale :g4 :major)
      \e #(use-chord-scale :f4 :locrian)
    }))