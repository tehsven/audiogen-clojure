(ns audiogen.input
	(:import org.jsfml.window.Keyboard
           	 org.jsfml.window.Keyboard$Key)
	(:gen-class))

(def keypress-state (agent {}))

(defn build-key-changes
	[]
	(loop [remaining (Keyboard$Key/values)
		   n {}]
		(let [k (first remaining)]
			(if (nil? k) 
				n
				(recur (rest remaining)
					(if (= k Keyboard$Key/UNKNOWN) 
						n
						(let [k-k (clojure.string/lower-case (.name k))
							  now (if (Keyboard/isKeyPressed k) :down :up)
							  prev (@keypress-state k-k)]
							  (if (= prev now) 
							  	n
								(assoc n k-k now)))))))))

(defn key-state-watcher
	[kp-fn kr-fn]
	(let [watcher-fn 
			(fn [keyz refz old-state new-state]
				(doseq [k (keys old-state)]
					(let [old-k (old-state k)
						  new-k (new-state k)]
						(if (not= old-k new-k)
							(if (= :up old-k)
								(kp-fn k)
								(kr-fn k))))))]
		watcher-fn))

(defn start-listening
	[kp-fn kr-fn]
	(add-watch keypress-state :x (key-state-watcher kp-fn kr-fn))
	(loop [kch (build-key-changes)]
		(if (not (empty? kch))
			(send keypress-state (fn [c n] (merge c n)) kch))
		(Thread/sleep 5)
		(recur (build-key-changes))))