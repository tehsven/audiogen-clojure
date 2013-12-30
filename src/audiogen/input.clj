(ns audiogen.input
	(:import org.jsfml.window.Keyboard
           	 org.jsfml.window.Keyboard$Key)
	(:use audiogen.jsfml)
	(:gen-class))

(def keypress-state
	(agent {}))

(defn build-key-changes
	[]
	(reduce
		(fn [changes k]
			(if (= k Keyboard$Key/UNKNOWN) 
				changes
				(let [t (translations k)
					  prev (@keypress-state t)
					  now (if (Keyboard/isKeyPressed k) :down :up)]
					  (if (or (nil? t) (= prev now))
					  	changes
						(assoc changes t now)))))
		{}
		(Keyboard$Key/values)))

(defn key-state-watcher 
	[key-pressed-fn key-released-fn]
	(let [watcher-fn
			(fn [keyz refz current changes]
				(doseq [k (keys current)]
					(let [prev-state (current k)
						  new-state (changes k)]
						(if (not= new-state prev-state)
							(if (= :down new-state)
								(key-pressed-fn k)
								(key-released-fn k))))))]
		watcher-fn))

(defn start-listening
	[key-pressed-fn key-released-fn]
	(add-watch keypress-state :x 
		(key-state-watcher key-pressed-fn key-released-fn))
	(loop [changes (build-key-changes)]
		(if (not (empty? changes))
			(send keypress-state 
				(fn [current changes]
					(merge current changes)) changes))
		(Thread/sleep 5)
		(recur (build-key-changes))))