(ns audiogen.inst.pianoscale
  (:use overtone.inst.piano
        overtone.music.pitch)
  (:gen-class))

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
      \q #(use-scale :c4 :ionian)
      \w #(use-scale :d4 :phrygian)
      \e #(use-scale :f4 :locrian)
      \r #(use-scale :e4 :minor)
      \t #(use-scale :a3 :aeolian)
      \y #(use-scale :g5 :yu)
      \u #(use-scale :f2 :hungarian-minor)
      \i #(use-scale :a5 :neapolitan-minor)
      \o #(use-scale :b4 :melodic-major)
      \p #(use-scale :e4 :todi)
    }))