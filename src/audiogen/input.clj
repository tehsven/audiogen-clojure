(ns audiogen.input
	(:use audiogen.sysexit
		  audiogen.jsfml)
	(:gen-class))

(def keypress-state
	(agent {}))

(defn build-key-changes
	[]
	(into {}
		(map (fn [k]
			(let [t (jsfml-translations k)
				  prev (@keypress-state t)
				  now (if (jsfml-is-key-pressed k) :down :up)]
				(if (and (not (nil? t)) (not= prev now))
					(vector t now))))
			jsfml-keys)))

(defn key-state-watcher 
	[key-pressed-fn key-released-fn]
	(defn watcher-fn
		[keyz refz current changes]
		(doall
			(map (fn [k]
				(let [prev-state (current k)
					  new-state (changes k)]
					(if (not= new-state prev-state)
						(if (= :down new-state)
							(key-pressed-fn k)
							(key-released-fn k)))))
				(keys current))))
	watcher-fn)

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
		(if (not @system-exit)
			(recur (build-key-changes)))))