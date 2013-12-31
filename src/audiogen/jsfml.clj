(ns audiogen.jsfml  
  (:import org.jsfml.window.Keyboard               
           org.jsfml.window.Keyboard$Key)
  (:gen-class))

(def jsfml-translations {
  (Keyboard$Key/A) \a
  (Keyboard$Key/B) \b
  (Keyboard$Key/C) \c
  (Keyboard$Key/D) \d
  (Keyboard$Key/E) \e
  (Keyboard$Key/F) \f
  (Keyboard$Key/G) \g
  (Keyboard$Key/H) \h
  (Keyboard$Key/I) \i
  (Keyboard$Key/J) \j
  (Keyboard$Key/K) \k
  (Keyboard$Key/L) \l
  (Keyboard$Key/M) \m
  (Keyboard$Key/N) \n
  (Keyboard$Key/O) \o
  (Keyboard$Key/P) \p
  (Keyboard$Key/Q) \q
  (Keyboard$Key/R) \r
  (Keyboard$Key/S) \s
  (Keyboard$Key/T) \t
  (Keyboard$Key/U) \u
  (Keyboard$Key/V) \v
  (Keyboard$Key/W) \w
  (Keyboard$Key/X) \x
  (Keyboard$Key/Y) \y
  (Keyboard$Key/Z) \z
  (Keyboard$Key/NUM0) \0
  (Keyboard$Key/NUM1) \1
  (Keyboard$Key/NUM2) \2
  (Keyboard$Key/NUM3) \3
  (Keyboard$Key/NUM4) \4
  (Keyboard$Key/NUM5) \5
  (Keyboard$Key/NUM6) \6
  (Keyboard$Key/NUM7) \7
  (Keyboard$Key/NUM8) \8
  (Keyboard$Key/NUM9) \9
  (Keyboard$Key/EQUAL) \=
  (Keyboard$Key/COMMA) \,
  (Keyboard$Key/PERIOD) \.
  (Keyboard$Key/ESCAPE) (char 27)
  })

(def jsfml-keys
  (keys jsfml-translations))

(defn jsfml-is-key-pressed
  [k]
  (Keyboard/isKeyPressed k))

(comment
  (Keyboard$Key/LBRACKET)
  (Keyboard$Key/RBRACKET)
  (Keyboard$Key/SEMICOLON)
  (Keyboard$Key/COMMA)
  (Keyboard$Key/PERIOD)
  (Keyboard$Key/QUOTE)
  (Keyboard$Key/SLASH)
  (Keyboard$Key/BACKSLASH)
  (Keyboard$Key/SPACE)
  (Keyboard$Key/RETURN)
  (Keyboard$Key/TAB)
  (Keyboard$Key/ADD)
  (Keyboard$Key/SUBTRACT)
  (Keyboard$Key/MULTIPLY)
  (Keyboard$Key/DIVIDE)
  )